
package it.iseed.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This class provides method in order to generate and validate JSON Web Tokens
*/
//it will be automatically instantiated and injected by Spring
public class JwtUtils {

    //https://stormpath.com/blog/jwt-java-create-verify
    //https://stormpath.com/blog/beginners-guide-jwts-in-java
    //https://github.com/jwtk/jjwt
    
    private static final String KEY = "myPersonalSecretKey12345";

    /**
     * This method generates the Jwt token to be sent to the client.
     * 
     * @param subject the subject of the JWT
     * @param date    expiration date
     * @param name    name of the user
     * @param scope   the scope to apply to the name
     * @return String jwt
     * @throws UnsupportedEncodingException
    */
    public static String generateJwt( String subject, Date date, String name, String scope ) throws UnsupportedEncodingException
    {
        String jwt = Jwts.builder()
                .setSubject( subject )
                .setExpiration( date )
                .claim( "name", name )
                .claim( "scope", scope )
                .signWith( SignatureAlgorithm.HS256, KEY.getBytes( "UTF-8" ) )
                .compact();

        return jwt;
    }
    
    /**
     * This method verifies whether the received Jwt is valid to access
     * the requested operation.
     * 
     * @param request    the received HTTP request
     * @return the map of objects associated to the Jwt
    */
    public static Map<String, Object> verifyJwtAndGetData( HttpServletRequest request ) throws UserNotLoggedException, UnsupportedEncodingException
    {
        String jwt = JwtUtils.getJwtFromHttpRequest( request );
        if (jwt == null) {
            throw new UserNotLoggedException( "Authentication token not found in the request" );
        }
        Map<String, Object> userData = jwt2Map( jwt );
        return userData;
    }
    
    /**
     * This method converts the token into a map of Userdata checking it's validity.
     * 
     * @param jwt
     * @return HashMap<String, Object> of user data
     * @throws UnsupportedEncodingException
    */
    private static Map<String, Object> jwt2Map( String jwt ) throws UnsupportedEncodingException, ExpiredJwtException
    {
        Jws<Claims> claim = Jwts.parser()
                                .setSigningKey( KEY.getBytes( "UTF-8" ) )
                                .parseClaimsJws( jwt );
        
        String name  = claim.getBody().get( "name", String.class );
        String scope = (String) claim.getBody().get( "scope" );
        
        Date expDate = claim.getBody().getExpiration();
        String subj  = claim.getBody().getSubject();
        
        Map<String, Object> userData = new HashMap<>();
        userData.put( "name", name );
        userData.put( "scope", scope );
        userData.put( "exp_date", expDate );
        userData.put( "subject", subj );
        
        Date now = new Date();
        if (now.after( expDate )) {
            throw new ExpiredJwtException( null, null, "Session expired!" );
        }
        
        return userData;
    }
    
    /**
     * This method extracts the jwt from the header or the cookie in the Http request.
     * 
     * @param request
     * @return jwt
    */
    public static String getJwtFromHttpRequest( HttpServletRequest request )
    {
        String jwt = null;
        if (request.getHeader( "jwt" ) != null) {
            jwt = request.getHeader( "jwt" );     //token present in header
        } else {
            if (request.getCookies() != null) {
                Cookie[] cookies = request.getCookies();   //token present in cookie
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals( "jwt" )) {
                        jwt = cookie.getValue();
                    }
                }
            }
        }
        return jwt;
    }
}
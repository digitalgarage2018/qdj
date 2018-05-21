package it.iseed.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.controllers.response.UserResponse;
import it.iseed.daos.LoginDao;
import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;
import it.iseed.util.JwtUtils;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.Utils;

@Service
public class LoginServiceImpl implements LoginService
{
	@Autowired
	private LoginDao loginDao;
	
	private String jwt;
	
	private static final Logger log = LoggerFactory.getLogger( LoginServiceImpl.class );
	
	
	@Override
	public UserEntity getUserByID( long id_user ) {
		return loginDao.getLoginByID( id_user );
	}
	
	@Override
	public ResponseTransferObject authenticateUserByEmail( UserEntity userEntity )
	{
	    ResponseTransferObject response = new ResponseTransferObject();
	    UserEntity user;
	    
	    try {
	        user = loginDao.getLoginByInstitutionalEmail( userEntity.getInstitutional_email() );
	    } catch( Exception e ) {
	        response.setState( ResponseTransferObject.ResponseState.EXCEPTION.getCode() );
	        response.setMessage( "Exception!" );
	        return response;
	    }
	    
	    if (user != null) {
	        if (Utils.checkPassword( userEntity.getPassword(), user.getPassword() ) &&
	            user.getInstitutional_email().equals( userEntity.getInstitutional_email() )) {
	            log.debug( "Creating Jwt..." );
	            try {
	                jwt = createJwt( user.getId_user()+"", user.getInstitutional_email(), user.getType(), new Date() );
	                log.debug( "Jwt created: " + jwt );
	            } catch( UnsupportedEncodingException e ) {
	                response.setState( ResponseTransferObject.ResponseState.EXCEPTION.getCode() );
	                response.setMessage( "Unable to create the Jwt." );
	                return response;
	            }
	            
	            response.addResult( "user", new UserResponse( user ) );
	            response.setMessage( "Login completed successfully!" );
	            response.setState( ResponseTransferObject.ResponseState.SUCCESS.getCode() );
	        } else {
	            response.setMessage( "User not found!" );
	            response.setState( ResponseTransferObject.ResponseState.FAILURE.getCode() );
	        }
	    } else {
	        response.setMessage( "User not found!" );
            response.setState( ResponseTransferObject.ResponseState.FAILURE.getCode() );
        }
	    
        return response;
    }
	
    @Override
    public List<ExamEntity> getAllExams()
    {
        List<ExamEntity> exams = loginDao.getAllExams();
        return exams;
    }
    
    @Override
    public String getJwt() {
        return jwt;
    }
    
    private String createJwt( String subject, String name, String permission, Date datenow ) throws UnsupportedEncodingException
    {
        Date expDate = datenow;
        // The expire time is 5 hours.
        expDate.setTime( datenow.getTime() + (300 * 1000 * 60) );
        log.info( "JWT Creation. Expiration time: " + expDate.getTime() );
        String token = JwtUtils.generateJwt( subject, expDate, name, permission );
        return token;
    }
}
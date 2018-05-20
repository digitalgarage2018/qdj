package it.iseed.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import it.iseed.daos.RegisterDao;
import it.iseed.entities.TokenEntity;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.ResponseTransferObject.ResponseState;
import it.iseed.util.resources.ResourceLoader;
import it.iseed.util.Utils;


@Service
public class RegisterServiceImpl implements RegisterService
{
    private static final Logger log = LoggerFactory.getLogger( RegisterService.class );
    
    @Autowired
    private RegisterDao register_dao;
    @Autowired
    private JavaMailSender mail_sender;
    
    
    
    public RegisterServiceImpl() {
        super();
    }
    
    @Override
    public ResponseTransferObject createUserAccount( UserEntity user )
    {
        String password = user.getPassword();
        user.setPassword( Utils.encryptPassword( password ) );
        
        ResponseTransferObject response = new ResponseTransferObject();
        if (register_dao.checkUser( user )) {
            log.debug( "Registration denied." );
            response.setState( ResponseState.FAILURE.getCode() );
            response.setMessage( "Username already in use." );
        } else {
            String mail = getNewInstitutionalEmail( user );
            user.setInstitutional_email( mail );
            
            log.debug( "Registration accepted." );
            register_dao.insertNewUser( user );
            
            String token = UUID.randomUUID().toString();
            register_dao.createVerificationToken( user, token );
            
            sendEmail( user, password, token );
            
            //response.addResult( "user", new UserResponse( user ) );
            response.setState( ResponseState.SUCCESS.getCode() );
            response.setMessage( "Registration accepted." );
        }
        
        return response;
    }
    
    @Override
    public TokenEntity getVerificationToken( String token )
    {
        TokenEntity verificationToken = register_dao.getVerificationToken( token );
        return verificationToken;
    }
    
    @Override
    public ResponseTransferObject saveNewUser( UserEntity user )
    {
        ResponseTransferObject response = new ResponseTransferObject();
        user.setEnabled( true );
        register_dao.saveNewUser( user );
        response.setState( ResponseState.SUCCESS.getCode() );
        response.setMessage( "Registration completed." );
        return response;
    }
    
    private void sendEmail( UserEntity user, String password, String token )
    {
        try {
            MimeMessage message = mail_sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( message, true );
            helper.setFrom( "unimarina.noreply@gmail.com" );
            helper.setTo( user.getPersonal_email() );
            helper.setSubject( "Registration Confirmation" );
            
            String text = this.readFile( "registration_template.txt" )
                              .replace( "?1", user.getInstitutional_email() )
                              .replace( "?2", password )
                              .replace( "?3", "http://localhost:3000/registration_confirm?token=" + token );
            helper.setText( text, true );
            
            mail_sender.send( message );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    
    private String readFile( String fileName )
    {
        String file = "";
        
        try {
            InputStream stream = ResourceLoader.getResourceAsStream( fileName );
            BufferedReader br = new BufferedReader( new InputStreamReader( stream ) );
            
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                file += sCurrentLine;
            }
            
            br.close();
            stream.close();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        
        return file;
    }
    
    private String getNewInstitutionalEmail( UserEntity newUser )
    {
        String n = newUser.getName().substring( 0, 1 );
        String s = newUser.getSurname();
        String email = n + "." + s + "@studenti.unimarina.it";
        return email;
    }
}
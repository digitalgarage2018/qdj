package it.iseed.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import it.iseed.daos.RegisterDao;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.Utils;
import it.seed.util.resources.ResourceLoader;


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
    public ResponseTransferObject insertNewUser( UserEntity newUser )
    {
        String password = newUser.getPassword();
        newUser.setPassword( Utils.encryptPassword( password ) );
        
        ResponseTransferObject response = new ResponseTransferObject();
        if (register_dao.checkUser( newUser )) {
            log.debug( "Registration denied." );
            response.setState( ResponseTransferObject.ResponseState.FAILURE.getCode() );
            response.setMessage( "Username already in use." );
        } else {
            String mail = getNewInstitutionalEmail( newUser );
            newUser.setInstitutional_email( mail );
            
            log.debug( "Registration accepted." );
            register_dao.insertNewUser( newUser );
            
            sendEmail( newUser, password );
            
            //response.addResult( "user", new UserResponse( user ) );
            response.setState( ResponseTransferObject.ResponseState.SUCCESS.getCode() );
            response.setMessage( "Registration accepted." );
        }
        
        return response;
    }
    
    private void sendEmail( UserEntity user, String password )
    {
        try {
            MimeMessage message = mail_sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( message, true );
            helper.setFrom( "unimarina.noreply@gmail.com" );
            helper.setTo( user.getPersonal_email() );
            helper.setSubject( "Registration status" );
            
            String text = this.readFile( "registration_template.txt" )
                              .replace( "?1", user.getInstitutional_email() )
                              .replace( "?2", password );
            helper.setText( text, true );
            
            mail_sender.send( message );
        } catch ( Exception e ) {
            
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
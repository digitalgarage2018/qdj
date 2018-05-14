package it.iseed.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import it.iseed.daos.RegisterDao;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.Utils;


@Service
public class RegisterServiceImpl implements RegisterService
{
    @Autowired
    private RegisterDao registerDao;
    
    @Autowired
    private JavaMailSender mailSender;
    
    private static final Logger log = LoggerFactory.getLogger( RegisterService.class );
    
    
    public RegisterServiceImpl() {
        super();
    }
    
    @Override
    public ResponseTransferObject insertNewUser( UserEntity newUser )
    {
        newUser.setPassword( Utils.encryptPassword( newUser.getPassword() ) );
        
        ResponseTransferObject response = new ResponseTransferObject();
        if (registerDao.checkUser( newUser )) {
            log.debug( "REGISTRAZIONE RIFIUTATA" );
            response.setState( ResponseTransferObject.ResponseState.FAILURE.getCode() );
            response.setMessage( "Username already in use." );
        } else {
            String mail = getNewInstitutionalEmail( newUser );
            newUser.setInstitutional_email( mail );
            
            log.debug( "REGISTRAZIONE ACCETTATA" );
            UserEntity user = registerDao.insertNewUser( newUser );
            SimpleMailMessage message = new SimpleMailMessage(); 
            message.setFrom( "unimarina.noreply@gmail.com" );
            message.setTo( newUser.getPersonal_email() );
            message.setSubject( "Registration status" );
            message.setText( "Registration completed succesfully." );
            mailSender.send( message );
            
            response.addResult( "user", user );
            response.setState( ResponseTransferObject.ResponseState.SUCCESS.getCode() );
            response.setMessage( "Registration accepted." );
        }
        
        return response;
    }
    
    private String getNewInstitutionalEmail( UserEntity newUser )
    {
        String n = newUser.getName().substring( 0,1 );
        String s = newUser.getSurname();
        String email = n + "." + s + "@studenti.unimarina.it";
        return email;
    }
}

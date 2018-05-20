package it.iseed.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.iseed.controllers.request.RegisterRequest;
import it.iseed.entities.TokenEntity;
import it.iseed.entities.UserEntity;
import it.iseed.services.RegisterService;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.Utils;

@RestController
@CrossOrigin
public class RegisterController
{
    @Autowired
    private RegisterService register_service;
    
    @RequestMapping(value = "/registerController", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> registerUserAccount( HttpServletRequest request,
                                                                       @RequestBody RegisterRequest register )
    {
        // TODO mi da stringa vuota, ma potrebbe servire nel caso di path particolari
        //String appUrl = request.getContextPath();
        //System.out.println( appUrl );
        
        UserEntity newUser = new UserEntity();
        
        newUser.setName( register.getName() );
        newUser.setSurname( register.getSurname() );
        newUser.setPersonal_email( register.getPersonalEmail() );
        newUser.setPassword( register.getPassword() );
        newUser.setType( UserEntity.STUDENT );
        Date dateOfBirthFormatted = null;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd" );
        
        try {
            dateOfBirthFormatted = sdf.parse( register.getDateOfBirth() );
        } catch ( ParseException e ) {
            e.printStackTrace();
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( null );
        }
        
        newUser.setDate_of_birth( dateOfBirthFormatted );
        ResponseTransferObject status = register_service.createUserAccount( newUser );
        ResponseEntity<ResponseTransferObject> response = new ResponseEntity<>( HttpStatus.OK );
        
        switch (status.getState()) {
            case 0: //NOCHANGE(0, "No action taken")
                response = ResponseEntity.status( HttpStatus.SERVICE_UNAVAILABLE ).body( status );
            break;
    
            case 1: //SUCCESS(1, "No errors found")
                response = ResponseEntity.status( HttpStatus.OK ).body( status );
            break;
    
            case 2: //FAILURE(2, "An error has been found")
                response = ResponseEntity.status( HttpStatus.OK ).body( status );
            break;
    
            case 3: //EXCEPTION(3, "An exception has been launched")
                response = ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( status );
            break;
        }
        
        return response;
    }
    
    @RequestMapping(value = "/confirmRegistration", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> confirmRegistration(
                                                @RequestParam( "token" ) String token )
    {
        TokenEntity verificationToken = register_service.getVerificationToken( token );
        if (verificationToken == null) {
            return ResponseEntity.status( HttpStatus.BAD_REQUEST )
                                 .body( Utils.createErrorMessage( "Token not found!" ) );
        }
        
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return ResponseEntity.status( HttpStatus.GATEWAY_TIMEOUT )
                                 .body( Utils.createErrorMessage( "Token expired!" ) );
        }
        
        UserEntity user = verificationToken.getUser();
        ResponseTransferObject status = register_service.saveNewUser( user );
        ResponseEntity<ResponseTransferObject> response = ResponseEntity.status( HttpStatus.OK )
                                                                        .body( status );
        
        return response;
    }
}

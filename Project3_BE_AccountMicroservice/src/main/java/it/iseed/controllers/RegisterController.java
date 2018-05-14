package it.iseed.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.iseed.entities.UserEntity;
import it.iseed.services.RegisterService;
import it.iseed.util.ResponseTransferObject;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value="/registerController", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> userInsert( HttpServletRequest request,
                                    @RequestParam(name="name") String name,
                                    @RequestParam(name="surname") String surname,
                                    @RequestParam(name="personalEmail") String personalEmail,
                                    @RequestParam(name="password") String password,
                                    @RequestParam(name="dateOfBirth") String dateOfBirth )
    {
        UserEntity newUser = new UserEntity();

        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setPersonal_email(personalEmail);
        newUser.setPassword(password);
        newUser.setType( "S" );
        Date dateOfBirthFormatted = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            dateOfBirthFormatted = sdf.parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newUser.setDate_of_birth(dateOfBirthFormatted);

        ResponseTransferObject status = registerService.insertNewUser( newUser );
        ResponseEntity<ResponseTransferObject> response = new ResponseEntity<>(HttpStatus.OK);
        
        switch (status.getState()) {
            case 0: //NOCHANGE(0, "No action taken")
                response = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(status);
            break;
    
            case 1: //SUCCESS(1, "No errors found")
                response = ResponseEntity.status(HttpStatus.OK).body(status);
            break;
    
            case 2: //FAILURE(2, "An error has been found")
                response = ResponseEntity.status(HttpStatus.OK).body(status);
            break;
    
            case 3: //EXCEPTION(3, "An exception has been launched")
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(status);
            break;
        }
        
        return response;

    }
}

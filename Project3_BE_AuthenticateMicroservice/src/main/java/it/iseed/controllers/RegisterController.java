package it.iseed.controllers;

import it.iseed.entities.UserEntity;
import it.iseed.services.RegisterService;
import it.iseed.util.ResponseTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value="/registerController", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> userInsert(HttpServletRequest request, @RequestParam("name") String name,
                                                             @RequestParam("personalEmail") String personalEmail,
                                                             @RequestParam("surname") String surname,
                                                             @RequestParam("password") String password,
                                                             @RequestParam("dateOfBirth") String dateOfBirth) {

        UserEntity newUser = new UserEntity();

        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setPersonal_email(personalEmail);
        newUser.setPassword(password);
        newUser.setType("S");

        Date dateOfBirthFormatted = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            dateOfBirthFormatted = sdf.parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newUser.setDate_of_birth(dateOfBirthFormatted);

        boolean canRegister = registerService.checkUser(newUser);

        ResponseTransferObject newUserResponse = new ResponseTransferObject(ResponseTransferObject.ResponseState.NOCHANGE.getDescription(), ResponseTransferObject.ResponseState.NOCHANGE, null,null);

        ResponseEntity response = new ResponseEntity(HttpStatus.NO_CONTENT);

        if (canRegister){

            newUserResponse = registerService.insertNewUser(newUser);

            switch (newUserResponse.getState()) {
                case 0: //NOCHANGE(0, "No action taken")
                    response = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(newUserResponse);
                    break;

                case 1: //SUCCESS(1, "No errors found")
                    response = ResponseEntity.status(HttpStatus.OK).body(newUserResponse);
                    break;

                case 3: //EXCEPTION(3, "An exception has been launched")
                    response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(newUserResponse);
                    break;
            }

        }else{
            newUserResponse.setState(ResponseTransferObject.ResponseState.FAILURE.getCode());
            newUserResponse.setMessage("Registrazione non consentita! Controllare i campi inseriti.");
            response = ResponseEntity.status(HttpStatus.OK).body(newUserResponse);
        }

        return response;
        /*ModelAndView model = new ModelAndView();

        if (canRegister){

            newUser = registerService.insertNewUser(newUser);
            model.addObject("user", newUser);
            model.setViewName("success");

        }else{
            model.setViewName("error");
        }

        return model;*/

    }
}

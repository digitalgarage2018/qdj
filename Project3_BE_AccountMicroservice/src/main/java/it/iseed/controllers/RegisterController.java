package it.iseed.controllers;

import it.iseed.entities.UserEntity;
import it.iseed.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value="/registerController", method = RequestMethod.POST)
    public ModelAndView userInsert(HttpServletRequest request) {

        UserEntity newUser = new UserEntity();

        newUser.setName(request.getParameter("name"));
        newUser.setSurname(request.getParameter("surname"));
        newUser.setPersonal_email(request.getParameter("personalEmail"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setType("S");
        //newUser.setDate_of_birth(request.getParameter("dateOfBirth"));
        Date dateOfBirthFormatted = new Date();
        String dateOfBirth = request.getParameter("dateOfBirth");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

        try {
            dateOfBirthFormatted = sdf.parse(dateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newUser.setDate_of_birth(dateOfBirthFormatted);

        boolean canRegister = registerService.checkUser(newUser);

        ModelAndView model = new ModelAndView();

        if (canRegister){

            newUser = registerService.insertNewUser(newUser);
            model.addObject("user", newUser);
            model.setViewName("success");

        }else{
            model.setViewName("error");
        }

        return model;

    }
}

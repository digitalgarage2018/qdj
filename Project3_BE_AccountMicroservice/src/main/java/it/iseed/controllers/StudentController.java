
package it.iseed.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.entities.UserEntity;
import it.iseed.services.LoginService;
import it.iseed.services.StudentService;


@RestController
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @Autowired
    private LoginService loginService;
    
    
    @RequestMapping(value="/studyPlan", method = RequestMethod.POST, headers="Accept=application/json")
    public String studyPlan( HttpServletRequest request,
                             @RequestBody StudyPlanRequest study_plan )
    {
        System.out.println( study_plan.toString() );
        //String[] exams  = request.getParameterValues( "exam" );
        studentService.insertStudyPlan( study_plan );
        return "OK";
    }
    
    @RequestMapping(value="/viewBooklet/{id}", method = RequestMethod.POST)
    public ModelAndView viewBooklet( HttpServletRequest request,
                                     @PathParam(value = "id") long userId )
    {
        UserEntity user = loginService.getUserByID( userId );
        
        request.setAttribute( "exams", user.getExam_list() );
        
        ModelAndView model = new ModelAndView();
        model.setViewName( "student/universityBooklet" );
        
        return model;
    }
}
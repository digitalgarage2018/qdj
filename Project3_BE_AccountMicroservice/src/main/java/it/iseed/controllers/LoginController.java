package it.iseed.controllers;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;
import it.iseed.services.ExamService;
import it.iseed.services.LoginService;
import it.iseed.util.ResponseTransferObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class LoginController{

	@Autowired
    private LoginService loginService;

	@Autowired
	private UserEntity userEntity;

	@Autowired
	private ExamService examService;


	@RequestMapping(value="/loginController", method = RequestMethod.POST)
	public ModelAndView userCheck(HttpServletRequest request, @RequestParam("username") String email, @RequestParam("password") String pwd) {

		userEntity.setIstitutional_email(email);
		userEntity.setPassword(pwd);

		ResponseTransferObject responseUser = loginService.authenticateUserByEmail(userEntity);

		ModelAndView model = new ModelAndView();


		if (user != null) {
		    request.getSession().setAttribute( "user_id", user.getId_user() );

			if (user.getType().equals( UserEntity.STUDENT )) {
				model.addObject( "user", user  );
			    if (user.getExam_list().isEmpty()) {
			        List<ExamEntity> exams = examService.getAllExams();
	                model.addObject( "exams", new JSONArray( exams ) );
			        model.setViewName( "student/studyPlan" );
                } else { // STUDENT_COMPLETE.
                    model.setViewName( "student/homeStudent" );
                }
			} else {
				model.addObject( "user", new JSONObject( user ) );
			    model.setViewName( "professor/welcomeDocente" );
			}
		} else {
			model.setViewName( "error" );
		}
		
    	return model;

	}
}

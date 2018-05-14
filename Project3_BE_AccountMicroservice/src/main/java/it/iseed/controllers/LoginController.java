package it.iseed.controllers;

import it.iseed.entities.UserEntity;
import it.iseed.services.LoginService;
import it.iseed.util.ResponseTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class LoginController{

	@Autowired
    private LoginService loginService;

	@Autowired
	private UserEntity userEntity;


	@RequestMapping(value="/loginController", method = RequestMethod.POST)
	public ResponseEntity<ResponseTransferObject> userCheck(HttpServletRequest request, @RequestParam("username") String email, @RequestParam("password") String pwd) {

		userEntity.setIstitutional_email(email);
		userEntity.setPassword(pwd);

		ResponseTransferObject responseUser = loginService.authenticateUserByEmail(userEntity);

		ResponseEntity<ResponseTransferObject> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

		switch (responseUser.getState()) {
			case 0: //NOCHANGE(0, "No action taken")
				response = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseUser);
			break;

			case 1: //SUCCESS(1, "No errors found")
				response = ResponseEntity.status(HttpStatus.OK).body(responseUser);
			break;

			case 2: //FAILURE(2, "An error has been found")
				response = ResponseEntity.status(HttpStatus.OK).body(responseUser);
			break;

			case 3: //EXCEPTION(3, "An exception has been launched")
				response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseUser);
			break;
		}

		return response;
	}
}

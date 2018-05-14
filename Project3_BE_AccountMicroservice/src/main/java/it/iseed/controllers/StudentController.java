
package it.iseed.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.ExpiredJwtException;
import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.entities.UserEntity;
import it.iseed.services.LoginService;
import it.iseed.services.StudentService;
import it.iseed.util.JwtUtils;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.UserNotLoggedException;
import it.iseed.util.Utils;


@RestController
public class StudentController
{
    @Autowired
    private StudentService studentService;

    @Autowired
    private LoginService loginService;
    
    
    @RequestMapping(value="/studyPlan", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<ResponseTransferObject>
                insertStudyPlan( HttpServletRequest request,
                                 @RequestBody StudyPlanRequest study_plan )
    {
        try {
            JwtUtils.verifyJwtAndGetData( request );
        } catch ( UnsupportedEncodingException e ) {
            return ResponseEntity.status( HttpStatus.FORBIDDEN )
                                 .body( Utils.createErrorMessage( "Unsupported Encoding: " + e.toString() ) );
        } catch ( UserNotLoggedException e ) {
            return ResponseEntity.status( HttpStatus.FORBIDDEN )
                                 .body( Utils.createErrorMessage( "User not correctly logged: " + e.toString() ) );
        } catch ( ExpiredJwtException e ) {
            return ResponseEntity.status( HttpStatus.GATEWAY_TIMEOUT )
                                 .body( Utils.createErrorMessage( "Session Expired!: " + e.toString() ) );
        }
        
        System.out.println( study_plan.toString() );
        ResponseEntity<ResponseTransferObject> response = new ResponseEntity<>( HttpStatus.NO_CONTENT );
        studentService.insertStudyPlan( study_plan );
        return response;
    }
    
    @RequestMapping(value="/viewBooklet/{id}", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject>
                viewBooklet( HttpServletRequest request,
                             @PathParam(value = "id") long userId )
    {
        try {
            JwtUtils.verifyJwtAndGetData( request );
        } catch ( UnsupportedEncodingException e ) {
            return ResponseEntity.status( HttpStatus.FORBIDDEN )
                                 .body( Utils.createErrorMessage( "Unsupported Encoding: " + e.toString() ) );
        } catch ( UserNotLoggedException e ) {
            return ResponseEntity.status( HttpStatus.FORBIDDEN )
                                 .body( Utils.createErrorMessage( "User not correctly logged: " + e.toString() ) );
        } catch ( ExpiredJwtException e ) {
            return ResponseEntity.status( HttpStatus.GATEWAY_TIMEOUT )
                                 .body( Utils.createErrorMessage( "Session Expired!: " + e.toString() ) );
        }
        
        ResponseEntity<ResponseTransferObject> response = new ResponseEntity<>( HttpStatus.NO_CONTENT );
        UserEntity user = loginService.getUserByID( userId );
        // TODO in teoria dovrei caricare il libretto
        
        return response;
    }
}

package it.iseed.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

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

import io.jsonwebtoken.ExpiredJwtException;
import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.services.StudentService;
import it.iseed.util.JwtUtils;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.UserNotLoggedException;
import it.iseed.util.Utils;


@RestController
@CrossOrigin
public class StudentController
{
    @Autowired
    private StudentService student_service;
    
    
    @RequestMapping(value="/studyPlan", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> insertStudyPlan(
                                             HttpServletRequest request,
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
        
        ResponseTransferObject service_result = student_service.insertStudyPlan( study_plan );
        ResponseEntity<ResponseTransferObject> response =
                        ResponseEntity.status( HttpStatus.OK )
                                      .body( service_result );
        
        return response;
    }
    
    @RequestMapping(value="/getBooklet", method = RequestMethod.GET)
    public ResponseEntity<ResponseTransferObject>
                            viewBooklet( HttpServletRequest request,
                                         @RequestParam(value = "user_id") long userId )
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
        
        ResponseTransferObject booklet = student_service.getBooklet( userId );
        ResponseEntity<ResponseTransferObject> response = ResponseEntity.status( HttpStatus.OK )
                                                                        .body( booklet );
        
        return response;
    }
    
    @RequestMapping(value="/getStudyPlan", method = RequestMethod.GET)
    public ResponseEntity<ResponseTransferObject> getStudyPlan( HttpServletRequest request )
    {
        String jwt;
        try {
            Map<String,Object> data = JwtUtils.verifyJwtAndGetData( request );
            jwt = (String) data.get( "jwt" );
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
        
        ResponseTransferObject service_response = student_service.getStudyPlan( jwt );
        ResponseEntity<ResponseTransferObject> response = ResponseEntity.status( HttpStatus.OK )
                                                                        .body( service_response );
        
        return response;
    }
    
    @RequestMapping(value="/subscribeSession", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> subscribeToSession(
                                HttpServletRequest request,
                                @RequestParam("user_id")    long user_id,
                                @RequestParam("exam_id")    long exam_id,
                                @RequestParam("session_id") long session_id )
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
        
        ResponseTransferObject service_response = student_service.subscribeToSession( user_id, exam_id, session_id );
        ResponseEntity<ResponseTransferObject> response = ResponseEntity.status( HttpStatus.OK )
                                                                        .body( service_response );
        return response;
    }
    
    @RequestMapping(value="/completeExam", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> completeExam(
                                    HttpServletRequest request,
                                    @RequestParam("user_id") long user_id,
                                    @RequestParam("exam_id") long exam_id,
                                    @RequestParam("mark")    int mark )
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
        
        ResponseTransferObject service_response = student_service.completeExam( user_id, exam_id, mark );
        ResponseEntity<ResponseTransferObject> response = ResponseEntity.status( HttpStatus.OK )
                                                                        .body( service_response );
        return response;
    }
}
package it.iseed.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import it.iseed.controllers.request.MaterialExamRequest;
import it.iseed.controllers.request.QuestionExamRequest;
import it.iseed.controllers.request.SessionRequest;
import it.iseed.services.ProfessorService;
import it.iseed.util.JwtUtils;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.UserNotLoggedException;
import it.iseed.util.Utils;

@RestController
@CrossOrigin
public class ProfessorController
{
    private static final Logger log = LoggerFactory.getLogger( ProfessorController.class );
    
    @Autowired
    private ProfessorService professor_service;
    
    @RequestMapping(value="/getExams", method=RequestMethod.GET)
    public ResponseEntity<ResponseTransferObject> getExams(
                                    HttpServletRequest request,
                                    @RequestParam("user_id") long user_id )
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
        
        ResponseTransferObject result = professor_service.getExams( user_id );
        return ResponseEntity.status( HttpStatus.OK ).body( result );
    }
    
    @RequestMapping(value="/uploadMaterialController", method=RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> upLoadMaterial(
                                  HttpServletRequest request,
                                  @RequestBody MaterialExamRequest material )
    {
        log.debug( material.toString() );
        
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
        
        ResponseTransferObject serviceResponse = professor_service.insertMaterial( material );
        ResponseEntity<ResponseTransferObject> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
        switch (serviceResponse.getState()) {
            case 0: //NOCHANGE(0, "No action taken")
                response = ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(serviceResponse);
            break;

            case 1: //SUCCESS(1, "No errors found")
                response = ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
            break;

            case 2: //FAILURE(2, "An error has been found")
                response = ResponseEntity.status(HttpStatus.OK).body(serviceResponse);
            break;

            case 3: //EXCEPTION(3, "An exception has been launched")
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(serviceResponse);
            break;
        }

        return response;
    }
    
    @RequestMapping(value="/uploadQuestions", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> uploadQuestion(
                                      HttpServletRequest request,
                                      //@RequestParam("id_exam") long id_exam,
                                      @RequestBody QuestionExamRequest question )
    {
        log.debug( question.toString() );
        
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
        
        ResponseTransferObject serviceResponse = professor_service.insertQuestions( question.getId_exam(), question );
        ResponseEntity<ResponseTransferObject> response = new ResponseEntity<>( HttpStatus.NO_CONTENT );

        switch (serviceResponse.getState()) {
            case 0: //NOCHANGE(0, "No action taken")
                response = ResponseEntity.status( HttpStatus.SERVICE_UNAVAILABLE )
                                         .body( serviceResponse );
            break;

            case 1: //SUCCESS(1, "No errors found")
                response = ResponseEntity.status( HttpStatus.OK )
                                         .body( serviceResponse );
            break;

            case 2: //FAILURE(2, "An error has been found")
                response = ResponseEntity.status( HttpStatus.OK )
                                         .body( serviceResponse );
            break;

            case 3: //EXCEPTION(3, "An exception has been launched")
                response = ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                                         .body( serviceResponse );
            break;
        }

        return response;
    }
    
    @RequestMapping(value="/getAllSessions", method = RequestMethod.GET)
    public ResponseEntity<ResponseTransferObject> getAllSessions(
                            @RequestParam(name="exam_id") long exam_id,
                            @RequestParam(name="open", defaultValue="false") boolean open)
    {
        ResponseTransferObject transfer = professor_service.getAllSessions( exam_id, open );
        ResponseEntity<ResponseTransferObject> response = ResponseEntity.status( HttpStatus.OK )
                                                                        .body( transfer );
        return response;
    }
    
    @RequestMapping(value="/createSession", method = RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> createSession(
                               @RequestParam("exam_id") long exam_id,
                               @RequestBody SessionRequest session )
    {
        ResponseTransferObject serviceResponse = professor_service.createSession( exam_id, session );
        ResponseEntity<ResponseTransferObject> response = new ResponseEntity<>( HttpStatus.NO_CONTENT );
        switch (serviceResponse.getState()) {
            case 0: //NOCHANGE(0, "No action taken")
                response = ResponseEntity.status( HttpStatus.SERVICE_UNAVAILABLE )
                                         .body( serviceResponse );
            break;
            
            case 1: //SUCCESS(1, "No errors found")
                response = ResponseEntity.status( HttpStatus.OK )
                                         .body( serviceResponse );
            break;
            
            case 2: //FAILURE(2, "An error has been found")
                response = ResponseEntity.status( HttpStatus.OK )
                                         .body( serviceResponse );
            break;
            
            case 3: //EXCEPTION(3, "An exception has been launched")
                response = ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR )
                                         .body( serviceResponse );
            break;
        }
        
        return response;
    }
}

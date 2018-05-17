
package it.iseed.controllers;

import java.io.UnsupportedEncodingException;

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
import it.iseed.services.ExamService;
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
    
    @Autowired
    private ExamService exam_service;
    
    
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
        student_service.insertStudyPlan( study_plan );
        return response;
    }
    
    @RequestMapping(value="/viewBooklet", method = RequestMethod.GET)
    public ResponseEntity<ResponseTransferObject>
                viewBooklet( HttpServletRequest request,
                             @RequestParam(value = "id") long userId )
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
        ResponseTransferObject booklet = student_service.getBookletByID( userId );
        response = ResponseEntity.status( HttpStatus.OK ).body( booklet );
        
        return response;
    }
    
    @RequestMapping(value="/getAllExams", method = RequestMethod.GET)
    public ResponseEntity<ResponseTransferObject>
                getAllexams( HttpServletRequest request )
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
        
        ResponseTransferObject service_response = exam_service.getAllExams( false );
        ResponseEntity<ResponseTransferObject> response = ResponseEntity.status( HttpStatus.ACCEPTED )
                                                                        .body( service_response );
        
        return response;
    }
}
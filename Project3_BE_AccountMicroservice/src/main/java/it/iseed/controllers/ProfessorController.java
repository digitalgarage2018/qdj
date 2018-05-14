package it.iseed.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.iseed.controllers.request.MaterialExamRequest;
import it.iseed.controllers.request.QuestionExamRequest;
import it.iseed.services.ProfessorService;
import it.iseed.util.ResponseTransferObject;

@RestController
public class ProfessorController
{
    private static final Logger log = LoggerFactory.getLogger( ProfessorController.class );
    
    @Autowired
    private ProfessorService professor_service;
    
    @RequestMapping(value="/uploadMaterialController", method=RequestMethod.POST)
    public ResponseEntity<ResponseTransferObject> upLoadMaterial(
                                  HttpServletRequest request,
                                  @RequestBody MaterialExamRequest material )
    {
        log.debug( material.toString() );
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
        
        ResponseTransferObject serviceResponse = professor_service.insertQuestions( question.getId_exam(), question );
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
}

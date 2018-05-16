
package it.iseed.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.controllers.request.MaterialExamRequest;
import it.iseed.controllers.request.QuestionExamRequest;
import it.iseed.controllers.request.QuestionExamRequest.QuestionAnswers;
import it.iseed.controllers.request.SessionRequest;
import it.iseed.controllers.response.ExamDataResponse;
import it.iseed.daos.ProfessorDao;
import it.iseed.entities.MaterialEntity;
import it.iseed.entities.QuestionEntity;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.ResponseTransferObject.ResponseState;

@Service
public class ProfessorServiceImpl implements ProfessorService
{
    @Autowired
    private ProfessorDao professorDao;
    
    @Override
    public ResponseTransferObject insertMaterial( MaterialExamRequest material )
    {
        long exam_id = material.getId_exam();
        
        List<MaterialEntity> materials = new ArrayList<>();
        for (String note : material.getNotes()) {
            materials.add( new MaterialEntity( note, MaterialEntity.NOTE ) );
        }
        
        for (String video : material.getVideos()) {
            materials.add( new MaterialEntity( video, MaterialEntity.VIDEO ) );
        }
        
        ResponseTransferObject response = new ResponseTransferObject();
        if (professorDao.insertMaterial( exam_id, materials )) {
            response.setMessage( "Question inserted correctly!" );
            response.setState( ResponseTransferObject.ResponseState.SUCCESS.getCode() );
        } else {
            response.setMessage( "Something went wrong!" );
            response.setState( ResponseTransferObject.ResponseState.FAILURE.getCode() );
        }
        
        return response;
    }

    @Override
    public ResponseTransferObject insertQuestions( long exam_id, QuestionExamRequest question )
    {
        List<QuestionEntity> questions = new ArrayList<>();
        for (QuestionAnswers qa : question.getQuestionAnswers()) {
            QuestionEntity qe = new QuestionEntity();
            qe.setQuestion( qa.getQuestion() );
            qe.setWeight( qa.getWeight() );
            qe.setAnswer1( qa.getAnswers().get( 0 ) );
            qe.setAnswer2( qa.getAnswers().get( 1 ) );
            qe.setAnswer3( qa.getAnswers().get( 2 ) );
            qe.setAnswer4( qa.getAnswers().get( 3 ) );
            qe.setCorrect_answer( qa.getCorrect_answer() );
            questions.add( qe );
        }
        
        ResponseTransferObject response = new ResponseTransferObject();
        if (professorDao.insertQuestions( exam_id, questions )) {
            response.setMessage( "Question inserted correctly!" );
            response.setState( ResponseTransferObject.ResponseState.SUCCESS.getCode() );
        } else {
            response.setMessage( "Something went wrong!" );
            response.setState( ResponseTransferObject.ResponseState.FAILURE.getCode() );
        }
        
        return response;
    }

    @Override
    public ResponseTransferObject getAllMaterial( long exam_id )
    {
        // Retrieve the material and questions associated to the exam.
        List<MaterialEntity> material  = professorDao.getMaterial( exam_id );
        List<QuestionEntity> questions = professorDao.getQuestions( exam_id );
        ExamDataResponse data = new ExamDataResponse( material, questions );
        ResponseTransferObject response = new ResponseTransferObject( null, ResponseState.SUCCESS );
        response.addResult( "data", data );
        return response;
    }

    @Override
    public ResponseTransferObject createSession( long exam_id, SessionRequest session )
    {
        Date date_start = null;
        Date date_end   = null;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd" );
        try {
            date_start = sdf.parse( session.getDate_start() );
            date_end   = sdf.parse( session.getDate_end() );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        
        ResponseTransferObject response;
        if (professorDao.insertSession( exam_id, date_start, date_end )) {
            response = new ResponseTransferObject( null, ResponseState.SUCCESS );
        } else {
            response = new ResponseTransferObject( null, ResponseState.FAILURE );
        }
        
        return response;
    }
}

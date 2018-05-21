
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
import it.iseed.controllers.response.ExamResponse;
import it.iseed.controllers.response.SessionResponse;
import it.iseed.daos.ExamDao;
import it.iseed.daos.ProfessorDao;
import it.iseed.entities.ExamEntity;
import it.iseed.entities.MaterialEntity;
import it.iseed.entities.QuestionEntity;
import it.iseed.entities.SessionEntity;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.ResponseTransferObject.ResponseState;

@Service
public class ProfessorServiceImpl implements ProfessorService
{
    @Autowired
    private ProfessorDao professor_dao;
    
    @Autowired
    private ExamDao exam_dao;
    
    
    @Override
    public ResponseTransferObject getExams( long user_id )
    {
        List<ExamEntity> exams = exam_dao.getExamsByUserId( user_id );
        List<ExamResponse> exam_response = new ArrayList<>( exams.size() );
        for (ExamEntity e : exams) {
            exam_response.add( new ExamResponse( e, null, true ) );
        }
        ResponseTransferObject result = new ResponseTransferObject( "OK", ResponseState.SUCCESS );
        result.addResult( "exams", exam_response );
        return result;
    }
    
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
        if (professor_dao.insertMaterial( exam_id, materials )) {
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
        if (professor_dao.insertQuestions( exam_id, questions )) {
            response.setMessage( "Question inserted correctly!" );
            response.setState( ResponseTransferObject.ResponseState.SUCCESS.getCode() );
        } else {
            response.setMessage( "Something went wrong!" );
            response.setState( ResponseTransferObject.ResponseState.FAILURE.getCode() );
        }
        
        return response;
    }
    
    @Override
    public ResponseTransferObject getAllSessions( long exam_id, boolean open )
    {
        List<SessionEntity> sessions = professor_dao.getAllSessions( exam_id );
        List<SessionResponse> response = new ArrayList<>( sessions.size() );
        Date now = new Date();
        for (SessionEntity entity : sessions) {
            if (open) {
                if (now.after( entity.getDate_start() ) &&
                    now.before( entity.getDate_end() )) {
                    response.add( new SessionResponse( entity ) );
                }
            }
        }
        ResponseTransferObject transfer = new ResponseTransferObject( "OK", ResponseState.SUCCESS );
        transfer.addResult( "sessions", response );
        return transfer;
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
            return new ResponseTransferObject( "Invalid date format!",
                                               ResponseState.EXCEPTION );
        }
        
        ResponseTransferObject response;
        if (professor_dao.insertSession( exam_id, date_start, date_end )) {
            response = new ResponseTransferObject( "Session created correctly!", ResponseState.SUCCESS );
        } else {
            response = new ResponseTransferObject( "Session not created!",
                                                   ResponseState.FAILURE );
        }
        
        return response;
    }
}

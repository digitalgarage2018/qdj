
package it.iseed.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.controllers.response.ExamResponse;
import it.iseed.controllers.response.UserResponse;
import it.iseed.daos.ExamDao;
import it.iseed.daos.StudentDao;
import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.ResponseTransferObject.ResponseState;

@Service
public class StudentServiceImpl implements StudentService
{
    @Autowired
    private ExamDao exam_dao;
    
    @Autowired
    private StudentDao student_dao;
    
    private static final int MAX_ATTEMPTS = 3;
    
    
    @Override
    public ResponseTransferObject insertStudyPlan( StudyPlanRequest request )
    {
        ResponseTransferObject response;
        if (exam_dao.saveExams( request.getUser_id(), request.getExams() )) {
            response = new ResponseTransferObject( "Exams saved correctly!", ResponseState.SUCCESS );
        } else {
            response = new ResponseTransferObject( "Invalid exams!", ResponseState.FAILURE );
        }
        
        return response;
    }
    
    @Override
    public ResponseTransferObject getStudyPlan( String jwt )
    {
        ResponseTransferObject response = new ResponseTransferObject();
        List<ExamEntity> exams = exam_dao.getAllExams();
        List<ExamResponse> exam_response = new ArrayList<>( exams.size() );
        for (ExamEntity exam : exams) {
            //List<UserEntity> _users = exam_dao.getUsersByExamId( exam.getId_exam() );
            List<UserResponse> users = new ArrayList<>();
            for (Map<String,Object> _user : getUsers( jwt, exam.getId_exam() )) {
                UserResponse user = new UserResponse( _user );
                if (user.getType().equals( UserEntity.PROFESSOR )) {
                    users.add( user );
                }
            }
            
            exam_response.add( new ExamResponse( exam, 0, users, false ) );
        }
        response.addResult( "exams", exam_response );
        return response;
    }
    
    @SuppressWarnings("unchecked")
    private List<Map<String,Object>> getUsers( String jwt, long exam_id )
    {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add( "jwt", jwt );
        HttpEntity<?> request = new HttpEntity<>( String.class, headers );
        final String url = "http://localhost:8060/accounts/user?exam_id=" + exam_id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseTransferObject> response =
                    restTemplate.exchange( url, HttpMethod.GET, request,
                                           ResponseTransferObject.class );
        System.out.println( "RESULTS: " + response.getBody().getValue( "users" ) );
        return (List<Map<String,Object>>) (response.getBody().getResults().get( "users" ));
    }
    
    @Override
    public ResponseTransferObject getBooklet( long user_id )
    {
        ResponseTransferObject response = new ResponseTransferObject( "OK", ResponseState.SUCCESS );
        List<ExamEntity> exams = exam_dao.getExamsByUserId( user_id );
        List<ExamResponse> exam_response = new ArrayList<>( exams.size() );
        for (ExamEntity exam : exams) {
            int mark = student_dao.getExamMark( exam.getId_exam(), user_id );
            exam_response.add( new ExamResponse( exam, mark, null, false ) );
        }
        response.addResult( "booklet", exam_response );
        return response;
    }
    
    @Override
    public ResponseTransferObject subscribeToSession( long user_id, long exam_id, long session_id )
    {
        ResponseTransferObject response;
        boolean already_done = student_dao.checkExamCompleted( user_id, exam_id );
        if (!already_done) {
            // Check for possibilities.
            int count = student_dao.getNumberOfOpenSessions( user_id, exam_id, session_id );
            if (count < MAX_ATTEMPTS) {
                student_dao.subscribeSession( user_id, exam_id, session_id );
                response = new ResponseTransferObject( "Subscribed to session!", ResponseState.SUCCESS );
            } else {
                response = new ResponseTransferObject( "No more attempts for this session!", ResponseState.FAILURE );
            }
        } else {
            // Exam already done.
            response = new ResponseTransferObject( "Exam already complete!", ResponseState.FAILURE );
        }
        
        return response;
    }
    
    @Override
    public ResponseTransferObject completeExam( long user_id, long exam_id, int mark )
    {
        // TODO sistemare tutti i return
        ResponseTransferObject response = new ResponseTransferObject();
        student_dao.completeExam( user_id, exam_id, mark );
        return response;
    }
}
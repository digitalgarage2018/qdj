
package it.iseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.daos.StudentDao;
import it.iseed.daos.StudyPlanDao;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.ResponseTransferObject.ResponseState;

@Service
public class StudentServiceImpl implements StudentService
{
    @Autowired
    private StudyPlanDao studyPlanDao;
    
    @Autowired
    private StudentDao student_dao;
    
    private static final int MAX_ATTEMPTS = 3;
    
    
    @Override
    public ResponseTransferObject insertStudyPlan( StudyPlanRequest request )
    {
        ResponseTransferObject response;
        if (studyPlanDao.saveExams( request.getUser_id(), request.getExams() )) {
            response = new ResponseTransferObject( "Exams saved correctly!", ResponseState.SUCCESS );
        } else {
            response = new ResponseTransferObject( "Invalid exams!", ResponseState.FAILURE );
        }
        
        return response;
    }
    
    @Override
    public ResponseTransferObject getStudyPlanByID( long id_user )
    {
        
        return null;
    }
    
    @Override
    public ResponseTransferObject getBookletByID( long id_user )
    {
        
        return null;
    }
    
    @Override
    public void subscribeToSession( long user_id, long exam_id, long session_id )
    {
        boolean already_done = student_dao.checkExamCompleted( user_id, exam_id );
        System.out.println( already_done );
        if (!already_done) {
            // Check for possibilities.
            Integer count = student_dao.getNumberOfOpenSessions( user_id, exam_id, session_id );
            System.out.println( "COUNT: " + count );
            if (count == null || count < MAX_ATTEMPTS) {
                student_dao.subscribeSession( user_id, exam_id, session_id );
            }
        } else {
            // Exam already done.
            
        }
    }
    
    @Override
    public void completeExam( long user_id, long exam_id )
    {
        
    }
}
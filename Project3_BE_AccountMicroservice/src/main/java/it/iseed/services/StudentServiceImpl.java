
package it.iseed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.daos.StudyPlanDao;
import it.iseed.util.ResponseTransferObject;

@Service
public class StudentServiceImpl implements StudentService
{
    @Autowired
    private StudyPlanDao studyPlanDao;
    
    @Override
    public void insertStudyPlan( StudyPlanRequest request ) {
        studyPlanDao.saveExams( request.getUser_id(), request.getExams() );
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
}
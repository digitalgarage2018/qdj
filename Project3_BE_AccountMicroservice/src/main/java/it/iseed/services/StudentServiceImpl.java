
package it.iseed.services;

import com.project.dao.StudyPlanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService
{
    @Autowired
    private StudyPlanDao studyPlanDao;
    
    @Override
    public void insertStudyPlan( long user_id, String[] exams ) {
        studyPlanDao.saveExams( user_id, exams );
    }
}

package it.iseed.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;

@Repository
@Transactional
public class StudyPlanDaoImpl implements StudyPlanDao
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveExams( long id_user, List<Long> examsId )
    {
        UserEntity user = entityManager.find( UserEntity.class, id_user );
        List<ExamEntity> exams = new ArrayList<>( examsId.size() );
        for (long exam_id : examsId) {
            exams.add( entityManager.find( ExamEntity.class, exam_id ) );
        }
        user.setExam_list( exams );
        entityManager.flush();
    }

    @Override
    public void getExams( long user_id )
    {
        
    }
}
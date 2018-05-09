
package it.iseed.daos;

import com.project.model.ExamEntity;
import com.project.model.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class StudyPlanDaoImpl implements StudyPlanDao
{
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void saveExams( long id_user, String[] examsId )
    {
        UserEntity user = entityManager.find( UserEntity.class, id_user );
        List<ExamEntity> exams = new ArrayList<>( examsId.length );
        for (String exam : examsId) {
            exams.add( entityManager.find( ExamEntity.class, Long.parseLong( exam ) ) );
        }
        user.setExam_list( exams );
        entityManager.flush();
    }
}
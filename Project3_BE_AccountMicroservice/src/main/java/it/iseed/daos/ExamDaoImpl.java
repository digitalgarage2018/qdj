package it.iseed.daos;

import com.project.model.ExamEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ExamDaoImpl implements ExamDao{

    @PersistenceContext
    public EntityManager entityManager;

    private static final String EXAM_QUERY = "SELECT e FROM ExamEntity e";

    @Override
    public List<ExamEntity> getAllExams()
    {
        List<ExamEntity> exams = null;
        try {
            exams = entityManager.createQuery( EXAM_QUERY, ExamEntity.class )
                                 .getResultList();
        } catch ( Exception e ) {
            e.printStackTrace();
            throw e;
            // Empty body.
        }
        return exams;
    }

}

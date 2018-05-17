package it.iseed.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;

@Repository
@Transactional
public class ExamDaoImpl implements ExamDao
{
    @PersistenceContext
    public EntityManager entityManager;
    
    private static final String EXAM_QUERY = "SELECT e FROM ExamEntity e";
    private static final String USER_EXAM  = "SELECT DISTINCT id_user, date_of_birth, institutional_email, name, password, personal_email, surname, type " +
                                             "FROM user, user_exam_list " + 
                                             "WHERE user_exam_list.user_list_id_user = user.id_user AND user_exam_list.exam_list_id_exam = ?1";
    
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
    
    @Override
    public List<UserEntity> getUsersByExamId( long exam_id )
    {
        @SuppressWarnings("unchecked")
        List<UserEntity> users = entityManager.createNativeQuery( USER_EXAM, UserEntity.class )
                                              .setParameter( 1, exam_id )
                                              .getResultList();
        return users;
    }
}
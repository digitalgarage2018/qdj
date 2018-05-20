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
public class ExamDaoImpl implements ExamDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    private static final String ALL_EXAMS     = "SELECT e FROM ExamEntity e";
    private static final String EXAMS_BY_USER = "SELECT DISTINCT id_exam, name, description, credits " +
                                                "FROM exam, user_exam_list " + 
                                                "WHERE user_exam_list.exam_list_id_exam = exam.id_exam AND user_exam_list.user_list_id_user = ?1";
    private static final String USERS_BY_EXAM = "SELECT DISTINCT id_user, date_of_birth, institutional_email, name, password, personal_email, surname, type, enabled " +
                                                "FROM user, user_exam_list " + 
                                                "WHERE user_exam_list.user_list_id_user = user.id_user AND user_exam_list.exam_list_id_exam = ?1";
    
    @Override
    public List<ExamEntity> getAllExams()
    {
        List<ExamEntity> exams = null;
        try {
            exams = entityManager.createQuery( ALL_EXAMS, ExamEntity.class )
                                 .getResultList();
        } catch ( Exception e ) {
            e.printStackTrace();
            throw e;
        }
        return exams;
    }
    
    @Override
    public List<ExamEntity> getExamsByUserId( long user_id )
    {
        @SuppressWarnings("unchecked")
        List<ExamEntity> exams = entityManager.createNativeQuery( EXAMS_BY_USER, ExamEntity.class )
                                              .setParameter( 1, user_id )
                                              .getResultList();
        return exams;
    }
    
    @Override
    public boolean saveExams( long id_user, List<Long> examsId )
    {
        try {
            UserEntity user = entityManager.find( UserEntity.class, id_user );
            if (user == null) {
                return false;
            }
            
            List<ExamEntity> exams = new ArrayList<>( examsId.size() );
            for (long exam_id : examsId) {
                exams.add( entityManager.find( ExamEntity.class, exam_id ) );
            }
            user.setExam_list( exams );
            entityManager.flush();
        } catch ( Exception e ) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public List<UserEntity> getUsersByExamId( long exam_id )
    {
        @SuppressWarnings("unchecked")
        List<UserEntity> users = entityManager.createNativeQuery( USERS_BY_EXAM, UserEntity.class )
                                              .setParameter( 1, exam_id )
                                              .getResultList();
        return users;
    }
}
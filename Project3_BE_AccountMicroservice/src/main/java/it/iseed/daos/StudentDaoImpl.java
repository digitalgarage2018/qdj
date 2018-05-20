
package it.iseed.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.AttemptEntity;
import it.iseed.entities.CareerEntity;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    private static final String GET_EXAM_CAREER     = "SELECT * FROM career c WHERE c.fk_user=?1 AND c.fk_exam=?2";
    private static final String GET_ATTEMPT           = "SELECT * FROM attempt WHERE fk_session = ?1 AND fk_user = ?2 AND fk_exam = ?3";
    private static final String INSERT_ATTEMPT        = "INSERT INTO attempt (fk_session, fk_user, fk_exam) VALUES (?1, ?2, ?3)";
    private static final String INSERT_EXAM_COMPLETED = "INSERT INTO career (fk_user, fk_exam, mark) VALUES (?1, ?2, ?3)";
    
    @Override
    public boolean checkExamCompleted( long user_id, long exam_id )
    {
        CareerEntity u = null;
        try {
            u = (CareerEntity) entityManager.createNativeQuery( GET_EXAM_CAREER, CareerEntity.class )
                                            .setParameter( 1, user_id )
                                            .setParameter( 2, exam_id )
                                            .getSingleResult();
        }catch ( NoResultException e ) {
            // Empty body.
        }
        
        return u != null;
    }
    
    @Override
    public boolean subscribeSession( long user_id, long exam_id, long session_id )
    {
        AttemptEntity entity = null;
        try {
            entity = (AttemptEntity) entityManager.createNativeQuery( GET_ATTEMPT, AttemptEntity.class )
                                                  .setParameter( 1, session_id )
                                                  .setParameter( 2, user_id )
                                                  .setParameter( 3, exam_id )
                                                  .getSingleResult();
            entity.incrementCount();
            entityManager.flush();
        } catch ( NoResultException e ) {
            // Empty body.
        }
        
        if (entity == null) {
            entityManager.createNativeQuery( INSERT_ATTEMPT )
                         .setParameter( 1, session_id )
                         .setParameter( 2, user_id )
                         .setParameter( 3, exam_id )
                         .executeUpdate();
        }
        
        return true;
    }
    
    @Override
    public int getNumberOfOpenSessions( long user_id, long exam_id, long session_id )
    {
        AttemptEntity entity;
        try {
            entity = (AttemptEntity) entityManager.createNativeQuery( GET_ATTEMPT, AttemptEntity.class )
                                                  .setParameter( 1, session_id )
                                                  .setParameter( 2, user_id )
                                                  .setParameter( 3, exam_id )
                                                  .getSingleResult();
        } catch ( NoResultException e ) {
            return 0;
        }
        
        return entity.getCount();
    }
    
    @Override
    public void completeExam( long user_id, long exam_id, int mark )
    {
        entityManager.createNativeQuery( INSERT_EXAM_COMPLETED )
                     .setParameter( 1, user_id )
                     .setParameter( 2, exam_id )
                     .setParameter( 3, mark )
                     .executeUpdate();
    }
    
    @Override
    public int getExamMark( long exam_id, long user_id )
    {
        try {
            CareerEntity entity = (CareerEntity) entityManager.createNativeQuery( GET_EXAM_CAREER, CareerEntity.class )
                                                              .setParameter( 1, user_id )
                                                              .setParameter( 2, exam_id )
                                                              .getSingleResult();
            return entity.getMark();
        } catch ( NoResultException e ) {
            // Empty body.
        }
        
        return 0;
    }
}
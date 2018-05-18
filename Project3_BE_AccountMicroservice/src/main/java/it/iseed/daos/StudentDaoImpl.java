
package it.iseed.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.CareerEntity;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    private static final String CHECK_EXAM_CAREER = "SELECT * FROM career c WHERE c.user_id_user=?1 AND c.fk_exam=?2";
    
    //private static final String GET_ALL_ATTEMPTS = "SELECT COUNT(*) FROM attempt WHERE ";
    
    @Override
    public boolean insertSession( long user_id, long exam_id )
    {
        
        return false;
    }
    
    @Override
    public boolean checkExamCompleted( long user_id, long exam_id )
    {
        CareerEntity u = null;
        try {
            u = (CareerEntity) entityManager.createNativeQuery( CHECK_EXAM_CAREER, CareerEntity.class )
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
        // Firstly check whether the user can be subscribed to the session.
        
        return true;
    }
}
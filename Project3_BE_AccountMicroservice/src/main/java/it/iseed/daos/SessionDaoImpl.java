
package it.iseed.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.AttemptEntity;
import it.iseed.entities.CareerEntity;
import it.iseed.entities.UserEntity;

@Repository
@Transactional
public class SessionDaoImpl implements SessionDao
{
    @PersistenceContext
    private EntityManager entityManager;
    
    //private static final String GET_ALL_ATTEMPTS = "SELECT COUNT(*) FROM attempt WHERE ";
    
    @Override
    public boolean getExamSubscribes( long user_id, long exam_id )
    {
        UserEntity user = entityManager.find( UserEntity.class, user_id );
        CareerEntity career = user.getCareer();
        if (career != null) {
            // TODO prima controlla se ha gia' sostenuto quell'esame
            /*for (AttemptEntity entity : career.getAttempts_list()) {
                if (entity.)
            }*/
        }
        return false;
    }
    
    @Override
    public boolean insertSession( long user_id, long exam_id )
    {
        
        return false;
    }
}
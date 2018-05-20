package it.iseed.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.UserEntity;

@Repository
@Transactional
public class LoginDaoImpl implements LoginDao
{
	@PersistenceContext
	private EntityManager entityManager;
	
	private final static String LOGIN_STUDENT = "SELECT u FROM UserEntity u WHERE u.institutional_email=?1";
	private static final String USERS_BY_EXAM = "SELECT DISTINCT id_user, date_of_birth, institutional_email, name, password, personal_email, surname, type, enabled " +
                                                "FROM user, user_exam_list " + 
                                                "WHERE user_exam_list.user_list_id_user = user.id_user AND user_exam_list.exam_list_id_exam = ?1";
	
    @Override
    public UserEntity getLoginByInstitutionalEmail( String istEmail )
    {
        UserEntity u = null;
        try {
            u = entityManager.createQuery( LOGIN_STUDENT, UserEntity.class )
                             .setParameter( 1, istEmail )
                             .getSingleResult();
        }catch ( NoResultException e ) {
            // Empty body.
        }
        return u;
    }
    
    @Override
	public UserEntity getLoginByID( long user_id ){
		return entityManager.find( UserEntity.class, user_id );
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
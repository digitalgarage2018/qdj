package it.iseed.daos;

import it.iseed.entities.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class LoginDaoImpl implements LoginDao {

	@PersistenceContext
	 public EntityManager entityManager;

	private final static String LOGIN_STUDENT = "SELECT u FROM UserEntity u WHERE u.istitutional_email=?1";


    @Override
    public UserEntity getLoginByIstitutionalEmail( String istEmail )
    {
        UserEntity u = null;
        try {
            u = entityManager.createQuery( LOGIN_STUDENT, UserEntity.class )
                    .setParameter( 1, istEmail )
                    .getSingleResult();
        }catch ( Exception e ){
            System.out.println( "User '" + "' not found!" );
            throw e;
        }
        return u;
    }

	public UserEntity getLoginByID(long user_id){

        return entityManager.find(UserEntity.class, user_id);

	}

}

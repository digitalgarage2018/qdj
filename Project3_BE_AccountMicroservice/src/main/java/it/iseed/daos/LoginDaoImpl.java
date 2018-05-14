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
public class LoginDaoImpl implements LoginDao {

	@PersistenceContext
	public EntityManager entityManager;

	private final static String LOGIN_STUDENT = "SELECT u FROM UserEntity u WHERE u.istitutional_email=?1";
	
	private static final String EXAM_QUERY = "SELECT e FROM ExamEntity e";

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
        }
        return u;
    }

	public UserEntity getLoginByID(long user_id){
		return entityManager.find(UserEntity.class, user_id);
	}
	
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
        }
        return exams;
    }

}

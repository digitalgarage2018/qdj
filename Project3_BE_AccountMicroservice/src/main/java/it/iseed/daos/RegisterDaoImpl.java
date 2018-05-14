package it.iseed.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.iseed.entities.UserEntity;

@Repository
@Transactional
public class RegisterDaoImpl implements RegisterDao
{
    @PersistenceContext
    public EntityManager entityManager;
    
    private static final String CHECK_STUDENT = "SELECT COUNT(*) FROM UserEntity u WHERE u.name=?1 AND u.surname=?2 AND u.personal_email=?3 AND u.date_of_birth=?4";
    
    
    @Override
    public boolean checkUser( UserEntity newUser )
    {
        Long result = entityManager.createQuery( CHECK_STUDENT, Long.class )
                                   .setParameter( 1, newUser.getName() )
                                   .setParameter( 2, newUser.getSurname() )
                                   .setParameter( 3, newUser.getPersonal_email() )
                                   .setParameter( 4, newUser.getDate_of_birth() )
                                   .getSingleResult();
        return result > 0;
    }
    
    @Override
    public UserEntity insertNewUser( UserEntity userEntity )
    {
        entityManager.persist( userEntity );
        return userEntity;
    }
}
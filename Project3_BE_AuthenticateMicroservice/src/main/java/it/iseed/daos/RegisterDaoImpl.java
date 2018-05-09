package it.iseed.daos;

import it.iseed.entities.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
@Transactional
public class RegisterDaoImpl implements RegisterDao {

    @PersistenceContext
    public EntityManager entityManager;

    private final static String CHECK_STUDENT = "SELECT COUNT(*) FROM UserEntity u WHERE u.name=? AND u.surname=? AND u.personal_email=? AND u.date_of_birth=?";


    public Long getCheckedUser(UserEntity newUser) {

        Long result;

        try{
            result = (Long) entityManager.createQuery(CHECK_STUDENT)
                    .setParameter(1,newUser.getName())
                    .setParameter(2,newUser.getSurname())
                    .setParameter(3,newUser.getPersonal_email())
                    .setParameter(4,newUser.getDate_of_birth())
                    .getSingleResult();
        }catch(Exception e){
            throw e;
        }
        return result;
    }


    public UserEntity insertNewUser(UserEntity userEntity) {

        try{

            entityManager.persist(userEntity);

        }catch(EntityExistsException eee) {
            throw eee;
        }catch (Exception e){
            throw e;
        }

        return userEntity;
    }




}

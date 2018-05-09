package it.iseed.daos;

import com.project.model.MaterialEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ProfessorDaoImpl implements ProfessorDao{

    @PersistenceContext
    public EntityManager entityManager;

    public boolean insertMeaterial(MaterialEntity material){
        boolean res = false;
        try{
            entityManager.persist(material);
            res = true;

        }catch(Exception e){
            System.out.println(e.getStackTrace().toString());
        }
        return res;
    }


}

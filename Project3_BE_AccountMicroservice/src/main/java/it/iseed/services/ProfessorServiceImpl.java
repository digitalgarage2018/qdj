package it.iseed.services;

import com.project.dao.ProfessorDao;
import com.project.model.MaterialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    ProfessorDao professorDao;

    public boolean insertMeaterial(MaterialEntity material){

        return professorDao.insertMeaterial(material);
    }
}

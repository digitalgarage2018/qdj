package it.iseed.daos;

import java.util.Date;
import java.util.List;

import it.iseed.entities.MaterialEntity;
import it.iseed.entities.QuestionEntity;
import it.iseed.entities.SessionEntity;

public interface ProfessorDao
{
    public List<MaterialEntity> getMaterial( long exam_id );
    
    public boolean insertMaterial( long exam_id, List<MaterialEntity> material );
    
    public List<QuestionEntity> getQuestions( long exam_id );
    
    public boolean insertQuestions( long exam_id, List<QuestionEntity> question );
    
    public List<SessionEntity> getAllSessions( long exam_id );
    
    public boolean insertSession( long exam_id, Date date_start, Date date_end );
}
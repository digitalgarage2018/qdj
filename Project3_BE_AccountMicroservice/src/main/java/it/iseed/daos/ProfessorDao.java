package it.iseed.daos;

import java.util.List;

import it.iseed.entities.MaterialEntity;
import it.iseed.entities.QuestionEntity;

public interface ProfessorDao
{
    public boolean insertMaterial( long exam_id, List<MaterialEntity> material );

    public boolean insertQuestions( long exam_id, List<QuestionEntity> question );
}
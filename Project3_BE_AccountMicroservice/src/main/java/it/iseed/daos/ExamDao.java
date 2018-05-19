package it.iseed.daos;

import java.util.List;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;

public interface ExamDao
{
    public List<ExamEntity> getAllExams();
    
    public List<ExamEntity> getExamsByUserId( long user_id );
    
    public boolean saveExams( long id_user, List<Long> examsId );
    
    public List<UserEntity> getUsersByExamId( long exam_id );
}
package it.iseed.services;

import java.util.List;

import it.iseed.entities.ExamEntity;
import it.iseed.util.ResponseTransferObject;

public interface ExamService
{
    public ResponseTransferObject getAllExams( boolean load_material );
    
    public List<ExamEntity> getAllExamsById( long userId );
}
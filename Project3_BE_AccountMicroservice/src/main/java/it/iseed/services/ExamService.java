package it.iseed.services;

import java.util.List;

import it.iseed.entities.ExamEntity;

public interface ExamService {

    public List<ExamEntity> getAllExams();

    public List<ExamEntity> getAllExamsById( long userId );
}

package it.iseed.services;

import it.iseed.entities.ExamEntity;

import java.util.List;

public interface ExamService {

    public List<ExamEntity> getAllExams();

    public List<ExamEntity> getAllExamsById(long userId);
}

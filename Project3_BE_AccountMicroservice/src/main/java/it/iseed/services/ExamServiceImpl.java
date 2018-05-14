package it.iseed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.daos.ExamDao;
import it.iseed.entities.ExamEntity;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamDao examDao;

    @Override
    public List<ExamEntity> getAllExams()
    {
        List<ExamEntity> exams = examDao.getAllExams();
        return exams;
    }

    @Override
    public List<ExamEntity> getAllExamsById( long userId )
    {
        List<ExamEntity> exams = examDao.getAllExams();
        return exams;
    }
}

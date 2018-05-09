package it.iseed.services;

import com.project.dao.ExamDao;
import com.project.model.ExamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

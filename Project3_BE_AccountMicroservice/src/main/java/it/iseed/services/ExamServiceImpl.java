package it.iseed.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.controllers.response.ExamResponse;
import it.iseed.controllers.response.UserResponse;
import it.iseed.daos.ExamDao;
import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;

@Service
public class ExamServiceImpl implements ExamService
{
    @Autowired
    private ExamDao examDao;

    @Override
    public ResponseTransferObject getAllExams( boolean load_material )
    {
        ResponseTransferObject response = new ResponseTransferObject();
        List<ExamEntity> exams = examDao.getAllExams();
        List<ExamResponse> exam_response = new ArrayList<>( exams.size() );
        for (ExamEntity exam : exams) {
            List<UserEntity> _users = examDao.getUsersByExamId( exam.getId_exam() );
            List<UserResponse> users = new ArrayList<>( _users.size() );
            for (UserEntity user : _users) {
                users.add( new UserResponse( user ) );
            }
            
            exam_response.add( new ExamResponse( exam, users, load_material ) );
        }
        response.addResult( "exams", exam_response );
        return response;
    }

    @Override
    public List<ExamEntity> getAllExamsById( long userId  )
    {
        List<ExamEntity> exams = examDao.getAllExams();
        return exams;
    }
}
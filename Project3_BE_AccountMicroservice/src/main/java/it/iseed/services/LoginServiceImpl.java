package it.iseed.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iseed.controllers.response.UserResponse;
import it.iseed.daos.LoginDao;
import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;
import it.iseed.util.Utils;

@Service
public class LoginServiceImpl implements LoginService
{
	@Autowired
	private LoginDao loginDao;
	
	
	@Override
	public UserEntity getUserByID(long id_user) {
		return loginDao.getLoginByID(id_user);
	}
	
	@Override
	public ResponseTransferObject authenticateUserByEmail( UserEntity userEntity )
	{
	    ResponseTransferObject response = new ResponseTransferObject();
	    UserEntity user;
	    
	    try {
	        user = loginDao.getLoginByIstitutionalEmail( userEntity.getIstitutional_email() );
	    } catch( Exception e ) {
	        response.setState( ResponseTransferObject.ResponseState.EXCEPTION.getCode() );
	        response.setMessage( "Eccezione!!" );
	        return response;
	    }
	    
	    if (user != null) {
	        if (Utils.checkPassword( userEntity.getPassword(), user.getPassword() ) &&
	            user.getIstitutional_email().equals( userEntity.getIstitutional_email() )) {
	            response.setResult( new UserResponse( user ) );
	            response.setMessage( "Utente Autenticato Correttamente!" );
	            response.setState( ResponseTransferObject.ResponseState.SUCCESS.getCode() );
	        } else {
	            response.setMessage( "Utente non trovato!" );
	            response.setState( ResponseTransferObject.ResponseState.FAILURE.getCode() );
	        }
	    } else {
	        response.setMessage( "Utente non trovato!" );
            response.setState( ResponseTransferObject.ResponseState.FAILURE.getCode() );
        }
	    
        return response;
    }
	
    @Override
    public List<ExamEntity> getAllExams()
    {
        List<ExamEntity> exams = loginDao.getAllExams();
        return exams;
    }
}
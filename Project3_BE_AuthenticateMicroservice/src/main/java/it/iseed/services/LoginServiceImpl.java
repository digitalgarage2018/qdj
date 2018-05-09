package it.iseed.services;

import it.iseed.daos.LoginDao;
import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDao loginDao;

	 public ResponseTransferObject authenticateUserByEmail(UserEntity userEntity){

	 	ResponseTransferObject response = new ResponseTransferObject(ResponseTransferObject.ResponseState.NOCHANGE.getDescription(), ResponseTransferObject.ResponseState.NOCHANGE, null,null);
	 	UserEntity user ;

	 	try{

	 		user = loginDao.getLoginByIstitutionalEmail(userEntity.getIstitutional_email());

		}catch(Exception e){
	 		response.setState(ResponseTransferObject.ResponseState.EXCEPTION.getCode());
	 		response.setMessage("Eccezione nel loginDao: " + e.getMessage());
	 		//response.setResult(e.getStackTrace());
	 		return response;
	 	}

	 	if(user != null){
	 		if(user.getPassword().equals(userEntity.getPassword()) && user.getIstitutional_email().equals(userEntity.getIstitutional_email())){

	 			response.setResult(user);
				response.setMessage("Utente Autenticato Correttamente !");
				response.setState(ResponseTransferObject.ResponseState.SUCCESS.getCode());

	 		}else {
	 			response.setMessage("Utente non trovato !");
				response.setState(ResponseTransferObject.ResponseState.FAILURE.getCode());
			}
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

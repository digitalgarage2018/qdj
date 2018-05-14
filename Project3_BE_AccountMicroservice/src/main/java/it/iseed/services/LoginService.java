package it.iseed.services;

import java.util.List;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;

public interface LoginService
{
    public ResponseTransferObject authenticateUserByEmail( UserEntity userEntity );
    
	public List<ExamEntity> getAllExams();
	
	public UserEntity getUserByID( long id_user );
	
	public String getJwt();
}
package it.iseed.services;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;

import java.util.List;

public interface LoginService {

	public List<ExamEntity> getAllExams();

	public ResponseTransferObject authenticateUserByEmail(UserEntity userEntity);


	}

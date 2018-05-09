package it.iseed.services;

import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;

public interface LoginService {
	

	public ResponseTransferObject authenticateUserByEmail(UserEntity userEntity);


	}

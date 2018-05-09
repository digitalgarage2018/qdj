package it.iseed.services;


import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;

public interface RegisterService {

    boolean checkUser(UserEntity newUser);

    ResponseTransferObject insertNewUser(UserEntity newUser);
}

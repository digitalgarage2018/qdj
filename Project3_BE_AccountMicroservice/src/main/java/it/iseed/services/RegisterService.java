package it.iseed.services;

import com.project.model.UserEntity;


public interface RegisterService {

    boolean checkUser(UserEntity newUser);

    UserEntity insertNewUser(UserEntity newUser);
}

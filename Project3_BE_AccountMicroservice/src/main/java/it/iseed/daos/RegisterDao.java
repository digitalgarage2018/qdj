package it.iseed.daos;

import it.iseed.entities.UserEntity;

public interface RegisterDao
{
    public boolean checkUser( UserEntity newUser );
    
    public UserEntity insertNewUser( UserEntity userEntity );
}
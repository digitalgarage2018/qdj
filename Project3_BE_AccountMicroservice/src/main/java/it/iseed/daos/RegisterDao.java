package it.iseed.daos;

import it.iseed.entities.TokenEntity;
import it.iseed.entities.UserEntity;

public interface RegisterDao
{
    public boolean checkUser( UserEntity newUser );
    
    public UserEntity insertNewUser( UserEntity userEntity );
    
    public TokenEntity createVerificationToken( UserEntity user, String token );
    
    public TokenEntity getVerificationToken( String token );
    
    public UserEntity saveNewUser( UserEntity userEntity );
}
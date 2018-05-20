package it.iseed.services;

import it.iseed.entities.TokenEntity;
import it.iseed.entities.UserEntity;
import it.iseed.util.ResponseTransferObject;

public interface RegisterService
{
    public ResponseTransferObject createUserAccount( UserEntity newUser );
    
    public ResponseTransferObject saveNewUser( UserEntity newUser );
    
    public TokenEntity getVerificationToken( String token );
}
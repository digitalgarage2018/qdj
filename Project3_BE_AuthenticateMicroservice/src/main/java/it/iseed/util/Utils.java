
package it.iseed.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

import it.iseed.util.ResponseTransferObject.ResponseState;

public class Utils
{
    private static final StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
    
    public static String encryptPassword( String password )
    {
        String encryptedPassword = encryptor.encryptPassword( password );
        return encryptedPassword;
    }
    
    public static boolean checkPassword( String input_password, String db_password ) {
        return encryptor.checkPassword( input_password, db_password );
    }
    
    public static ResponseTransferObject createErrorMessage( String message ) {
        return new ResponseTransferObject( message, ResponseState.FAILURE );
    }
}
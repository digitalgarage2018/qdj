
package it.iseed.util;

public class UserNotLoggedException extends Exception
{
    private static final long serialVersionUID = 6139425155028968858L;

    public UserNotLoggedException( String errorMessage ) {
        super( errorMessage );
    }
}
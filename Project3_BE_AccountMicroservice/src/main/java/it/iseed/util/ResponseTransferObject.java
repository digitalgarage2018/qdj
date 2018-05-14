
package it.iseed.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseTransferObject
{
    private String message;
    private int state;
    private Map<String,Object> results;

    public ResponseTransferObject() {
        this( ResponseTransferObject.ResponseState.NOCHANGE.getDescription(), ResponseTransferObject.ResponseState.NOCHANGE );
    }
    
    public ResponseTransferObject( String message, ResponseState state )
    {
        super();
        
        this.message = message;
        this.state = state.getCode();
        this.results = new HashMap<>();
    }
    
    public enum ResponseState
    {
        NOCHANGE(  0, "No action taken" ),
        SUCCESS(   1, "No errors found" ),
        FAILURE(   2, "An error has been found" ),
        EXCEPTION( 3, "An exception has been launched" );
        
        private final int code;
        private final String description;
        
        private ResponseState( int code, String description )
        {
            this.code = code;
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
        
        public int getCode() {
            return code;
        }
        
        @Override
        public String toString() {
            return code + ": " + description;
        }
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Map<String,Object> getResultList() {
        return results;
    }

    public void addResult( String key, Object value ) {
        this.results.put( key, value );
    }
}


package it.iseed.util;

/**
 * Inner class used as the Object tied into the Body of the ResponseEntity.
 * It's important to have this Object because it is composed of server response code and response object.
 * Then, JACKSON LIBRARY automatically convert this JsonResponseBody Object into a JSON response.
*/
public class JsonResponseBody
{
    private int server;
    private Object response;

    public JsonResponseBody() {
        super();
    }
    
    public JsonResponseBody( int server, Object response )
    {
        super();
        this.server = server;
        this.response = response;
    }
    
    public int getServer() {
        return server;
    }
    
    public void setServer( int server ) {
        this.server = server;
    }
    
    public Object getResponse() {
        return response;
    }
    
    public void setResponse( Object response ) {
        this.response = response;
    }
}
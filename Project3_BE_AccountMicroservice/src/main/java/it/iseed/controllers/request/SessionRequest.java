
package it.iseed.controllers.request;

public class SessionRequest
{
    private String date_start;
    private String date_end;
    
    public SessionRequest() {
        super();
    }
    
    public SessionRequest( String date_start, String date_end )
    {
        super();
        this.date_start = date_start;
        this.date_end = date_end;
    }
    
    public String getDate_start() {
        return date_start;
    }
    
    public void setDate_start( String date_start ) {
        this.date_start = date_start;
    }
    
    public String getDate_end() {
        return date_end;
    }
    
    public void setDate_end( String date_end ) {
        this.date_end = date_end;
    }
    
    @Override
    public String toString() {
        return "SessionEntity [date_start=" + date_start + ", date_end=" + date_end + "]";
    }
}
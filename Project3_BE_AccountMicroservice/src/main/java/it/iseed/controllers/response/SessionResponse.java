
package it.iseed.controllers.response;

import java.util.Date;

import it.iseed.entities.SessionEntity;

public class SessionResponse
{
    private Date date_start;
    private Date date_end;
    
    public SessionResponse() {
        super();
    }
    
    public SessionResponse( SessionEntity entity )
    {
        super();
        this.date_start = entity.getDate_start();
        this.date_end   = entity.getDate_end();
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start( Date date_start ) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end( Date date_end ) {
        this.date_end = date_end;
    }
    
    @Override
    public String toString() {
        return "SessionEntity [date_start=" + date_start + ", date_end=" + date_end + "]";
    }
}

package it.iseed.daos;

public interface StudentDao
{
    public boolean checkExamCompleted( long user_id, long exam_id );
    
    public Integer getNumberOfOpenSessions( long user_id, long exam_id, long session_id );
    
    public boolean subscribeSession( long user_id, long exam_id, long session_id );
}
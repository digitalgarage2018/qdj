
package it.iseed.daos;

public interface StudentDao
{
    public boolean insertSession( long user_id, long exam_id );
    
    public boolean checkExamCompleted( long user_id, long exam_id );
    
    public boolean subscribeSession( long user_id, long exam_id, long session_id );
}
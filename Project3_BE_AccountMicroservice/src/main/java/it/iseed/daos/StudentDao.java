
package it.iseed.daos;

public interface StudentDao
{
    public boolean checkExamCompleted( long user_id, long exam_id );
    
    public int getNumberOfOpenSessions( long user_id, long exam_id, long session_id );
    
    public boolean subscribeSession( long user_id, long exam_id, long session_id );
    
    public void completeExam( long user_id, long exam_id, int mark );
    
    public int getExamMark( long exam_id, long user_id );
}
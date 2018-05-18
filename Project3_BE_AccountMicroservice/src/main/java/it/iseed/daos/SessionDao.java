
package it.iseed.daos;

public interface SessionDao
{
    public boolean getExamSubscribes( long user_id, long exam_id );
    
    public boolean insertSession( long user_id, long exam_id );
}
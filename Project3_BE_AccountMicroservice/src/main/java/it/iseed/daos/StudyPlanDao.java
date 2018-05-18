
package it.iseed.daos;

import java.util.List;

public interface StudyPlanDao
{
    public boolean saveExams( long id_user, List<Long> exams );
    
    public void getExams( long user_id );
}
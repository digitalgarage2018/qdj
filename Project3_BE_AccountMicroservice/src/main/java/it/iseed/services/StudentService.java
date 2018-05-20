
package it.iseed.services;

import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.util.ResponseTransferObject;

public interface StudentService
{
    public ResponseTransferObject insertStudyPlan( StudyPlanRequest request );
    
    public ResponseTransferObject getBooklet( long id_user );
    
    public ResponseTransferObject getStudyPlan( String jwt );
    
    public ResponseTransferObject subscribeToSession( long user_id, long exam_id, long session_id );
    
    public ResponseTransferObject completeExam( long user_id, long exam_id, int mark );
}
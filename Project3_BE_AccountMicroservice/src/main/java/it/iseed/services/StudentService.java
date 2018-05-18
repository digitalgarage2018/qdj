
package it.iseed.services;

import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.util.ResponseTransferObject;

public interface StudentService
{
    public ResponseTransferObject insertStudyPlan( StudyPlanRequest request );
    
    public ResponseTransferObject getBookletByID( long id_user );
    
    public ResponseTransferObject getStudyPlanByID( long id_user );
    
    public void subscribeToSession( long user_id, long exam_id );
}
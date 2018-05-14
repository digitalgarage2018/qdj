
package it.iseed.services;

import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.util.ResponseTransferObject;

public interface StudentService
{
    public void insertStudyPlan( StudyPlanRequest request );
    
    public ResponseTransferObject getBookletByID( long id_user );
    
    public ResponseTransferObject getStudyPlanByID( long id_user );
}

package it.iseed.services;

import it.iseed.controllers.request.StudyPlanRequest;
import it.iseed.controllers.response.BookletResponse;

public interface StudentService
{
    public void insertStudyPlan( StudyPlanRequest request );
    
    public BookletResponse getStudyPlanByID( long id_user );
}
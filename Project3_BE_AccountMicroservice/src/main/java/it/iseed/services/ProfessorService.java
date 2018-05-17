package it.iseed.services;

import it.iseed.controllers.request.MaterialExamRequest;
import it.iseed.controllers.request.QuestionExamRequest;
import it.iseed.controllers.request.SessionRequest;
import it.iseed.util.ResponseTransferObject;

public interface ProfessorService
{
    public ResponseTransferObject getAllMaterial( long exam_id );
    
    public ResponseTransferObject insertMaterial( MaterialExamRequest material );

    public ResponseTransferObject insertQuestions( long exam_id, QuestionExamRequest question );
    
    public ResponseTransferObject getAllSessions( long exam_id, boolean open );
    
    public ResponseTransferObject createSession( long exam_id, SessionRequest session );
}
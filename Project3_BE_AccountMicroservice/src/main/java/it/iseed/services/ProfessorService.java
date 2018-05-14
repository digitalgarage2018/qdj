package it.iseed.services;

import it.iseed.controllers.request.MaterialExamRequest;
import it.iseed.controllers.request.QuestionExamRequest;
import it.iseed.util.ResponseTransferObject;

public interface ProfessorService
{
    public ResponseTransferObject insertMaterial( MaterialExamRequest material );

    public ResponseTransferObject insertQuestions( long exam_id, QuestionExamRequest question );
}

package it.iseed.controllers.response;

import java.util.ArrayList;
import java.util.List;

import it.iseed.entities.MaterialEntity;
import it.iseed.entities.QuestionEntity;

/**
 * This class contains all the data associated to the exam
 * including notes, videos, and questions.
*/
public class ExamDataResponse
{
    private List<String> notes;
    private List<String> videos;
    private List<ExamQuestionResponse> questions;
    
    public ExamDataResponse() {
        super();
    }
    
    public ExamDataResponse( List<MaterialEntity> material,
                             List<QuestionEntity> questions )
    {
        super();
        this.notes     = new ArrayList<>();
        this.videos    = new ArrayList<>();
        this.questions = new ArrayList<>();
        
        for (MaterialEntity m : material) {
            if (m.getType().equals( MaterialEntity.NOTE )) {
                notes.add( m.getFile() );
            } else {
                videos.add( m.getFile() );
            }
        }
        
        for (QuestionEntity q : questions) {
            this.questions.add( new ExamQuestionResponse( q ) );
        }
    }
    
    public List<String> getNotes() {
        return notes;
    }
    
    public void setNotes( List<String> notes ) {
        this.notes = notes;
    }
    
    public List<String> getVideos() {
        return videos;
    }
    
    public void setVideos( List<String> videos ) {
        this.videos = videos;
    }
    
    public List<ExamQuestionResponse> getQuestions() {
        return questions;
    }
    
    public void setQuestions( List<ExamQuestionResponse> questions ) {
        this.questions = questions;
    }
    
    @Override
    public String toString() {
        return "MaterialResponse [notes=" + notes + ", videos=" + videos + ", questions=" + questions + "]";
    }
}
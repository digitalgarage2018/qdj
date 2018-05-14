package it.iseed.controllers.request;

import java.util.List;

public class StudyPlanRequest
{
    private long user_id;
    private List<Long> exams;
    
    public StudyPlanRequest() {
        super();
    }

    public long getUser_id() {
        return user_id;
    }

    public void setExam_id( long user_id ) {
        this.user_id = user_id;
    }

    public List<Long> getExams() {
        return exams;
    }

    public void setExams( List<Long> exams ) {
        this.exams = exams;
    }

    @Override
    public String toString() {
        return "StudyPlanRequest [user_id=" + user_id + ", exams=" + exams + "]";
    }
}
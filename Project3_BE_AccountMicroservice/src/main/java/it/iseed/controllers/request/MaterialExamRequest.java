
package it.iseed.controllers.request;

import java.util.List;

public class MaterialExamRequest
{
    private long id_exam;
    private List<String> videos;
    private List<String> notes;
    
    public MaterialExamRequest() {
        super();
    }

    public MaterialExamRequest( long id_exam, List<String> videos, List<String> notes )
    {
        super();
        this.id_exam  = id_exam;
        this.videos   = videos;
        this.notes    = notes;
    }

    public long getId_exam() {
        return id_exam;
    }

    public void setId_exam(long id_exam) {
        this.id_exam = id_exam;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "MaterialExamRequest [id_exam=" + id_exam + ", videos=" + videos + ", notes="
                + notes + "]";
    }
}
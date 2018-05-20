
package it.iseed.controllers.response;

import java.util.ArrayList;
import java.util.List;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.MaterialEntity;
import it.iseed.entities.QuestionEntity;

public class ExamResponse
{
    private long id_exam;
    private String name;
    private String description;
    private int credits;
    private int mark;
    private List<UserResponse> user_list;
    private List<MaterialEntity> material_list;
    private List<QuestionEntity> question_list;
    
    
    public ExamResponse() {
        super();
    }
    
    public ExamResponse( ExamEntity exam, int mark, List<UserResponse> users, boolean load_material )
    {
        this.id_exam       = exam.getId_exam();
        this.name          = exam.getName();
        this.description   = exam.getDescription();
        this.credits       = exam.getCredits();
        this.mark          = mark;
        this.user_list     = users;
        if (load_material) {
            this.material_list = exam.getMaterial_list();
            this.question_list = exam.getQuestion_list();
        } else {
            // I prefer sending an empty list rather than a null value.
            this.material_list = new ArrayList<>();
            this.question_list = new ArrayList<>();
        }
    }
    
    public long getId_exam() {
        return id_exam;
    }
    
    public void setId_exam( long id_exam ) {
        this.id_exam = id_exam;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName( String name ) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription( String description ) {
        this.description = description;
    }
    
    public int getCredits() {
        return credits;
    }
    
    public void setMark( int mark ) {
        this.mark = mark;
    }
    
    public int getMark() {
        return mark;
    }
    
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    public List<UserResponse> getUser_list() {
        return user_list;
    }
    
    public void setUser_list( List<UserResponse> user_list ) {
        this.user_list = user_list;
    }
    
    public List<MaterialEntity> getMaterial_list() {
        return material_list;
    }
    
    public void setMaterial_list( List<MaterialEntity> material_list ) {
        this.material_list = material_list;
    }
    
    public List<QuestionEntity> getQuestion_list() {
        return question_list;
    }
    
    public void setQuestion_list( List<QuestionEntity> question_list ) {
        this.question_list = question_list;
    }
    
    @Override
    public String toString() {
        return "ExamResponse [id_exam=" + id_exam + ", name=" + name + ", description=" + description + ", credits="
                + credits + ", user_list=" + user_list + ", material_list=" + material_list + ", question_list="
                + question_list + "]";
    }
}
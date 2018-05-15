
package it.iseed.controllers.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.iseed.entities.ExamEntity;
import it.iseed.entities.UserEntity;

public class UserResponse
{
    private String institutional_email;
    private String personal_email;
    private String name;
    private String surname;
    private Date date_of_birth;
    private String type;
    
    private List<ExamResponse> exams;
    
    public UserResponse() {
        super();
    }
    
    public UserResponse( UserEntity user )
    {
        this.institutional_email = user.getInstitutional_email();
        this.personal_email      = user.getPersonal_email();
        this.name                = user.getName();
        this.surname             = user.getSurname();
        this.date_of_birth       = user.getDate_of_birth();
        this.type                = user.getType();
        
        List<ExamEntity> exams = user.getExam_list();
        this.exams = new ArrayList<>( exams.size() );
        for (ExamEntity e : exams) {
            this.exams.add( new ExamResponse( e ) );
        }
    }
    
    public String getInstitutional_email() {
        return institutional_email;
    }
    
    public void setInstitutional_email(String institutional_email) {
        this.institutional_email = institutional_email;
    }
    
    public String getPersonal_email() {
        return personal_email;
    }
    
    public void setPersonal_email(String personal_email) {
        this.personal_email = personal_email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    
    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType( String type ) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "UserResponse [institutional_email=" + institutional_email + ", personal_email=" + personal_email
                + ", name=" + name + ", surname=" + surname + ", date_of_birth=" + date_of_birth + "]";
    }
}
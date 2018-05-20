
package it.iseed.controllers.response;

import java.util.Map;

import it.iseed.entities.UserEntity;

public class UserResponse
{
    private long    matricola;
    private String  institutional_email;
    private String  personal_email;
    private String  name;
    private String  surname;
    private String  date_of_birth;
    private String  type;
    
    public UserResponse() {
        super();
    }
    
    public UserResponse( Map<String,Object> user )
    {
        this.matricola           = (Integer) user.get( "matricola" );
        this.institutional_email = (String)  user.get( "institutional_email" );
        this.personal_email      = (String)  user.get( "personal_email" );
        this.name                = (String)  user.get( "name" );
        this.surname             = (String)  user.get( "surname" );
        this.date_of_birth       = (String)  user.get( "date_of_birth" );
        this.type                = (String)  user.get( "type" );
    }
    
    public UserResponse( UserEntity user )
    {
        this.matricola           = user.getId_user();
        this.institutional_email = user.getInstitutional_email();
        this.personal_email      = user.getPersonal_email();
        this.name                = user.getName();
        this.surname             = user.getSurname();
        this.date_of_birth       = user.getDate_of_birth().toString();
        this.type                = user.getType();
    }
    
    public long getMatricola() {
        return matricola;
    }
    
    public void setMatricola( long matricola ) {
        this.matricola = matricola;
    }
    
    public String getInstitutional_email() {
        return institutional_email;
    }
    
    public void setInstitutional_email( String institutional_email ) {
        this.institutional_email = institutional_email;
    }
    
    public String getPersonal_email() {
        return personal_email;
    }
    
    public void setPersonal_email( String personal_email ) {
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
    
    public String getDate_of_birth() {
        return date_of_birth;
    }
    
    public void setDate_of_birth( String date_of_birth ) {
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
        return "UserResponse [matrciola=" + matricola + ", institutional_email=" + institutional_email + ", personal_email=" + personal_email
                + ", name=" + name + ", surname=" + surname + ", date_of_birth=" + date_of_birth + "]";
    }
}
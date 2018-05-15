
package it.iseed.controllers.request;

public class RegisterRequest
{
    private String name;
    private String surname;
    private String personalEmail;
    private String password;
    private String dateOfBirth;
    
    public RegisterRequest() {
        super();
    }
    
    public RegisterRequest(String name, String surname, String personalEmail, String password, String dateOfBirth)
    {
        super();
        this.name = name;
        this.surname = surname;
        this.personalEmail = personalEmail;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
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

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "RegisterRequest [name=" + name + ", surname=" + surname + ", personalEmail=" + personalEmail
                + ", password=" + password + ", dateOfBirth=" + dateOfBirth + "]";
    }
}
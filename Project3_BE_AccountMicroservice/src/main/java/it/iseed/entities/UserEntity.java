package it.iseed.entities;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@SessionScope
@Component
@Table(name="user")
public class UserEntity implements Serializable
{
    private static final long serialVersionUID = -4203404679059807682L;

    public static final String STUDENT = "S", PROFESSOR = "P";
    
    public UserEntity() {
        super();
    }

    public UserEntity(long id_user, String istitutional_email, String personal_email, String password, String name, String surname, Date date_of_birth, String type, List<ExamEntity> exam_list) {
        this.id_user = id_user;
        this.istitutional_email = istitutional_email;
        this.personal_email = personal_email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.type = type;
        this.exam_list = exam_list;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user", nullable=false)
    private long id_user;

    @NotEmpty
    @NotBlank
    @Column(name="istitutional_email", nullable=false)
    private String istitutional_email;

    @Column(name="personal_email")
    private String personal_email;
    
    @NotEmpty
    @NotBlank
    @Column(name="password", nullable=false)
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Temporal(TemporalType.DATE)
    @Column(name="date_of_birth")
    private Date date_of_birth;

    @NotEmpty
    @NotBlank
    @Column(name="type")
    private String type;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ExamEntity> exam_list;



    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getIstitutional_email() {
        return istitutional_email;
    }

    public void setIstitutional_email(String istitutional_email) {
        this.istitutional_email = istitutional_email;
    }

    public String getPersonal_email() {
        return personal_email;
    }

    public void setPersonal_email(String personal_email) {
        this.personal_email = personal_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setType(String type) {
        this.type = type;
    }

    public List<ExamEntity> getExam_list() {
        return exam_list;
    }

    public void setExam_list(List<ExamEntity> exam_list) {
        this.exam_list = exam_list;
    }

    @Override
    public String toString() {
        return "UserEntity [id_user=" + id_user + ", istitutional_email=" + istitutional_email + ", personal_email="
                + personal_email + ", password=" + password + ", name=" + name + ", surname=" + surname
                + ", date_of_birth=" + date_of_birth + ", type=" + type + ", exam_list=" + exam_list + "]";
    }
}

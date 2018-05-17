package it.iseed.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="exam")
public class ExamEntity implements Serializable
{
    private static final long serialVersionUID = 4296529480985482024L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_exam", nullable=false)
    private long id_exam;
    
    @Column(name="name")
    private String name;
    
    @Column(name="description", length = 1000)
    private String description;
    
    @Column(name="credits")
    private int credits;
    
    @ManyToMany(fetch = FetchType.LAZY,
                mappedBy="exam_list")
    private List<UserEntity> user_list;
    
    @OneToMany(fetch = FetchType.LAZY,
               orphanRemoval = true,
               cascade = CascadeType.ALL)
    @JoinColumn(name="fk_exam")
    private List<MaterialEntity> material_list;
    
    @OneToMany(fetch = FetchType.LAZY,
               orphanRemoval = true,
               cascade = CascadeType.ALL)
    @JoinColumn(name="fk_exam")
    private List<QuestionEntity> question_list;
    
    @OneToMany(fetch = FetchType.LAZY,
               orphanRemoval = true,
               cascade = CascadeType.ALL)
    @JoinColumn(name="fk_exam")
    private List<SessionEntity> session_list;
    
    
    public ExamEntity() {
        super();
    }
    
    public ExamEntity( long id_exam, String name, String description, int credits,List<UserEntity> user_list )
    {
        this.id_exam = id_exam;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.user_list = user_list;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<UserEntity> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserEntity> user_list) {
        this.user_list = user_list;
    }
    
    public List<MaterialEntity> getMaterial_list() {
        return material_list;
    }

    public void setMaterial_list( List<MaterialEntity> material_list) {
        this.material_list = material_list;
    }
    
    public List<QuestionEntity> getQuestion_list() {
        return question_list;
    }

    public void setQuestion_list( List<QuestionEntity> material_list) {
        this.question_list = material_list;
    }
    
    public List<SessionEntity> getSession_list() {
        return session_list;
    }
    
    public void addSession( SessionEntity session ) {
        session_list.add( session );
    }

    public void setSession_list( List<SessionEntity> session_list) {
        this.session_list = session_list;
    }

    @Override
    public String toString() {
        return "ExamEntity [id_exam=" + id_exam + ", name=" + name + ", description=" + description + ", credits="
                + credits + ", user_list=" + user_list + ", material_list=" + material_list + ", question_list="
                + question_list + ", session_list=" + session_list + "]";
    }
}

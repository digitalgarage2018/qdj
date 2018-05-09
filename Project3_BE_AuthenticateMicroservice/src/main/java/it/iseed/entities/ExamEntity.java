package it.iseed.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table (name="exam")
public class ExamEntity implements Serializable {

    public ExamEntity() {
        super();
    }

    public ExamEntity(long id_exam, String name, String description, int credits, List<UserEntity> user_list, List<MaterialEntity> material_list) {
        this.id_exam = id_exam;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.user_list = user_list;
        this.material_list = material_list;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(name="id_exam", nullable=false)
    private long id_exam;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="credits")
    private int credits;

    @ManyToMany(mappedBy = "exam_list")
    private List<UserEntity> user_list;

    @OneToMany
    private List<MaterialEntity> material_list;

    @OneToMany(mappedBy = "exam_fk")
    private List<QuestionEntity> question_list;


    public long getId_exam() {
        return id_exam;
    }

    public void setId_exam(long id_exam) {
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

    public void setMaterial_list(List<MaterialEntity> material_list) {
        this.material_list = material_list;
    }
}

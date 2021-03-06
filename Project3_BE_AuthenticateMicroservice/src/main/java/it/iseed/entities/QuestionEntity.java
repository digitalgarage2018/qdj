package it.iseed.entities;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="question")
public class QuestionEntity implements Serializable {

    public QuestionEntity() {
        super();
    }

    public QuestionEntity(String question, int weight, String answer1, String answer2, String answer3, String answer4, int correct_answer) {
        this.question = question;
        this.weight = weight;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correct_answer = correct_answer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_question;

    @NotBlank
    @NotEmpty
    private String question;

    @NotBlank
    @NotEmpty
    private int weight;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    @NotBlank
    @NotEmpty
    private int correct_answer;

    @ManyToOne
    private ExamEntity exam_fk;


    public long getId_question() {
        return id_question;
    }

    public void setId_question(long id_question) {
        this.id_question = id_question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(int correct_answer) {
        this.correct_answer = correct_answer;
    }

    public ExamEntity getExam_fk() {
        return exam_fk;
    }

    public void setExam_fk(ExamEntity exam_fk) {
        this.exam_fk = exam_fk;
    }
}

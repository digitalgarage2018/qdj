
package it.iseed.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="career")
public class CareerEntity implements Serializable
{
    private static final long serialVersionUID = -4113553652241084450L;
    
    @Id
    private long id_career;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserEntity user;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_exam")
    //@JoinColumn(name = "fk_exam")
    private ExamEntity exam;
    
    public CareerEntity() {
        super();
    }
    
    public long getId_career() {
        return id_career;
    }
    
    public void setId_career( long id_career ) {
        this.id_career = id_career;
    }
    
    public ExamEntity getExam() {
        return exam;
    }
    
    public void setExam( ExamEntity exam ) {
        this.exam = exam;
    }
    
    @Override
    public String toString() {
        return "CareerEntity [id_career=" + id_career + ", exam=" + exam + "]";
    }
}
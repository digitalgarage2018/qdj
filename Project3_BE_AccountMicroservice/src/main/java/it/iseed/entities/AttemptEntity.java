
package it.iseed.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name= "attempt")
public class AttemptEntity implements Serializable
{
    private static final long serialVersionUID = -297296506615484195L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_attempt", nullable=false)
    private long id_attempt;
    
    // TODO il count non dovrebbe servire piu'
    @Column(name="count")
    @ColumnDefault("0")
    private int count;
    
    @Column(name="mark")
    private int mark;
    
    private long exam_fk;
    
    private long session_fk;
    
    public AttemptEntity() {
        super();
    }
    
    public AttemptEntity( int count ) {
        this.count = count;
    }
    
    public void increment(){
        count++;
    }
    
    public long getId_attempt() {
        return id_attempt;
    }
    
    public void setId_attempt( long id_attempt ) {
        this.id_attempt = id_attempt;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount( int count ) {
        this.count = count;
    }
    
    public int getMark() {
        return mark;
    }
    
    public void setMark( int mark ) {
        this.mark = mark;
    }
    
    @Override
    public String toString() {
        return "AttemptEntity [id_attempt=" + id_attempt + ", count=" + count + ", mark=" + mark + "]";
    }
}
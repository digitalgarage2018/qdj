
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
@Table(name= "attemp")
public class AttempEntity implements Serializable
{
    private static final long serialVersionUID = -297296506615484195L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_attemp", nullable=false)
    private long id_attemp;
    
    @Column(name="count")
    @ColumnDefault("0")
    private int count;
    
    @Column(name="mark")
    private int mark;

    public AttempEntity() {
        super();
    }
    
    public AttempEntity( int count ) {
        this.count = count;
    }
    
    public void increment(){
        count++;
    }
    
    public long getId_attemp() {
        return id_attemp;
    }
    
    public void setId_attemp( long id_attemp ) {
        this.id_attemp = id_attemp;
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
    
    public void setMark(int mark) {
        this.mark = mark;
    }
}

package it.iseed.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="career")
public class CareerEntity implements Serializable
{
    private static final long serialVersionUID = -4113553652241084450L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_career", nullable=false)
    private long id_career;
    
    @Column(name="mark")
    private int mark;
    
    public CareerEntity( int mark )
    {
        super();
        this.mark = mark;
    }
    
    public long getId_career() {
        return id_career;
    }
    
    public void setId_career( long id_career ) {
        this.id_career = id_career;
    }
    
    public int getMark() {
        return mark;
    }
    
    public void setMark( int mark ) {
        this.mark = mark;
    }
    
    @Override
    public String toString() {
        return "CareerEntity [id_career=" + id_career + ", mark=" + mark + "]";
    }
}
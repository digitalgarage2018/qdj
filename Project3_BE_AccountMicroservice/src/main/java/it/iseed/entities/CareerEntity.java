
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
    
    public CareerEntity() {
        super();
    }
    
    
}
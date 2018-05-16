
package it.iseed.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="career")
public class CareerEntity implements Serializable
{
    private static final long serialVersionUID = -4113553652241084450L;
    
    
    
    public CareerEntity() {
        super();
    }
    
    
}
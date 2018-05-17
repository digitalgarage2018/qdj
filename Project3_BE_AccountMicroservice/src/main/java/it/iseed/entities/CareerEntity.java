
package it.iseed.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="career")
public class CareerEntity implements Serializable
{
    private static final long serialVersionUID = -4113553652241084450L;
    
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="id_career", nullable=false)
    private long id_career;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private UserEntity user;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_career")
    private List<AttemptEntity> attempts_list;
    
    public CareerEntity() {
        super();
    }
    
    public long getId_career() {
        return id_career;
    }
    
    public void setId_career( long id_career ) {
        this.id_career = id_career;
    }
    
    public List<AttemptEntity> getAttempts_list() {
        return attempts_list;
    }
    
    public void setAttempts_list( List<AttemptEntity> attempts_list ) {
        this.attempts_list = attempts_list;
    }
    
    @Override
    public String toString() {
        return "CareerEntity [id_career=" + id_career + ", attempts_list=" + attempts_list + "]";
    }
}
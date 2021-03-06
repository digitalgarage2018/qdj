
package it.iseed.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="session")
public class SessionEntity implements Serializable
{
    private static final long serialVersionUID = 8644741967012304877L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_session", nullable=false)
    private long id_session;
    
    @Temporal(TemporalType.DATE)
    @Column(name="date_start")
    private Date date_start;
    
    @Temporal(TemporalType.DATE)
    @Column(name="date_end")
    private Date date_end;
    
    @OneToMany(fetch = FetchType.LAZY,
               orphanRemoval = true,
               cascade = CascadeType.ALL)
    @JoinColumn(name="fk_session")
    private List<AttemptEntity> attempts;
    
    
    public SessionEntity() {
        super();
    }
    
    public SessionEntity( Date date_start, Date date_end )
    {
        super();
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public long getId_session() {
        return id_session;
    }

    public void setId_session(long id_session) {
        this.id_session = id_session;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }
    
    @Override
    public String toString() {
        return "SessionEntity [id_session=" + id_session + ", date_start=" + date_start + ", date_end=" + date_end + "]";
    }
}
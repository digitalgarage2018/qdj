
package it.iseed.entities;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="token")
public class TokenEntity
{
    private static final int EXPIRATION = 60 * 24;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_token;
    
    @Column(name="token")
    private String token;
    
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;
    
    @Temporal(TemporalType.DATE)
    @Column(name="expiry_date")
    private Date expiryDate;
    
    public TokenEntity() {
        super();
    }
    
    public TokenEntity( String token, UserEntity user )
    {
        super();
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate( EXPIRATION );
    }
    
    private Date calculateExpiryDate( int expiryTimeInMinutes )
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime( new Timestamp( cal.getTime().getTime() ) );
        cal.add( Calendar.MINUTE, expiryTimeInMinutes );
        return new Date( cal.getTime().getTime() );
    }

    public Long getId_token() {
        return id_token;
    }
    
    public void setId_token( Long id_token ) {
        this.id_token = id_token;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken( String token ) {
        this.token = token;
    }
    
    public UserEntity getUser() {
        return user;
    }
    
    public void setUser( UserEntity user ) {
        this.user = user;
    }
    
    public Date getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate( Date expiryDate ) {
        this.expiryDate = expiryDate;
    }
    
    @Override
    public String toString() {
        return "TokenEntity [id_token=" + id_token + ", token=" + token + ", user=" + user + ", expiryDate=" + expiryDate + "]";
    }
}
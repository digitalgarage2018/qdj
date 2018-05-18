package it.iseed.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="material")
public class MaterialEntity implements Serializable
{
    private static final long serialVersionUID = 8490877745613382422L;
    
    public static final String NOTE  = "N";
    public static final String VIDEO = "V";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_material", nullable=false)
    private long id_material;
    
    @Column(name="file")
    private String file;
    
    @Column(name="type")
    private String type;
    
    public MaterialEntity() {
        super();
    }

    public MaterialEntity( String file, String type )
    {
        this.file = file;
        this.type = type;
    }

    public long getId_material() {
        return id_material;
    }

    public void setId_material(long id_material) {
        this.id_material = id_material;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
    public void setType( String type ) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MaterialEntity [id_material=" + id_material + ", file=" + file + ", type=" + type + "]";
    }
}

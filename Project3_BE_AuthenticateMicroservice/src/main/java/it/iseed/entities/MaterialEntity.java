package it.iseed.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="material")
public class MaterialEntity implements Serializable {

    public MaterialEntity() {
        super();
    }

    public MaterialEntity(String name_pdf, byte[] file, String link_to_video, String video_name) {
        this.name_pdf = name_pdf;
        this.file = file;
        this.link_to_video = link_to_video;
        this.video_name = video_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_material", nullable=false)
    private long id_material;

    @Column(name="name_pdf")
    private String name_pdf;

    @Column(name="file")
    private byte[] file;

    @Column(name="video_name")
    private String video_name;

    @Column(name="link_to_video")
    private String link_to_video;

    public long getId_material() {
        return id_material;
    }

    public void setId_material(long id_material) {
        this.id_material = id_material;
    }

    public String getName_pdf() {
        return name_pdf;
    }

    public void setName_pdf(String name_pdf) {
        this.name_pdf = name_pdf;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getLink_to_video() {
        return link_to_video;
    }

    public void setLink_to_video(String link_to_video) {
        this.link_to_video = link_to_video;
    }
}

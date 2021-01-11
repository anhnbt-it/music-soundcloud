package com.codegym.music.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "songs")
public class Song implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long album_id;

    private Long singer_id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "lyric",nullable = false,columnDefinition = "TEXT")
    private String lyric;

    @Basic(optional = false)
    private String image;

    @Column(name = "url",nullable = false)
    private String url;

    @Column(name = "status",nullable = false)
    private boolean status;

    @Column(name = "create_at",nullable = false)
    private Date create_at;

    public Song() {
    }

    @Transient
    private MultipartFile imageData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Long album_id) {
        this.album_id = album_id;
    }

    public Long getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(Long singer_id) {
        this.singer_id = singer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public MultipartFile getImageData() {
        return imageData;
    }

    public void setImageData(MultipartFile imageData) {
        this.imageData = imageData;
    }
}

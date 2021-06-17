package com.codegym.zingmp3.model;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;


@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String imageURL;
    @Transient
    private MultipartFile imageData;


    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    private Artist artist;

    @ManyToMany(mappedBy = "albums", cascade = CascadeType.REMOVE)
    private Collection<Song> songs;


    public Collection<Song> getSongs() {
        return songs;
    }

    public void setSongs(Collection<Song> songs) {
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public MultipartFile getImageData() {
        return imageData;
    }

    public Artist getSinger() {
        return artist;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imgSrc) {
        this.imageURL = imgSrc;
    }

    public void setImageData(MultipartFile imageData) {
        this.imageData = imageData;
    }

    public void setSinger(Artist artist) {
        this.artist = artist;
    }
}

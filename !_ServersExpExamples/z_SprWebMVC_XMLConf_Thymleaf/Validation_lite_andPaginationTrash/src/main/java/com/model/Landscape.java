package com.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name = "landscapes")
public class Landscape {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String content;
    private String image;

    @Transient
    private MultipartFile avatar;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Landscape() {
    }

    public Landscape(Long id, String name, String content, String image, MultipartFile avatar, Country country) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.image = image;
        this.avatar = avatar;
        this.country = country;
    }

    public Landscape(String name, String content, MultipartFile avatar, Country country) {
        this.name = name;
        this.content = content;
        this.avatar = avatar;
        this.country = country;
    }

    public Landscape(String name, String content, String image, Country country) {
        this.name = name;
        this.content = content;
        this.image = image;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}

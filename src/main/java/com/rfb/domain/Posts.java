package com.rfb.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "posts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "image")
    private String image;

    @ManyToOne
    private Paper paper;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {return body;}

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() { return image; }

    public void setImage(String image) {
        this.image = image;
    }
    public Paper getPaper() {
        return paper;
    }

    public Posts paper(Paper paper) {
        this.paper = paper;
        return this;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RfbEventAttendance rfbEventAttendance = (RfbEventAttendance) o;
        if (rfbEventAttendance.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rfbEventAttendance.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Posts{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}

package com.rfb.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "paper")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paper_name")
    private String paperName;

    @Column(name = "attendance_date")
    private LocalDate attendanceDate;

    @ManyToOne
    private RfbEvent rfbEvent;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "paper")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Posts> posts1 = new HashSet<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPaperName() { return paperName;}
    public void setPaperName(String paperName) { this.paperName = paperName; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public Paper attendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
        return this;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public RfbEvent getRfbEvent() {
        return rfbEvent;
    }

    public Paper rfbEvent(RfbEvent rfbEvent) {
        this.rfbEvent = rfbEvent;
        return this;
    }

    public void setRfbEvent(RfbEvent rfbEvent) {
        this.rfbEvent = rfbEvent;
    }

    public User getUser() {
        return user;
    }

    public Paper user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User rfbUser) {
        this.user = rfbUser;
    }


    public Set<Posts> getPosts1() {
        return posts1;
    }

    public Paper posts1(Set<Posts> posts1) {
        this.posts1 = posts1;
        return this;
    }

    public Paper addPosts(Posts posts) {
        this.posts1.add(posts);
        posts.setPaper(this);
        return this;
    }

    public Paper removePosts(Posts posts) {
        this.posts1.remove(posts);
        posts.setPaper(null);
        return this;
    }

    public void setPosts(Set<Posts> posts1) {
        this.posts1 = posts1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Paper paper = (Paper) o;
        if (paper.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paper.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Paper{" +
            "id=" + getId() +
            ", attendanceDate='" + getAttendanceDate() + "'" +
            "}";
    }

}

package com.rfb.service.dto;

import com.rfb.domain.Paper;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class PostsDTO implements Serializable {

    private Long id;

    private String title;

    private String body;

    private Image image;

    private PaperDTO paperDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public PaperDTO getPaperDTO() { return paperDTO; }
    public void setPaperDTO(PaperDTO paperDTO) {
        this.paperDTO = paperDTO;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PostsDTO postsDTO = (PostsDTO) o;
        if(postsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), postsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PostsDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }



}

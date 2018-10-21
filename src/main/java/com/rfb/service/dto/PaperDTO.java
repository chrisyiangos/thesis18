package com.rfb.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PaperDTO implements Serializable {
    private Long id;

    private String paperName;

    private LocalDate attendanceDate;

    private RfbEventDTO rfbEventDTO;

    private UserDTO userDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) { this.paperName = paperName; }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public RfbEventDTO getRfbEventDTO() {
        return rfbEventDTO;
    }

    public void setRfbEventDTO(RfbEventDTO rfbEventDTO) {
        this.rfbEventDTO = rfbEventDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaperDTO paperDTO = (PaperDTO) o;
        if(paperDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paperDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaperDTO{" +
            "id=" + getId() +
            ", attendanceDate='" + getAttendanceDate() + "'" +
            "}";
    }
}

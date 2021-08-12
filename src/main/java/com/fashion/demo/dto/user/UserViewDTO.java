package com.fashion.demo.dto.user;

import com.fashion.demo.Enum.RoleName;
import com.fashion.demo.Enum.UserGender;
import java.util.*;

public class UserViewDTO {

    private long id;
    private String name;
    private String email;
    private UserGender gender;
    private String photoPath;
    private String username;
    private Date birthday;
    private com.fashion.demo.Enum.status status;
    private Date createdDate;
    private RoleName role;

    public UserViewDTO() {
    }

    public UserViewDTO(long id, String name, String email, UserGender gender, String photoPath, String username, Date birthday, com.fashion.demo.Enum.status status, Date createdDate, RoleName role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.photoPath = photoPath;
        this.username = username;
        this.birthday = birthday;
        this.status = status;
        this.createdDate = createdDate;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public com.fashion.demo.Enum.status getStatus() {
        return status;
    }

    public void setStatus(com.fashion.demo.Enum.status status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "userViewDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", photoPath='" + photoPath + '\'' +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", role=" + role +
                '}';
    }
}

package com.shoppingcart.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoppingcart.demo.Enum.UserGender;
import com.shoppingcart.demo.Enum.UserStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class AdminEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;

    @Column(length = 500)
    private String firstName;
    @Column(length = 500)
    private String lastName;
    @Column(unique = true, length = 100)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserGender gender;
    @Column(name = "photo_path")
    private String photoPath;
    @Column(name = "username", nullable = false)
    private String username;
    @Column
    private Date birthday;
    @Column
    private UserStatus status;
    @Column(nullable = false)
    private String password;

    public AdminEntity(long adminId, String firstName, String lastName, String email, UserGender gender, String photoPath, String username, Date birthday, UserStatus status, String password) {
        this.adminId = adminId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.photoPath = photoPath;
        this.username = username;
        this.birthday = birthday;
        this.status = status;
        this.password = password;
    }
}

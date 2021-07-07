package com.shoppingcart.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoppingcart.demo.Enum.UserGender;
import com.shoppingcart.demo.Enum.UserStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tbl_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

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
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:MM:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;




}

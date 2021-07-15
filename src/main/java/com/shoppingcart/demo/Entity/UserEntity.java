package com.shoppingcart.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shoppingcart.demo.Enum.UserGender;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tbl_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(length = 500)
    private String name;
    @Column(unique = true, length = 100)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserGender gender;
    @Column(name = "photo_path")
    private String photoPath;
//    @Column(name = "username", nullable = false)
    @Column
    private String username;
    @Column
    private Date birthday;
    @Column
    private com.shoppingcart.demo.Enum.status status;
    @Column(nullable = false)
    private String password;
    @Column
    @JsonFormat(pattern = "dd-MM-yyyy HH:MM:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OderEntity> userOrders = new ArrayList<>();


    public UserEntity() {

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public com.shoppingcart.demo.Enum.status getStatus() {
        return status;
    }

    public void setStatus(com.shoppingcart.demo.Enum.status status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserEntity other = (UserEntity) obj;
        if (id != other.id)
            return false;
        return true;
    }

}

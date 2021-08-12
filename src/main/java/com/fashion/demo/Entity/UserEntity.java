package com.fashion.demo.Entity;

import com.fashion.demo.Enum.RoleName;
import com.fashion.demo.Enum.status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fashion.demo.Enum.UserGender;

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
    @Enumerated(EnumType.STRING)
    private com.fashion.demo.Enum.status status;
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

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private RoleName role;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OderEntity> userOrders = new ArrayList<>();


    public UserEntity() {

    }

    public UserEntity(long id, String name, String email, UserGender gender, String photoPath, String username, Date birthday, com.fashion.demo.Enum.status status, String password, Date createdDate, Set<RoleEntity> roles, RoleName role, List<OderEntity> userOrders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.photoPath = photoPath;
        this.username = username;
        this.birthday = birthday;
        this.status = status;
        this.password = password;
        this.createdDate = createdDate;
        this.roles = roles;
        this.role = role;
        this.userOrders = userOrders;
    }

    public UserEntity(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public List<OderEntity> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<OderEntity> userOrders) {
        this.userOrders = userOrders;
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

    public com.fashion.demo.Enum.status getStatus() {
        return status;
    }

    public void setStatus(com.fashion.demo.Enum.status status) {
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

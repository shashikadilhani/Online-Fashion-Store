package com.fashion.demo.Entity;

import com.fashion.demo.Enum.RoleName;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

//import org.springframework.data.annotation.Id;

@Entity
@Table(name = "tbl_roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "role_id")
    private long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public RoleEntity() {

    }

    public RoleEntity(RoleName name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

}

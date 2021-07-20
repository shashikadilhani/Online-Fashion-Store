package com.fashion.demo.dto.user;

import java.util.List;

public class UserListDTO {

    private List<UserDTO> users;

    public UserListDTO(List<UserDTO> users) {
        this.users = users;
    }

    public UserListDTO() {
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserListDTO{" +
                "users=" + users +
                '}';
    }
}

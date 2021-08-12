package com.fashion.demo.dto.request;

import javax.validation.constraints.NotBlank;

public class UserLoginReqDTO {
    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

    public UserLoginReqDTO() {
    }

    public UserLoginReqDTO(@NotBlank String usernameOrEmail, @NotBlank String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginReqDTO{" +
                "usernameOrEmail='" + usernameOrEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

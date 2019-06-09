package com.joe.joeorganizer.security;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class loginParams {

    @NotEmpty(message = "Email is required!")
    @NotNull(message = "Email is required!")
    private String email;
    @NotEmpty(message = "password is required!")
    @NotNull(message = "password is required!")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

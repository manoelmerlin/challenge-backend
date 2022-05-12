package com.backendchallenge.challenge.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserDto implements Serializable {
    @NotEmpty(message="name is required")
    private String name;
    @NotEmpty(message="email is required")
    @NotBlank
    @Email(message = "Invalid email")
    private String email;

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
}

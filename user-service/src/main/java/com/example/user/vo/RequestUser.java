package com.example.user.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {
    @NotNull(message = "email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    private String email;

    @NotNull(message = "name cannot be null")
    @Size(min = 2, message = "name not be less than two characters")
    private String name;

    @NotNull(message = "password cannot be null")
    @Size(min = 6, message = "password not be less than six characters")
    private String pwd;
}

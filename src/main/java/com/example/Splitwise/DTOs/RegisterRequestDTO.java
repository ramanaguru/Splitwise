package com.example.Splitwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    private String name;
    private String email;
    private String password;
}

package com.example.Splitwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequestDTO {
    private Long Id;
    private String name;
    private String password;
    private String email;

}

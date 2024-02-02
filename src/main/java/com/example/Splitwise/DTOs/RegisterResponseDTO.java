package com.example.Splitwise.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponseDTO {
    private long Id;
    private String message;
    private ResponseStatus status;


}

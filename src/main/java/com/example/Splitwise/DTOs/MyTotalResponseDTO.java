package com.example.Splitwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyTotalResponseDTO {


    private String message;
    private ResponseStatus status;
    private Long MyTotal;

}

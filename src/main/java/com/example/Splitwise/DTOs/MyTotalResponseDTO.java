package com.example.Splitwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyTotalResponseDTO {

    private Long UserId;
    private String message;
    private ResponseStatus status;

}

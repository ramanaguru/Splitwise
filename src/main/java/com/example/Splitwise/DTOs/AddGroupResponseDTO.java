package com.example.Splitwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGroupResponseDTO {
    private Long groupId;
    private String message;
    private ResponseStatus status;
}

package com.example.Splitwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddExpenseResponseDTO {
    private String message;
    private ResponseStatus status;
}

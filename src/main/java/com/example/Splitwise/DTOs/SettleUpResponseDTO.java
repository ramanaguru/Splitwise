package com.example.Splitwise.DTOs;

import com.example.Splitwise.Services.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpResponseDTO {
    private String message;

    private ResponseStatus responseStatus;

    private List<Transaction> transactions;
}

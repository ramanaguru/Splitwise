package com.example.Splitwise.Services;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Long Amount;
    private Long fromUser;
    private Long ToUser;

    public Transaction(Long Amount, Long fromUser, Long ToUser){
        this.Amount = Amount;
        this.fromUser = fromUser;
        this.ToUser = ToUser;
    }

}

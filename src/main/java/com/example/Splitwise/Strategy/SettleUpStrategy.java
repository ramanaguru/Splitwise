package com.example.Splitwise.Strategy;

import com.example.Splitwise.Services.Transaction;
import com.example.Splitwise.models.ExpenseUser;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUp(List<ExpenseUser> expenseUsers);
}

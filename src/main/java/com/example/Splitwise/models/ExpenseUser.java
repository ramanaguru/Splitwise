package com.example.Splitwise.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExpenseUser extends BaseModel{
    @ManyToOne
    private User user;

    @ManyToOne
    private Expense expense;

    private long amount;

    @Enumerated(EnumType.ORDINAL)
    private UserExpenseType userExpenseType;



    public ExpenseUser(User user, Expense expense, long amount, UserExpenseType userExpenseType) {
        this.user = user;
        this.expense = expense;
        this.amount = amount;
        this.userExpenseType = userExpenseType;
    }

    public ExpenseUser() {

    }
}

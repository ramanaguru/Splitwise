package com.example.Splitwise.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Expense extends BaseModel{
    private Long amount;
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private ExpenseType ExpenseType;

    @ManyToOne
    private User createdBy;
    public Expense(User createdBy,Long amount, String description, ExpenseType expenseType) {
        this.createdBy = createdBy;
        this.amount = amount;
        this.description = description;
        ExpenseType = expenseType;
    }

    public Expense(){

    }
}

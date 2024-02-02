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


/*
    The ExpenseUser class is a model class that represents the relationship between a user and an expense in the application.
    It has a user, an expense, an amount, and a userExpenseType.

    The user and expense attributes are of type User and Expense, and the amount attribute is a long.
    The userExpenseType attribute is an enumeration of type UserExpenseType, which represents the type of expense that the user has.

    The class is annotated with @Entity to indicate that it is a JPA entity, and the user and expense attributes are annotated with @ManyToOne to specify the type of relationship between the user and the expense.

    The amount attribute is not annotated, which means that it is a basic attribute that is persisted as a column in the database.

    The userExpenseType attribute is annotated with @Enumerated(EnumType.ORDINAL) to specify that the enumeration should be persisted as an ordinal value in the database.

    The class also has getter and setter methods for the attributes, and it has a constructor that takes the user, expense, amount, and userExpenseType as arguments.

    The ExpenseUser class is used to represent the relationship between a user and an expense in the application,
    and it is used in the application's logic to calculate the user's share of the expense.





 */
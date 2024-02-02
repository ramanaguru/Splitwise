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


/*
    The Expense class is a model class that represents an expense in the application.
    It has an amount, a description, an expenseType, and a createdBy attribute.

    The amount attribute is a long, the description attribute is a string, and the expenseType attribute is an enumeration of type ExpenseType,
    which represents the type of expense.

    The createdBy attribute is a user, and it represents the user who created the expense.

    The class is annotated with @Entity to indicate that it is a JPA entity, and the amount, description, and expenseType attributes are not annotated,
    which means that they are basic attributes that are persisted as columns in the database.

    The createdBy attribute is annotated with @ManyToOne to specify the type of relationship between the expense and the user who created it.

    The class also has getter and setter methods for the attributes, and it has a constructor that takes the createdBy, amount, description, and expenseType as arguments.

    The Expense class is used to represent an expense in the application, and it is used in the application's logic to calculate the user's share of the expense.




 */
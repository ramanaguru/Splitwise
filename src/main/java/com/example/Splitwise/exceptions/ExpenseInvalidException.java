package com.example.Splitwise.exceptions;

import org.springframework.stereotype.Component;


public class ExpenseInvalidException extends Exception{
    public ExpenseInvalidException(){
        super("Invalid Expense");
    }


}

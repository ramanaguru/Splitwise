package com.example.Splitwise.exceptions;

public class AdminIdInvalidException extends Exception{
    public AdminIdInvalidException(){
        super("AdminID is invalid. Please enter a valid AdminID.");
    }
}

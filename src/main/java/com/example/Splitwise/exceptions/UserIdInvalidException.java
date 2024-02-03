package com.example.Splitwise.exceptions;

public class UserIdInvalidException extends Exception{
    public UserIdInvalidException(){
        super("User ID is invalid. Please try with different userId.");
    }
}

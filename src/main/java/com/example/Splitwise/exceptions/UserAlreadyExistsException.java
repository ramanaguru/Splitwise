package com.example.Splitwise.exceptions;

public class UserAlreadyExistsException extends  Exception{
    public UserAlreadyExistsException(){
        super("User with email  is already registered.  Please try with different email.");
    }
}

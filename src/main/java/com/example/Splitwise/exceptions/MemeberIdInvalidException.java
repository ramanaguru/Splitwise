package com.example.Splitwise.exceptions;

public class MemeberIdInvalidException extends Exception{
    public MemeberIdInvalidException(){
        super("Member Id is invalid. Please try with different member id.");
    }
}

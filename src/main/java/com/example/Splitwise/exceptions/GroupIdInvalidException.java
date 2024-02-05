package com.example.Splitwise.exceptions;

public class GroupIdInvalidException extends Exception{
    public GroupIdInvalidException(){
        super("Group Id is invalid. Please try with different group id.");
    }
}

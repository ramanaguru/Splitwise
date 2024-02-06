package com.example.Splitwise.exceptions;

public class MemberAlreadyInGroupException extends Exception{
    public MemberAlreadyInGroupException(){
        super("Member is already in the group. Please enter a valid memberId.");
    }
}

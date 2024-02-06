package com.example.Splitwise.exceptions;

public class DuplicateGroupException extends Exception{
    public DuplicateGroupException(){

        super("Group with this name already exists");
    }

}

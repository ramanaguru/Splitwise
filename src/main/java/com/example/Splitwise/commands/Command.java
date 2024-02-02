package com.example.Splitwise.commands;

public interface Command {
    void execute(String inputCommand);
    boolean matches(String inputCommand);
}

package com.example.Splitwise.commands;

import com.example.Splitwise.DTOs.RegisterRequestDTO;
import com.example.Splitwise.DTOs.RegisterResponseDTO;
import com.example.Splitwise.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterCommand implements Command {
    UserController userController;

    @Autowired
    public RegisterCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String inputCommand) {
        String[] command = inputCommand.split(" ");
        return command[0].equals(CommandKeywords.register); // check if the first word is register
    }


    @Override
    public void execute(String inputCommand) {
        String[] command = inputCommand.split(" "); // split the input command

        RegisterRequestDTO request = new RegisterRequestDTO();
        request.setName(command[1]);        // set the name
        request.setEmail(command[2]);       // set the email
        request.setPassword(command[3]);    // set the password

        RegisterResponseDTO response = userController.register(request); // call the register method of the userController
        System.out.println(response.getMessage()); // print the response message
    }
}

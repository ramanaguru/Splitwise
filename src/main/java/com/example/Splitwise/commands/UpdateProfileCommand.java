package com.example.Splitwise.commands;


import com.example.Splitwise.DTOs.UpdateProfileRequestDTO;
import com.example.Splitwise.DTOs.UpdateProfileResponseDTO;
import com.example.Splitwise.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateProfileCommand implements Command{
    UserController userController;

    @Autowired
    public UpdateProfileCommand(UserController userController){
        this.userController = userController;
    }

    @Override
    public boolean matches(String inputCommand){
        String[] command = inputCommand.split(" ");
        return command[0].equals(CommandKeywords.updateprofile); // check if the first word is updateprofile
    }
    @Override
    public void execute(String inputCommand){
        String[] command = inputCommand.split(" ");

        UpdateProfileRequestDTO request = new UpdateProfileRequestDTO();
        request.setName(command[1]);        // set the name
        request.setEmail(command[2]);       // set the email
        request.setPassword(command[3]);    // set the password

        UpdateProfileResponseDTO response = userController.updateprofile(request); // call the updateProfile method of the userController
        System.out.println(response.getMessage()); // print the response message
    }
}

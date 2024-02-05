package com.example.Splitwise.commands;

import com.example.Splitwise.DTOs.AddGroupRequestDTO;
import com.example.Splitwise.DTOs.AddGroupResponseDTO;
import com.example.Splitwise.controllers.GroupController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Addgroup implements Command{

    GroupController groupController;

    @Autowired
    public Addgroup(GroupController groupController) {
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String inputCommand) {
        String[] command = inputCommand.split(" ");
        return command.length == 3 &&  command[1].equals(CommandKeywords.addgroup);
    }

    @Override
    public void execute(String inputCommand){
        String command[] = inputCommand.split(" ");
        AddGroupRequestDTO request = new AddGroupRequestDTO();
        request.setUserId(Long.parseLong(command[0]));
        request.setGroupName(command[2]);
        AddGroupResponseDTO response = groupController.addGroup(request);
        System.out.println(response.getMessage());
    }

}

package com.example.Splitwise.commands;

import com.example.Splitwise.DTOs.AddGroupRequestDTO;
import com.example.Splitwise.DTOs.AddMemberRequestDTO;
import com.example.Splitwise.DTOs.AddMemberResponseDTO;
import com.example.Splitwise.controllers.GroupController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddMember implements Command{

    GroupController groupController;

    @Autowired
    public AddMember(GroupController groupController){
        this.groupController = groupController;
    }

    @Override
    public boolean matches(String inputCommand){
            String command[] = inputCommand.split(" ");
            return command.length == 4 && command[1].equals(CommandKeywords.addmember) ;
    }

    @Override
    public void execute(String inputCommand){
            String command[] = inputCommand.split(" ");
            AddMemberRequestDTO request = new AddMemberRequestDTO();
            request.setAdminId(Long.parseLong(command[0]));
            request.setGroupId(Long.parseLong(command[2]));
            request.setMemberId(Long.parseLong(command[3]));
            AddMemberResponseDTO response = groupController.addMember(request);
            System.out.println(response.getMessage());

    }


}

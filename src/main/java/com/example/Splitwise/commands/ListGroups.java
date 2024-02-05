package com.example.Splitwise.commands;

import com.example.Splitwise.DTOs.ListGroupsRequestDTO;
import com.example.Splitwise.DTOs.ListGroupsResponseDTO;
import com.example.Splitwise.controllers.GroupController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListGroups implements Command {

   GroupController groupController;

   @Autowired
   public ListGroups(GroupController groupController){
       this.groupController = groupController;
   }

   public boolean matches(String inputCommand){
       String[] command = inputCommand.split(" ");
       return command[0].equals(CommandKeywords.listgroups);
   }

   public void execute(String inputCommand){

       String[] command = inputCommand.split(" ");
       ListGroupsRequestDTO request = new ListGroupsRequestDTO();
       request.setUserId(Long.parseLong(command[1]));
       ListGroupsResponseDTO response = groupController.listGroups(request);
       System.out.println(response.getMessage());

   }



}

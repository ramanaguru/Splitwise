package com.example.Splitwise.controllers;

import com.example.Splitwise.DTOs.*;
import com.example.Splitwise.Services.GroupService;
import com.example.Splitwise.exceptions.*;
import com.example.Splitwise.models.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {
    GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }


    public AddGroupResponseDTO addGroup(AddGroupRequestDTO request){

        Group group ;
        AddGroupResponseDTO response = new AddGroupResponseDTO();
        try{
            group = groupService.addGroup(request.getUserId(), request.getGroupName());
        }catch(UserIdInvalidException | DuplicateGroupException e){
            response.setMessage(e.getMessage());
            response.setStatus(ResponseStatus.FAILURE);
            return response;
        }

        response.setGroupId(group.getId());
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("Group added successfully with Id : " + group.getId());
        return response;
    }

    public AddMemberResponseDTO addMember(AddMemberRequestDTO request){
        AddMemberResponseDTO response = new AddMemberResponseDTO();

        try{
            groupService.addMember((request.getAdminId()), request.getGroupId(), request.getMemberId());
        }
        catch( MemeberIdInvalidException | MemberAlreadyInGroupException |
               GroupIdInvalidException | AdminIdInvalidException e){
            response.setMessage(e.getMessage());
            response.setStatus(ResponseStatus.FAILURE);
            return response;
        }
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("Member added successfully");
        return  response;

    }

    public ListGroupsResponseDTO listGroups(ListGroupsRequestDTO request){
        ListGroupsResponseDTO response = new ListGroupsResponseDTO();

        try{
            groupService.listGroups(request.getUserId());
        }catch(UserIdInvalidException e){
            response.setMessage("UserId is invalid . Please enter a valid userId");
            response.setStatus(ResponseStatus.FAILURE);
            return response;
        }

        response.setMessage("Groups listed successfully");
        response.setStatus(ResponseStatus.SUCCESS);

        return response;
    }



}

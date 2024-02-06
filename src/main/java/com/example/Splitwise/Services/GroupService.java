package com.example.Splitwise.Services;

import com.example.Splitwise.DTOs.AddGroupRequestDTO;
import com.example.Splitwise.DTOs.AddGroupResponseDTO;
import com.example.Splitwise.Repository.GroupRepository;
import com.example.Splitwise.Repository.UserRepository;
import com.example.Splitwise.exceptions.*;
import com.example.Splitwise.models.Group;
import com.example.Splitwise.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    UserRepository userRepository;
    GroupRepository groupRepository;

    @Autowired
    public GroupService(UserRepository userRepository , GroupRepository groupRepository){
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public Group addGroup(Long UserId, String GroupName)throws UserIdInvalidException , DuplicateGroupException {
        Optional<User> userOptional = userRepository.findById(UserId);
        if(userOptional.isEmpty()){
            throw new UserIdInvalidException();
        }

        User user = userOptional.get();

        List<Group> userGroups = groupRepository.findAllByAdmin(user);

        Group group = new Group();

        group.setName(GroupName);
        group.setAdmin(user);

        List<User> users = new ArrayList<>();
        users.add(user);
        group.setUsers(users);

        return groupRepository.save(group);

    }

    public void addMember(Long AdminId, Long GroupId, Long MemberId) throws GroupIdInvalidException , AdminIdInvalidException, MemeberIdInvalidException, MemberAlreadyInGroupException{
        Optional<Group>groupsOptional = groupRepository.findById(GroupId);
        if(groupsOptional.isEmpty()){
            throw new GroupIdInvalidException();
        }

        Group group = groupsOptional.get();

        if(!group.getAdmin().getId().equals(AdminId)){
            throw new AdminIdInvalidException();
        }

        Optional<User> membersOptional  = userRepository.findById(MemberId); // check if user is present in the database or not

        if(membersOptional.isEmpty()){
            // if user is not present in the database then throw exception
            throw new MemeberIdInvalidException();
        }

        User member = membersOptional.get(); // get the user from the optional

        List<User>members = group.getUsers(); // get the list of users from the group


        for(User u : members){
            if(u.getId().equals(member.getId())){
                throw new MemberAlreadyInGroupException(); // if the user is already present in the group then throw exception
            }
        }

        members.add(member); // add the member to the list of users

         groupRepository.save(group); // save the group

    }

    public void listGroups(Long UserId) throws UserIdInvalidException{
         Optional<User>userOptional = userRepository.findById(UserId); // check if the user is present in the database or not

         if(userOptional.isEmpty()){
             throw new UserIdInvalidException(); // if the user is not present in the database then throw exception
         }

         User user = userOptional.get();

         List<Group>groups = groupRepository.findAll(); // get all the groups from the database and store it in the list of groups


         boolean flag = false; // flag to check if the user is present in any of the groups or not

        for(Group group : groups){
            List<User>users  = group.getUsers(); // get the list of users from the group

            for(User u : users){
                if(u.getId().equals(user.getId())){
                    flag = true; // if the user is present in any of the groups then set the flag to true
                    System.out.println( "Group ID :  "  + group.getId() + "  ||   Group Name : " + group.getName() ); // print the  ID and name of the group
                    break;
                }
            }
        }

        if(!flag){ // if the user is not present in any of the groups then print the message (flag == false)
            System.out.println("OOPS , You are not present in any of the groups ");
        }
    }

}

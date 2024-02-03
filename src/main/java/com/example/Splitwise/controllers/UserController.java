package com.example.Splitwise.controllers;

import com.example.Splitwise.DTOs.*;
import com.example.Splitwise.exceptions.UserAlreadyExistsException;
import com.example.Splitwise.Services.UserService;
import com.example.Splitwise.exceptions.UserIdInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.Splitwise.models.User;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO){
        User user;

        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();

        try{
            user = userService.register(
                    registerRequestDTO.getName(),
                    registerRequestDTO.getPassword(),
                    registerRequestDTO.getEmail()) ;// call the register method of the userService
        } catch(UserAlreadyExistsException e){
            registerResponseDTO.setMessage("User already exists with email");
            registerResponseDTO.setStatus(ResponseStatus.FAILURE);
            return registerResponseDTO;
        }
        registerResponseDTO.setId(user.getId());
        registerResponseDTO.setStatus(ResponseStatus.SUCCESS);
        registerResponseDTO.setMessage("User registered successfully with Id : " + user.getId() );

        return registerResponseDTO;
    }

    public UpdateProfileResponseDTO updateprofile(UpdateProfileRequestDTO request){
        UpdateProfileResponseDTO response = new UpdateProfileResponseDTO();

        try{
            userService.updateProfile(request.getId(), request.getName(), request.getPassword(), request.getEmail()); // call the updateProfile method of the userService

        } catch(UserIdInvalidException e){
            response.setMessage("User does not exist with id");
            response.setStatus(ResponseStatus.FAILURE);
            return response;
        }

        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("UserProfile  updated successfully." );

        return response; // return the response
    }
}

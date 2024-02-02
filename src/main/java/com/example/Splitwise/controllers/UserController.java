package com.example.Splitwise.controllers;

import com.example.Splitwise.DTOs.RegisterRequestDTO;
import com.example.Splitwise.DTOs.RegisterResponseDTO;
import com.example.Splitwise.DTOs.ResponseStatus;
import com.example.Splitwise.exceptions.UserAlreadyExistsException;
import com.example.Splitwise.Services.UserService;
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
}

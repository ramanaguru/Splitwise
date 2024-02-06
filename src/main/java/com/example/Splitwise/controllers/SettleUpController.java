package com.example.Splitwise.controllers;

import com.example.Splitwise.DTOs.ResponseStatus;
import com.example.Splitwise.DTOs.SettleUpRequestDTO;
import com.example.Splitwise.DTOs.SettleUpResponseDTO;
import com.example.Splitwise.Services.SettleUpService;
import com.example.Splitwise.Services.Transaction;
import com.example.Splitwise.exceptions.GroupIdInvalidException;
import com.example.Splitwise.exceptions.MemeberIdInvalidException;
import com.example.Splitwise.exceptions.UserIdInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SettleUpController {

    SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleUpResponseDTO settleUp(SettleUpRequestDTO request){

        SettleUpResponseDTO response = new SettleUpResponseDTO();
        List<Transaction> transactions = new ArrayList<>();

        try{
           if(request.getGroupId() != null){
               //if group id is found that means (group!= null) then settle up group
               transactions = settleUpService.settlUpGroup(request.getUserId() , request.getGroupId());
           }
           else{
               //if group id is not found that means (group == null) then settle up user
               transactions = settleUpService.settleUpUser(request.getUserId());
           }
        }
        catch(UserIdInvalidException | GroupIdInvalidException | MemeberIdInvalidException e){
            response.setMessage(e.getMessage());
            response.setResponseStatus(ResponseStatus.FAILURE);
            return response;

        }
        response.setResponseStatus(ResponseStatus.SUCCESS);
        response.setMessage("Settle Up Successful");
        return response; 

    }

}

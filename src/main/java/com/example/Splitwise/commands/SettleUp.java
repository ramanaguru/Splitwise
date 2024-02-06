package com.example.Splitwise.commands;

import com.example.Splitwise.DTOs.SettleUpRequestDTO;
import com.example.Splitwise.DTOs.SettleUpResponseDTO;
import com.example.Splitwise.Services.Transaction;
import com.example.Splitwise.controllers.SettleUpController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettleUp implements Command{
    SettleUpController settleUpController;
    @Autowired
    public void settleUp(SettleUpController settleUpController){
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String inputCommand){
        String[] command = inputCommand.split(" ");
        return command[0].equals(CommandKeywords.settleup);
    }

    @Override
    public void execute(String inputCommand){
        String[] command = inputCommand.split(" ");
        SettleUpRequestDTO request = new SettleUpRequestDTO();
        try{
            request.setUserId(Long.parseLong(command[1]));
            if(command.length == 3){
                request.setGroupId(Long.parseLong(command[2]));
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid Input");
            return;
        }
        SettleUpResponseDTO response = settleUpController.settleUp(request);
        System.out.println(response.getMessage());

        if (response.getResponseStatus().equals("SUCCESS")){
            if(response.getTransactions().isEmpty()){
                System.out.println("No Transactions required ");
                return;
            }
            for(Transaction transaction : response.getTransactions()){
                System.out.println(transaction.getFromUser() + " needs to pay " + transaction.getToUser() + " " + transaction.getAmount());

            }

        }
    }


}

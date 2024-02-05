package com.example.Splitwise.commands;

import com.example.Splitwise.DTOs.MyTotalRequestDTO;
import com.example.Splitwise.DTOs.MyTotalResponseDTO;
import com.example.Splitwise.controllers.ExpenseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyTotal implements Command{
    ExpenseController expenseController;

    @Autowired
    public MyTotal(ExpenseController expenseController){
        this.expenseController = expenseController;
    }
    public boolean matches(String inputCommand){
        String[] command = inputCommand.split(" ");
        return command.length == 2  && command[1].equals(CommandKeywords.mytotal);
    }


    @Override
    public void execute(String inputCommand){
            String[] command = inputCommand.split(" ");
            MyTotalRequestDTO request = new MyTotalRequestDTO();
            request.setUserId(Long.parseLong(command[0]));
            MyTotalResponseDTO response = expenseController.myTotal(request);
            System.out.println(response.getMessage());

    }

}

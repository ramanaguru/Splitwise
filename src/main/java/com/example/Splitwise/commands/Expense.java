package com.example.Splitwise.commands;

import com.example.Splitwise.DTOs.AddExpenseRequestDTO;
import com.example.Splitwise.DTOs.AddExpenseResponseDTO;
import com.example.Splitwise.controllers.ExpenseController;
import com.example.Splitwise.exceptions.ExpenseInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Expense implements Command{
        ExpenseController expenseController;

        @Autowired
        public Expense(ExpenseController expenseController){
            this.expenseController = expenseController;
        }

        @Override
        public boolean matches(String inputCommand){
            String[] command = inputCommand.split(" ");
            return command[1].equals(CommandKeywords.expense);
        }

        @Override
        public void execute(String inputCommand){

            AddExpenseRequestDTO request = new AddExpenseRequestDTO();

            try{
                request = ExpenseInputDTOConverter.parseExpenseInput(inputCommand);
            }
            catch(ExpenseInvalidException e){
                System.out.println(e.getMessage());
                return;
            }

            AddExpenseResponseDTO response = expenseController.addExpense(request);
            System.out.println(response.getMessage());


        }


}

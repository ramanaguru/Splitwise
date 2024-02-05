package com.example.Splitwise.controllers;

import com.example.Splitwise.DTOs.AddExpenseRequestDTO;
import com.example.Splitwise.DTOs.AddExpenseResponseDTO;
import com.example.Splitwise.DTOs.ResponseStatus;
import com.example.Splitwise.Services.ExpenseService;
import com.example.Splitwise.exceptions.GroupIdInvalidException;
import com.example.Splitwise.exceptions.MemeberIdInvalidException;
import com.example.Splitwise.exceptions.UserIdInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExpenseController {

        ExpenseService expenseService;

        @Autowired
        public ExpenseController(ExpenseService expenseService){
            this.expenseService = expenseService;
        }

        public AddExpenseResponseDTO addExpense(AddExpenseRequestDTO request){
            AddExpenseResponseDTO response = new AddExpenseResponseDTO();
            try{
                if(request.getGroupId() == null){
                    expenseService.addExpenseUsers
                            (
                            request.getCreatedByUserId(), request.getUsers(), request.getPayType(),
                            request.getTotalAmount(), request.getPaidAmounts(), request.getSplitAmounts(),
                            request.getSplitType(), request.getDescription()
                            );


                }
                else{
                    expenseService.addExpenseGroup
                            (
                            request.getCreatedByUserId(), request.getGroupId() ,request.getUsers(), request.getPayType(),
                            request.getTotalAmount(), request.getPaidAmounts(), request.getSplitAmounts(),
                            request.getSplitType(), request.getDescription()
                            );
                }
            }
            catch(UserIdInvalidException | GroupIdInvalidException | MemeberIdInvalidException e){
                response.setMessage(e.getMessage());
                response.setStatus(ResponseStatus.FAILURE);
                return response;
            }

            response.setMessage("Expense added successfully");
            response.setStatus(ResponseStatus.SUCCESS);
            return response;

        }


}

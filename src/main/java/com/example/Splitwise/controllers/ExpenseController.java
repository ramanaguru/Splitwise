package com.example.Splitwise.controllers;

import com.example.Splitwise.DTOs.*;
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

        public MyTotalResponseDTO myTotal(MyTotalRequestDTO request){
            MyTotalResponseDTO response = new MyTotalResponseDTO();
            Long myTotal;

            try{
                myTotal = expenseService.myTotal(request.getUserId());
            }
            catch(UserIdInvalidException e){
                response.setMessage(e.getMessage());
                response.setStatus(ResponseStatus.FAILURE);
                return response;
            }

            if (myTotal > 0){
                response.setMessage("Reminder -> Your Total Amount yet to receive  " + myTotal);
            }
            else if(myTotal < 0){
                response.setMessage("Reminder -> Your Total Amount yet to pay " + myTotal);
            }
            else{
                response.setMessage("You are settled up with everyone");
            }

            response.setStatus(ResponseStatus.SUCCESS);
            response.setMyTotal(myTotal);

            return response;
        }


}

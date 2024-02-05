package com.example.Splitwise.commands;

import com.example.Splitwise.DTOs.AddExpenseRequestDTO;
import com.example.Splitwise.exceptions.ExpenseInvalidException;

import java.util.ArrayList;
import java.util.List;

public class ExpenseInputDTOConverter {


    // u1 Expense g1 iPay 1000 Equal Desc Wifi Bill
    // u1 Expense u2 u3 u4 iPay 1000 Equal Desc Last night dinner


    public static AddExpenseRequestDTO parseExpenseInput(String input) throws ExpenseInvalidException {

        AddExpenseRequestDTO request = new AddExpenseRequestDTO();

        try{
            String[] command = input.split(" ");

            if(command[0].charAt(0) != 'u' )throw new ExpenseInvalidException();
            request.setCreatedByUserId(Long.parseLong(command[0].substring(1) ) );
            List<Long>users = new ArrayList<>();

            int index = 2 ;

           if(command[index].charAt(0) == 'g'){
               request.setGroupId(Long.parseLong(command[index].substring(1)));
               index++;
           }

           else{
               users.add(request.getCreatedByUserId());
               while(command[index].charAt(0) == 'u'){
                   users.add(Long.parseLong(command[index].substring(1)));
                   index++;
               }
           }

           request.setUsers(users);  // setting users
           request.setPayType(PayType.valueOf(command[index])); // setting PayType


           if(request.getPayType().equals(PayType.MultiplePay) && request.getGroupId() != null){
               throw new ExpenseInvalidException();
           }

           index++;

           List<Long>paidAmounts = new ArrayList<>();

           if(request.getPayType().equals(PayType.iPay)){
               paidAmounts.add(Long.parseLong(command[index]));
               index++;
           }
           else{
               for(int i = 0; i < users.size(); i++){
                    paidAmounts.add(Long.parseLong(command[index]));
                   index++;
               }
           }

            // setting paidAmounts
            request.setPaidAmounts(paidAmounts);

            // setting totalAmount
            request.setTotalAmount(paidAmounts.stream().reduce(Long :: sum).get() ) ;

            request.setSplitType(SplitType.valueOf(command[index])); // setting SplitType

            index++;

            if(request.getGroupId() != null && !request.getSplitType().equals(SplitType.EQUAL)){
                // group and equal split is not allowed together in this application so throwing exception here
                throw new ExpenseInvalidException();
            }

            List<Long> splitAmounts = new ArrayList<>();

            if(!request.getSplitType().equals(SplitType.EQUAL)){
                // if split type is not equal then we need to set the split amounts
                for(int i = 0; i < users.size(); i++){
                    splitAmounts.add(Long.parseLong(command[index]));
                    index++;
                }
            }

            if(request.getSplitType().equals(SplitType.PERCENT)){
                // if split type is percent then we need to check if the sum of split amounts is 100 or not
               if(splitAmounts.stream().reduce(Long :: sum).get() != 100){
                   // if sum is not 100 then throwing exception
                   throw new ExpenseInvalidException();
               }

            }
            else if (request.getSplitType().equals(SplitType.EXACT)){
                if(splitAmounts.stream().reduce(Long :: sum).get().equals(request.getTotalAmount())){
                    // if split amounts sum is not equal to total amount then throwing exception
                    throw new ExpenseInvalidException();
                }
            }

            request.setSplitAmounts(splitAmounts); // setting split amounts

            if(!command[index].equals("DESC") ){
                // if the next word is not DESC then throwing exception
                throw new ExpenseInvalidException();
            }
            index++;




            request.setDescription(String.join(" ", command).substring((index))); //Refer line 139 for explanation

        }catch(ExpenseInvalidException |ArrayIndexOutOfBoundsException | IllegalArgumentException e){
            //ExpenseInvalidException is thrown when the input is invalid
            //ArrayIndexOutOfBoundsException is thrown when we try to access the index which is not present
            //IllegalArgumentException is thrown when Long.parseLong is not able to parse the string
            throw new ExpenseInvalidException();
        }



        return request;  // returning the request


    }



}


/*

String.join(" ", command): This part joins the elements of the command array into a single string, using a space (" ") as the separator.

.substring(index): This part takes a substring of the joined string, starting from the specified index (index). It means it extracts the portion of the string from index to the end.

request.setDescription(...): This sets the extracted substring as the description of an object referred to as request.

example :   u1 Expense u2 u3 u4 iPay 1000 Equal Desc Last night dinner

command =   [u1, Expense, u2, u3, u4, iPay, 1000, Equal, Desc, Last, night, dinner]
            then we join the command array using a space as the separator, which gives us "u1 Expense u2 u3 u4 iPay 1000 Equal Desc Last night dinner"
            then we take a substring of the joined string starting from index 8, which gives us "Last night dinner"
            then we set the extracted substring as the description of the request object





*/
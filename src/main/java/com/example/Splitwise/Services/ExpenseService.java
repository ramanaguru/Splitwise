package com.example.Splitwise.Services;

import com.example.Splitwise.Repository.ExpenseRepository;
import com.example.Splitwise.Repository.ExpenseUserRepository;
import com.example.Splitwise.Repository.GroupRepository;
import com.example.Splitwise.Repository.UserRepository;
import com.example.Splitwise.Strategy.SplitAmountStrategy;
import com.example.Splitwise.Strategy.SplitAmountStrategyFactory;
import com.example.Splitwise.commands.PayType;
import com.example.Splitwise.commands.SplitType;
import com.example.Splitwise.exceptions.GroupIdInvalidException;
import com.example.Splitwise.exceptions.MemeberIdInvalidException;
import com.example.Splitwise.exceptions.UserIdInvalidException;
import com.example.Splitwise.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    UserRepository userRepository;
    ExpenseRepository expenseRepository;
    GroupRepository groupRepository;
    ExpenseUserRepository expenseUserRepository;

    @Autowired
    public ExpenseService(UserRepository userRepository, ExpenseRepository expenseRepository, GroupRepository groupRepository, ExpenseUserRepository expenseUserRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.expenseUserRepository = expenseUserRepository;
    }

    public void addExpenseUsers(Long createdByUserId, List<Long> users, PayType payType, Long totalAmount,
                                List<Long> paidAmounts, List<Long>splitAmounts, SplitType splitType , String description)
                                throws UserIdInvalidException {


        User createdUser = fetchUserById(createdByUserId);
        List<User>paidUsers = new ArrayList<>();
        for(Long userId : users){
            User user = fetchUserById(userId);
            paidUsers.add(user);
        }

        Expense expense= new Expense(createdUser,totalAmount, description, ExpenseType.REAL);
        expenseRepository.save(expense);

      if(payType.equals(PayType.iPay)){
                expenseUserRepository.save(new ExpenseUser(createdUser, expense, totalAmount,UserExpenseType.PAID));
      }
      else{
          for(int i = 0; i < paidUsers.size(); i++){
              expenseUserRepository.save(new ExpenseUser(paidUsers.get(i), expense, paidAmounts.get(i), UserExpenseType.PAID));
          }
      }


      splitAmounts = SplitAmountStrategyFactory.getSplitAmountStrategy(splitType).splitAmount(users.size(),totalAmount, splitAmounts);

      for(int i =0; i < users.size(); i++){
          User user = fetchUserById(users.get(i));
          expenseUserRepository.save(new ExpenseUser(user, expense, splitAmounts.get(i), UserExpenseType.HAS_TO_PAY));
      }


    }

    public void addExpenseGroup(Long createdByUserId, Long GroupId, List<Long> users, PayType payType, Long totalAmount,
                                List<Long> paidAmounts, List<Long>splitAmounts, SplitType splitType ,
                                String description)
                                throws UserIdInvalidException , MemeberIdInvalidException, GroupIdInvalidException
    {




    }

    private User fetchUserById(Long UserId)throws UserIdInvalidException{
        Optional<User> userOptional = userRepository.findById(UserId);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else{
            throw new UserIdInvalidException();
        }
    }


}

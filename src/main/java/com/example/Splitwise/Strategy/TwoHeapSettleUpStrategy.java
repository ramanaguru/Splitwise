package com.example.Splitwise.Strategy;

import com.example.Splitwise.Repository.UserRepository;
import com.example.Splitwise.Services.Transaction;
import com.example.Splitwise.models.ExpenseUser;
import com.example.Splitwise.models.UserExpenseType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TwoHeapSettleUpStrategy implements SettleUpStrategy{

    @Autowired
    UserRepository userRepository;
    @Override
    public List<Transaction>settleUp(List<ExpenseUser> expenseUsers){
        HashMap<Long, Long> map = new HashMap<>();

        for(ExpenseUser expenseUser : expenseUsers){
            if(!map.containsKey(expenseUser)){
                map.put(expenseUser.getUser().getId(), 0L);
            }
            else{
                Long updatedAmount = expenseUser.getUserExpenseType().equals(UserExpenseType.PAID)
                                    ? map.get(expenseUser.getUser().getId()) - expenseUser.getAmount() : map.get(expenseUser.getUser().getId()) + expenseUser.getAmount();
                map.put(expenseUser.getUser().getId(), updatedAmount);
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap

        PriorityQueue<Pair> minHeap = new PriorityQueue<>();    // minHeap

        for(Long userId: map.keySet()){
            Long amount = map.get(userId);

            if(amount > 0){
                maxHeap.add(new Pair (userId , amount));
            }
            else if(amount < 0){
                minHeap.add(new Pair (userId , amount));
            }
        }

        while(!minHeap.isEmpty() && !maxHeap.isEmpty()){
            Pair paid = maxHeap.poll();  //Paid
            Pair owed = minHeap.poll();  //HasToPAY

            Long paidUserId = paid.getUserId();
            Long owedUserId = owed.getUserId();

            Long paidAmount = paid.getAmount();
            Long owedAmount = (-1) * owed.getAmount();

            if(paidAmount > owedAmount){
                transactions.add(new Transaction(owedAmount, paidUserId, owedUserId));
                maxHeap.add(new Pair(paidUserId, paidAmount - owedAmount));
            }
            else if(paidAmount < owedAmount){
                transactions.add(new Transaction(paidAmount, paidUserId, owedUserId));
                minHeap.add(new Pair(owedUserId, owedAmount - paidAmount));
            }
            else{
                transactions.add(new Transaction(paidAmount, paidUserId, owedUserId));
            }

        }
        return transactions;
    }
}


@Getter
@Setter
class Pair implements Comparable<Pair>{
    Long  UserId;
    Long Amount;

    public Pair(Long UserId, Long Amount){
        this.UserId = UserId;
        this.Amount = Amount;
    }


    @Override
    public int compareTo(Pair pair){
       return Long.compare(this.Amount, pair.Amount);
    }

}

/*
    The TwoHeapSettleUpStrategy class is a concrete implementation of the SettleUpStrategy interface that uses a two-heap approach to settle up expenses between users.

    The class is annotated with @Component to indicate that it is a Spring component, and it is autowired with the UserRepository to access user data.

    The settleUp method takes a list of ExpenseUser objects as input and returns a list of Transaction objects as output.

    The method first creates a HashMap to store the total amount owed by each user.

    It then iterates through the list of ExpenseUser objects and updates the total amount owed by each user based on the type of expense (PAID or OWED).

    After calculating the total amount owed by each user, the method creates two priority queues (maxHeap and minHeap)
    to store the users who have paid more than they owe and the users who owe more than they have paid, respectively.

    The method then iterates through the priority queues and settles up the expenses between the users by creating Transaction objects and adding them to the list of transactions.

    Finally, the method returns the list of transactions.

    The Pair class is a simple class that represents a pair of user ID and amount,
    and it is used to store the users and their total amounts owed in the priority queues.

    The class has a constructor that takes the user ID and amount as arguments, and it has a compareTo method that compares the amounts of two pairs.

    The Pair class is used in the TwoHeapSettleUpStrategy class to store the users and their total amounts owed in the priority queues.

    and it is used in the application's logic to settle up expenses between users.

*/







package com.example.Splitwise.Strategy;

import java.util.ArrayList;
import java.util.List;

public class EqualSplitAmountStrategy implements  SplitAmountStrategy{
    @Override
    public List<Long> splitAmount(int NoOfUsers, Long Amount, List<Long>splits){
        List<Long> splitAmounts = new ArrayList<>();
        for(int i = 0; i < NoOfUsers; i++){
            splitAmounts.add(Amount/NoOfUsers);
        }

        Long check = splitAmounts.stream().reduce(Long :: sum).get();
        if(check.equals(Amount)){
            return splitAmounts;
        }
        else{
            splitAmounts.set(0, splitAmounts.get(0) + (Amount - check));
        }

        return splitAmounts;
    }

}


/*

        Initialize variables:

    NoOfUsers = 3
    amount = 100L
    splitAmounts is an empty ArrayList

    Loop through each user (i from 0 to 2):

    Iteration 1: splitAmounts.add(100L / 3), so splitAmounts = [33]
    Iteration 2: splitAmounts.add(100L / 3), so splitAmounts = [33, 33]
    Iteration 3: splitAmounts.add(100L / 3), so splitAmounts = [33, 33, 33]

    Calculate check:

    check = splitAmounts.stream().reduce(Long::sum).get()
    check = 33 + 33 + 33 = 99

    Check if checkSplitSum equals amount (100):

    Since 99 is not equal to 100, adjust the first element in splitAmounts:

    splitAmounts.set(0, splitAmounts.get(0) + (amount - check))
    splitAmounts.set(0, 33 + (100 - 99))
    splitAmounts.set(0, 33 + 1)
    splitAmounts = [34, 33, 33]
    Return the final splitAmounts: [34, 33, 33]

    So, in this example, the strategy adjusts the first user's split amount to ensure that the total sum of split amounts
     is equal to the original amount. The final result is [34, 33, 33].


 */
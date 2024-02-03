package com.example.Splitwise.Strategy;

import java.util.ArrayList;
import java.util.List;

public class RatioSplitAmountStrategy implements SplitAmountStrategy{
    @Override
    public List<Long> splitAmount(int NoOfUsers, Long Amount, List<Long>splits){

       Long total = splits.stream().reduce(Long :: sum).get();
       List<Long> splitAmounts = new ArrayList<>();

       for(int i = 0; i < NoOfUsers; i++){
           splitAmounts.add((splits.get(i) * Amount) / total);
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

    splits = [1, 2, 3]
    amount = 100L

    Calculate totalRatio: 1 + 2 + 3 = 6

    splitAmounts is an empty ArrayList

    Loop through each ratio in splits:

    Calculate the split amount for each ratio: (split * amount) / totalRatio

    Iteration 1: (100 * 1) / 6 = 16
    Iteration 2: (100 * 2) / 6 = 33
    Iteration 3: (100 * 3) / 6 = 50

    Add these split amounts to splitAmounts: [16, 33, 50]

    Calculate check:

    check = splitAmounts.stream().reduce(Long::sum).get()
    check = 16 + 33 + 50 = 99

    Check if checkSplitSum equals amount (100):

    Since 99 is not equal to 100, adjust the first element in splitAmounts:

    splitAmounts.set(0, splitAmounts.get(0) + (amount - checkSplitSum))

    splitAmounts.set(0, 16 + (100 - 99))
    splitAmounts.set(0, 16 + 1)
    splitAmounts = [17, 33, 50]

    Return the final splitAmounts: [17, 33, 50]

    So, in this example, the RatioSplitAmountStrategy calculates split amounts based on ratios and adjusts the first split amount to ensure
    that the total sum matches the original amount.
    The final result is [17, 33, 50].



*/

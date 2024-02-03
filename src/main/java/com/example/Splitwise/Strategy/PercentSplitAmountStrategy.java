package com.example.Splitwise.Strategy;

import java.util.List;

public class PercentSplitAmountStrategy implements SplitAmountStrategy {

    @Override
    public List<Long> splitAmount(int NoOfUsers, Long Amount, List<Long>splits){
        List<Long> splitAmounts = splits;

        Long check = splitAmounts.stream().reduce(Long::sum).get();
        if(check.equals(Amount)){
            return splitAmounts;
        }
        else{
            splitAmounts.set(0,splitAmounts.get(0) + (Amount - check));
        }

        return  splitAmounts;
    }

}


/*

    Initialize variables:

    splits = [10, 20, 30]
    amount = 100L

    splitAmounts is an empty ArrayList

    Loop through each percentage in splits:

    Calculate the split amount for each percentage: (amount * split) / 100

    Iteration 1: (100 * 10) / 100 = 10
    Iteration 2: (100 * 20) / 100 = 20
    Iteration 3: (100 * 30) / 100 = 30

    Add these split amounts to splitAmounts: [10, 20, 30]

    Calculate check:

    check = splitAmounts.stream().reduce(Long::sum).get()
    check = 10 + 20 + 30 = 60

    Check if check equals amount (100):

    Since 60 is not equal to 100, adjust the first element in splitAmounts:

    splitAmounts.set(0, splitAmounts.get(0) + (amount - check))
    splitAmounts.set(0, 10 + (100 - 60))
    splitAmounts.set(0, 10 + 40)

    splitAmounts = [50, 20, 30]

    Return the final splitAmounts: [50, 20, 30]

    So, in this example, the PercentSplitAmountStrategy calculates split amounts based on percentages
    and adjusts the first split amount to ensure that the total sum matches the original amount.
    The final result is [50, 20, 30].



 */

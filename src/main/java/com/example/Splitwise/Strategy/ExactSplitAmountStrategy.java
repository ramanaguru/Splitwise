package com.example.Splitwise.Strategy;

import java.util.List;

public  class ExactSplitAmountStrategy implements SplitAmountStrategy{


    public List<Long> splitAmount(int NoOfUsers, Long Amount, List<Long>splits){
            return splits;
    }

}

/*

     Initialize variables (assuming some values are provided, but not specified):

    NoOfUsers = some value
    amount = some value
    splits = a list of Long values, e.g., [10, 20, 30]

    Call the splitAmount method:

    The method simply returns the provided splits without any modification.
    In this example, it would return the original list of splits [10, 20, 30].

    Return the result:
     The result is the same as the input splits ([10, 20, 30]).

    So, the ExactSplitAmountStrategy strategy doesn't perform any calculation or adjustment to the split amounts;
    it just returns the original list of splits as is. The dry run indicates that the output of the strategy is identical to the input list of splits.


*/

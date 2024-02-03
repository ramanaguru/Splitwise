package com.example.Splitwise.Strategy;

import java.util.List;

public interface SplitAmountStrategy {
    List<Long> splitAmount(int NoOfUsers, Long amount, List<Long>splits);
}

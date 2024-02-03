package com.example.Splitwise.Strategy;

import com.example.Splitwise.commands.SplitType;

public class SplitAmountStrategyFactory{
    public static SplitAmountStrategy getSplitAmountStrategy(SplitType splitType){
        if(splitType.equals(SplitType.EQUAL)){
            return new EqualSplitAmountStrategy();
        }

        else if(splitType.equals(SplitType.PERCENT)){
            return new PercentSplitAmountStrategy();
        }

        else if(splitType.equals(SplitType.RATIO)){
            return new RatioSplitAmountStrategy();
        }

        else return  new ExactSplitAmountStrategy();
    }
}

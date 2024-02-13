package com.example.Splitwise.DTOs;

import com.example.Splitwise.commands.PayType;
import com.example.Splitwise.commands.SplitType;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class AddExpenseRequestDTO {
    private Long createdByUserId;

    private List<Long> users;
    

    private List<Long>PaidAmounts;

    private List<Long> SplitAmounts;

    private Long groupId;

    private Long totalAmount;

    private String description;

    private SplitType splitType;

    private PayType payType;


}

package com.example.Splitwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMemberRequestDTO {

    private Long adminId; // adminId is the user who is adding the member

    private Long groupId; // groupId is the group to which the member is being added

    private Long memberId; // memberId is the user who is being added to the group


}

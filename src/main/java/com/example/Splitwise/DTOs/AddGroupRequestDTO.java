package com.example.Splitwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGroupRequestDTO {
    private Long userId;
    private Long groupId;
    private String groupName;
}

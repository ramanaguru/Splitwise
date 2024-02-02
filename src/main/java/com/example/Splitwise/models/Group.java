package com.example.Splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "groups")
public class Group  extends BaseModel{

    private String name;
    @ManyToOne
    private User admin;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Expense> expenses;

    @OneToMany(fetch = FetchType.EAGER)
    private List<User> users;


}

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


/*
    The Group class is a model class that represents a group in the application. It has a name, an admin, a list of expenses, and a list of users.
    The name of the group is a string, the admin is a user, and the list of expenses and users are lists of the respective models.

    The class is annotated with
    @Entity to indicate that it is a JPA entity, and the name attribute is used to specify the name of the entity in the database.

    The class also has getter and setter methods for the attributes, and the admin, expenses, and
    users attributes are annotated with @ManyToOne, @ManyToMany, and @OneToMany respectively to specify the type of relationship between the group and the other models.

    The fetch attribute is set to FetchType.EAGER to indicate that the related entities should be fetched eagerly when the group is fetched from the database.


 */
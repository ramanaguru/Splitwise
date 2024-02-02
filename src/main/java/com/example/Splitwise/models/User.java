package com.example.Splitwise.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private  String name;

    private String email;

    private  String password;
}
/*
    The User class is a model class that represents a user in the application. It has a name, an email, and a password.

    The name, email, and password attributes are strings, and the class is annotated with @Entity to indicate that it is a JPA entity.

    The class also has getter and setter methods for the attributes.

    The User class extends the BaseModel class, which is a base class that contains common attributes such as id, createdAt, and updatedAt.

    The BaseModel class is not shown here, but it is a simple class with the id, createdAt, and updatedAt attributes, and getter and setter methods for these attributes.

    The User class is used to represent users in the application, and it is used in other classes
    such as the Group and Expense classes to represent the users who are part of a group and the users who are involved in an expense.

    The User class is also used in the authentication and authorization logic of the application to represent the users
     who are authenticated and authorized to access the application's resources.

    The User class is a simple model class that represents a user in the application, and
    it is used in various parts of the application to represent the users who are part of a group, the users who are involved in an expense,
    and the users who are authenticated and authorized to access the application's resources.

    The class is annotated with
    @Entity to indicate that it is a JPA entity, and the name, email, and password attributes are strings.

    The class also has getter and setter methods for the attributes, and it extends the BaseModel class, which is a base class
     that contains common attributes such as id, createdAt, and updatedAt.




 */
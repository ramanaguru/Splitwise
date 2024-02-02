package com.example.Splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    @CreatedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date createdAt;

    @LastModifiedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date updatedAt;

}

/*
    @MappedSuperclass --> This annotation is used to create a super class for the entity classes. This super class will not be mapped to the database.
                          But the entity classes which inherit from this super class will have the columns of this super class.

    @Id --> This annotation is used to define the primary key.
    @CreatedDate --> This annotation is used to define the date of creation of the entity.
    @LastModifiedDate --> This annotation is used to define the date of last modification of the entity.
    @Getter --> This annotation is used to generate the getter methods for the fields.
    @Setter --> This annotation is used to generate the setter methods for the fields.
    @GeneratedValue --> This annotation is used to define the generation strategy for the primary key.
    @Temporal --> This annotation is used to define the type of the date.
    @Entity --> This annotation is used to define the class as an entity.
    @EntityListeners --> This annotation is used to define the entity listeners for the entity.


 */

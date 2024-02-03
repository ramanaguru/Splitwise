package com.example.Splitwise.Repository;

import com.example.Splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email); // find user by email
    Optional<User> findById(Long Id);         // find user by  id
}

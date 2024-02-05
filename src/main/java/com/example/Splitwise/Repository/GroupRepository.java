package com.example.Splitwise.Repository;

import com.example.Splitwise.models.Group;
import com.example.Splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group>findAllByAdmin(User user);
}

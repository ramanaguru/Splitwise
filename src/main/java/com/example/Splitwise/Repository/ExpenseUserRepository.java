package com.example.Splitwise.Repository;

import com.example.Splitwise.models.Expense;
import com.example.Splitwise.models.ExpenseUser;
import com.example.Splitwise.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseUserRepository extends JpaRepository<ExpenseUser , Long> {
    List<ExpenseUser> findAllByExpense(Expense expense);
    List<ExpenseUser> finAllByUser(User UserId);
}

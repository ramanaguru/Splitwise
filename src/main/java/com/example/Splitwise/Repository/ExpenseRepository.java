package com.example.Splitwise.Repository;

import com.example.Splitwise.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense , Long> {
    List<Expense>findAllExpense(Expense expense);
}

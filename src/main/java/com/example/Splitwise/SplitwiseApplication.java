package com.example.Splitwise;

import com.example.Splitwise.commands.*;
import com.example.Splitwise.controllers.ExpenseController;
import com.example.Splitwise.controllers.GroupController;
import com.example.Splitwise.controllers.SettleUpController;
import com.example.Splitwise.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;


@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {
	@Autowired
	CommandExecutor commandExecutor;

	@Autowired
	UserController userController;
	@Autowired
	GroupController groupController;
	@Autowired
	ExpenseController expenseController;
	@Autowired
	SettleUpController settleUpController;

	Scanner scanner = new Scanner(System.in);


	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}
	@Override
	public void run(String ... args){
		commandExecutor.addCommand(new RegisterCommand(userController));
		commandExecutor.addCommand(new UpdateProfileCommand(userController));
		commandExecutor.addCommand(new Addgroup(groupController));
		commandExecutor.addCommand(new AddMember(groupController));
		commandExecutor.addCommand(new AddMember(groupController));
		commandExecutor.addCommand(new MyTotal(expenseController));
		commandExecutor.addCommand(new Expense(expenseController));




	}



}

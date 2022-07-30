package com.fatimah.pokePook.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatimah.pokePook.models.Expenses;
import com.fatimah.pokePook.repositories.ExpenseRepository;

@Service
public class ExpenseService {
	private final ExpenseRepository expenseRepository;
	public ExpenseService(ExpenseRepository expenseRepo) {
		this.expenseRepository = expenseRepo;
	}
	
	public List<Expenses> getAllExpenses(){
		List<Expenses> allExpenses = expenseRepository.findAll();
		return allExpenses;
	}
	
	public Expenses createExpense(Expenses expense) {
		Expenses new_expense = expenseRepository.save(expense);
		return new_expense;
	}

}

package com.fatimah.pokePook.services;
import java.util.List;
import java.util.Optional;

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
	
	public Expenses getCurrentExpense(Long id) {
		Optional<Expenses> optionalExpense = expenseRepository.findById(id);
		if(optionalExpense.isPresent()) {
			return optionalExpense.get();
		}
		return null;
	}
	
	public Expenses editExpense(Expenses theExpense) {
		Expenses edited_expense = getCurrentExpense(theExpense.getId());
		edited_expense = expenseRepository.save(theExpense);
		return edited_expense;
	}
	
	public void deleteExpense(Long id) {
		Expenses expense = getCurrentExpense(id);
		expenseRepository.delete(expense);
	}
	
	public Expenses findExpense(Long id) {
		Optional<Expenses> theExpense = expenseRepository.findById(id);
		return theExpense.get();
	}

}

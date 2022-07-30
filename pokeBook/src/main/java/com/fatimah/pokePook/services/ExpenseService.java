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
	
	//get all items method:
	public List<Expenses> getAllExpenses(){
		List<Expenses> allExpenses = expenseRepository.findAll();
		return allExpenses;
	}
	
	//save method:
	public Expenses createExpense(Expenses expense) {
		Expenses new_expense = expenseRepository.save(expense);
		return new_expense;
	}
	
	//get item by Id method:
	public Expenses getCurrentExpense(Long id) {
		Optional<Expenses> optionalExpense = expenseRepository.findById(id);
		if(optionalExpense.isPresent()) {
			return optionalExpense.get();
		}
		return null;
	}
	
	//update method:
	public Expenses editExpense(Long id, Expenses theExpense) {
		Expenses edited_expense = getCurrentExpense(id);
		edited_expense.setExpense_name(theExpense.getExpense_name());
		edited_expense.setVendor(theExpense.getVendor());
		edited_expense.setAmount(theExpense.getAmount());
		edited_expense.setDescription(theExpense.getDescription());
		return expenseRepository.save(edited_expense);
	}
	
	//delete item method:
	public void deleteExpense(Long id) {
		Expenses expense = getCurrentExpense(id);
		expenseRepository.delete(expense);
	}

}

package com.fatimah.pokePook.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fatimah.pokePook.models.Expenses;
import com.fatimah.pokePook.services.ExpenseService;

@Controller
public class ExpenseController {
	private final ExpenseService expenseService;
	public ExpenseController(ExpenseService expenseServ) {
		this.expenseService = expenseServ;
	}
	
	//get all expenses:
	@RequestMapping(value="/expenses", method=RequestMethod.GET)
	public String expenses(Model model) {
		if (!model.containsAttribute("myExpense")) {
			model.addAttribute("myExpense", new Expenses());
		}
		List<Expenses> allExpenses = expenseService.getAllExpenses();
		model.addAttribute("allExpenses", allExpenses);
		return "expensesPage.jsp";
	}
	
	@RequestMapping(value="/expenses", method=RequestMethod.POST)
	public String storeExpenses(@Valid @ModelAttribute("myExpense") Expenses expense,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			System.out.println("Inside the bindingResult*******************");
			//add the expense object and its validation messages to the model:
			redirectAttributes.addFlashAttribute("myExpense", expense);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.myExpense", bindingResult);
			return "redirect:/expenses";
		}
		expenseService.createExpense(expense);
		redirectAttributes.addFlashAttribute("success", "The expense has been added successfully");
		return "redirect:/expenses";
	}
	
	

}

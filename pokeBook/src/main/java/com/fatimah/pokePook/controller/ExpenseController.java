package com.fatimah.pokePook.controller;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fatimah.pokePook.models.Expenses;
import com.fatimah.pokePook.services.ExpenseService;

@Controller
@RequestMapping("expenses")
public class ExpenseController {
	private final ExpenseService expenseService;
	public ExpenseController(ExpenseService expenseServ) {
		this.expenseService = expenseServ;
	}
	
	//get all expenses:
	@RequestMapping(value="", method=RequestMethod.GET)
	public String expenses(Model model) {
		if (!model.containsAttribute("myExpense")) {
			model.addAttribute("myExpense", new Expenses());
		}
		List<Expenses> allExpenses = expenseService.getAllExpenses();
		model.addAttribute("allExpenses", allExpenses);
		return "expensesPage.jsp";
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String storeExpenses(@Valid @ModelAttribute("myExpense") Expenses expense,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			System.out.println("Inside the bindingResult*******************");
			//add the expense object and its validation messages to the model:
			redirectAttributes.addFlashAttribute("myExpense", expense);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.myExpense", bindingResult);
			return "redirect:/";
		}
		expenseService.createExpense(expense);
		redirectAttributes.addFlashAttribute("success", "The expense has been added successfully");
		return "redirect:/expenses";
	}
	
	@RequestMapping(value="/editPage/{id}", method=RequestMethod.GET)
	public String editPage(@PathVariable("id") Long id, Model model){
		if(!model.containsAttribute("current_expense")) {
			Expenses current_expense = expenseService.getCurrentExpense(id);
			model.addAttribute("current_expense",current_expense);
			System.out.println("creating new mapping now ***************  "+ current_expense.getId());
		}
		System.out.println("the id is: " + id);
		System.out.println("because no error, just render");
		return "editExpense.jsp";
	}
	
	
	
	@RequestMapping(value="/toEdit/{id}", method=RequestMethod.PUT)
	public String saveEdit(@PathVariable("id") Long id, @Valid @ModelAttribute("current_expense") Expenses theExpense,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model){
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("current_expense", theExpense);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.current_expense", bindingResult);
			System.out.println("there is an error here *************  " + theExpense.getId());
			return "redirect:/expenses/editPage/{id}";
		}
		expenseService.editExpense(theExpense);
		redirectAttributes.addFlashAttribute("success", "The expense has been Updated successfully");
		System.out.println("no error and updated ************   "+ theExpense.getId());
		return "redirect:/expenses";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		expenseService.deleteExpense(id);
		redirectAttributes.addFlashAttribute("success", "this record has beed deleted");
		return "redirect:/expenses";
	}
	
	@RequestMapping("/display/{id}")
	public String displayDetails(@PathVariable("id") Long id, Model model) {
		Expenses theExpense = expenseService.findExpense(id);
		model.addAttribute("theExpense",theExpense);
		return "expenseInfo.jsp";
	}
	
	

}

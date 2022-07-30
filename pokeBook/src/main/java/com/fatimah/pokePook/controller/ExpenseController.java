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
	
	//save the new item:
	@RequestMapping(value="", method=RequestMethod.POST)
	public String storeExpenses(@Valid @ModelAttribute("myExpense") Expenses expense,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		//if there's validation errors --> add them to Flash attr then redirect to create page
		if (bindingResult.hasErrors()) {
			System.out.println("Inside the bindingResult*******************");
			//add the expense object and its validation messages to the model:
			redirectAttributes.addFlashAttribute("myExpense", expense);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.myExpense", bindingResult);
			return "redirect:/";
		}
		//no errors --> save and redirect to main page:
		expenseService.createExpense(expense);
		redirectAttributes.addFlashAttribute("success", "The expense has been added successfully");
		return "redirect:/expenses";
	}
	
	//display the update page of item:
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
	
	//update an item:
	@RequestMapping(value="/toEdit/{id}", method=RequestMethod.PUT)
	public String saveEdit(@PathVariable("id") Long id, @Valid @ModelAttribute("current_expense") Expenses theExpense,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model){
		//check if there's an error --> redirect to display edit page
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("current_expense", theExpense);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.current_expense", bindingResult);
			return "redirect:/expenses/editPage/{id}";
		}
		//no errors --> update the item then redirect to main page
		expenseService.editExpense(id,theExpense);
		redirectAttributes.addFlashAttribute("success", "The expense has been Updated successfully");
		return "redirect:/expenses";
	}
	
	//delete an item:
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		expenseService.deleteExpense(id);
		redirectAttributes.addFlashAttribute("success", "this record has beed deleted");
		return "redirect:/expenses";
	}
	
	//get details about item:
	@RequestMapping("/display/{id}")
	public String displayDetails(@PathVariable("id") Long id, Model model) {
		Expenses theExpense = expenseService.getCurrentExpense(id);
		model.addAttribute("theExpense",theExpense);
		return "expenseInfo.jsp";
	}
	
	

}

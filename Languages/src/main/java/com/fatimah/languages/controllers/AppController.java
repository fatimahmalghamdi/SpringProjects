package com.fatimah.languages.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fatimah.languages.models.Languages;
import com.fatimah.languages.services.LanguagesService;

@Controller
public class AppController {
	private LanguagesService languagesService;
	public AppController(LanguagesService langServ) {
		this.languagesService = langServ;
	}
	
	//display dashboard
	@RequestMapping("/languages")
	public String dashboard(Model model) {
		if (!model.containsAttribute("alllangs")) {
			model.addAttribute("alllangs", new Languages());
		}
		List<Languages> myAllLangs = languagesService.getlangs();
		model.addAttribute("myAllLangs",myAllLangs);
		return "dashboard.jsp";
	}
	
	//create action
	@RequestMapping(value="/languages", method=RequestMethod.POST)
	public String createlang(@Valid @ModelAttribute("alllangs") Languages thelangs, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		//if there's validation errors --> add them to Flash attr then redirect to create page
		if (bindingResult.hasErrors()) {
			//add the expense object and its validation messages to the model:
			redirectAttributes.addFlashAttribute("alllangs", thelangs);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.alllangs", bindingResult);
			return "redirect:/languages";
		}
		//no errors --> save and redirect to main page:
		languagesService.createlang(thelangs);
		redirectAttributes.addFlashAttribute("success", "The Language has been added successfully");
		return "redirect:/languages";
	}
	
	
	//display Language details
	@RequestMapping("/displaylang/{id}")
	public String displaylang(@PathVariable("id") Long id, Model model) {
		Languages mylang = languagesService.currlang(id);
		if(mylang != null) {
			model.addAttribute("mylang", mylang);
			return "displayLang.jsp";
		}
		return null;
	}
		
		
	//update page
	@RequestMapping(value="/editlang/{id}", method=RequestMethod.GET)
	public String editPage(@PathVariable("id") Long id, Model model) {
		if (!model.containsAttribute("currentlang")) {
			Languages mycurrlang = languagesService.currlang(id);
			model.addAttribute("mylanguages", mycurrlang);
			System.out.println("before render *******" + mycurrlang.getLangname() );
		}
			model.addAttribute("langId", id);
			return "editLang.jsp";
	}
	
	//update action
	@RequestMapping(value="/editlang/{id}", method=RequestMethod.PUT)
	public String updatelangAction(@PathVariable("id") Long id,
			@Valid @ModelAttribute("mylanguages") Languages currentlang,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		//check if there's an error --> redirect to display edit page
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("mylanguages",currentlang);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mylanguages", bindingResult);
			System.out.println("has error *******************************");
			return "redirect:/editlang/{id}";
		}
		//no errors --> update the item then redirect to main page
		languagesService.editlang(id, currentlang);
		redirectAttributes.addFlashAttribute("success", "The Languages has been Updated successfully");
		return "redirect:/languages";
	}
	
	//delete:
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String destroy(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		languagesService.deletelang(id);
		redirectAttributes.addFlashAttribute("success", "this record has beed deleted");
		return "redirect:/languages";
	}
	
	
		
	

}

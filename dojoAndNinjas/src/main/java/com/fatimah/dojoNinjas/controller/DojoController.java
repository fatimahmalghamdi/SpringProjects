package com.fatimah.dojoNinjas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fatimah.dojoNinjas.models.Dojos;
import com.fatimah.dojoNinjas.models.Ninjas;
import com.fatimah.dojoNinjas.repository.DojoRepository;
import com.fatimah.dojoNinjas.services.DojoService;
import com.fatimah.dojoNinjas.services.NinjaService;

@Controller
@RequestMapping("/welcome")
public class DojoController {
	private DojoService dojoService;
	private NinjaService ninjaService;
	public DojoController(DojoService ojoServices, NinjaService ninjaService) {
		this.dojoService = ojoServices;
		this.ninjaService = ninjaService;
	}
	
	@RequestMapping("/choose")
	public String mainPage(Model model) {
		List<Dojos> allDojos = dojoService.getAll();
		model.addAttribute("allDojos", allDojos);
		return "mainPage.jsp";
	}
	
	//create Dojo page:
	@RequestMapping(value="/createDojoPage", method=RequestMethod.GET)
	public String createDojoPage(Model model) {
		if (!model.containsAttribute("mydojo")) {
			Dojos dojo = new Dojos();
			model.addAttribute("mydojo", dojo);
		}
		return "addDojo.jsp";
	}
	
	//create Ninja page:
	@RequestMapping(value="/createNinjaPage", method=RequestMethod.GET)
	public String createNinjaPage(Model model) {
		if (!model.containsAttribute("myninja")) {
			Ninjas ninja = new Ninjas();
			model.addAttribute("myninja", ninja);
		}
		List<Dojos> dojos = dojoService.getAll();
		model.addAttribute("dojos", dojos);
		return "addNinja.jsp";
	}
	
	
	//save dojo:
	@RequestMapping(value="/createdojo", method=RequestMethod.POST)
	public String storeDojo(@Valid @ModelAttribute("mydojo") Dojos dojo,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		System.out.println("store dojo ********");
		//if there's validation errors --> add them to Flash attr then redirect to create page
		if (bindingResult.hasErrors()) {
			//add the expense object and its validation messages to the model:
			redirectAttributes.addFlashAttribute("mydojo", dojo);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mydojo", bindingResult);
			return "redirect:/welcome/createDojoPage";
		}
		//no errors --> save and redirect to main page:
		dojoService.creatdojo(dojo);
		redirectAttributes.addFlashAttribute("success", "The Dojo has been added successfully");
		return "redirect:/welcome/choose";
	}
	
	//save ninja:
	@RequestMapping(value="/createninja", method=RequestMethod.POST)
	public String storeNinja(@Valid @ModelAttribute("myninja") Ninjas ninja,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		System.out.println("store ninja ********");
		//if there's validation errors --> add them to Flash attr then redirect to create page
		if (bindingResult.hasErrors()) {
			//add the expense object and its validation messages to the model:
			redirectAttributes.addFlashAttribute("myninja", ninja);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.myninja", bindingResult);
			return "redirect:/welcome/createNinjaPage";
		}
		//no errors --> save and redirect to main page:
		ninjaService.creatNinja(ninja);
		redirectAttributes.addFlashAttribute("success", "The Ninja has been added successfully");
		return "redirect:/welcome/choose";
	}
	
	//display dojo details:
	@RequestMapping(value="/displayDojo", method=RequestMethod.POST)
	public String displayDojo(@RequestParam("dojo") Long dojo_id, Model model) {
		Dojos my_dojo = dojoService.getDojo(dojo_id);
		model.addAttribute("my_dojo", my_dojo);
		return "dojoDetails.jsp";
	}
	
	
	
	

}

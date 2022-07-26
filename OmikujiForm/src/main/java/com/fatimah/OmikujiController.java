package com.fatimah;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class OmikujiController {
	
	@RequestMapping("")
	public String home() {
		return "homePage.jsp";
	}
	
	@RequestMapping(value="/sendOmikuji", method=RequestMethod.POST)
	public String sendOmikuji(
			@RequestParam(value="numbers") String myNum,
			@RequestParam(value="city") String myCity ,
			@RequestParam(value="name") String myName ,
			@RequestParam(value="hobby") String myHobby ,
			@RequestParam(value="message") String myMessage ,
			HttpSession session) {
		session.setAttribute("theNum", myNum);
		session.setAttribute("theCity", myCity);
		session.setAttribute("theName", myName);
		session.setAttribute("theHobby", myHobby);
		session.setAttribute("theMessage", myMessage);
		
		return "redirect:/home/displayFortune";	
	}
	
	@RequestMapping("/displayFortune")
	public String displayFortune(Model model, HttpSession sesion) {
		model.addAttribute("theNum", sesion.getAttribute("theNum"));
		model.addAttribute("theCity", sesion.getAttribute("theCity"));
		model.addAttribute("myName", sesion.getAttribute("myName"));
		model.addAttribute("theHobby", sesion.getAttribute("theHobby"));
		model.addAttribute("theMessage", sesion.getAttribute("theMessage"));
		
		return "displayFortune.jsp";
	}
	
	

}

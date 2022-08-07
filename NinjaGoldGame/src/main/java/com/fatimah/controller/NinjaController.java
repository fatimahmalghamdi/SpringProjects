package com.fatimah.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NinjaController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String startpoint(HttpSession session) {
		//check if gold is exists or start with 0:
		if(session.getAttribute("thegold") == null) {
			session.setAttribute("thegold", 0);
		}
		//begin the list of activities:
		ArrayList<String> activities = new ArrayList<String>();
		
		if(session.getAttribute("activities") == null) {
			session.setAttribute("activities", activities);
		}
		
		return "mainPage.jsp";
	}
	
	

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String getGold(@RequestParam(name = "location") String location, HttpSession session) {
		//we should convert it to (int) bc the session stores it as string
		int gold = (int) session.getAttribute("thegold");
		Random random = new Random();//import the Random class
		int random_num = 0;
		
		//take the activity list from the session
		ArrayList<String> activities = (ArrayList<String>) session.getAttribute("activities");
		
		//create theTime variable
		LocalDateTime theTime = LocalDateTime.now();
		DateTimeFormatter theFormatDate = DateTimeFormatter.ofPattern("MMMM dd yyyy h:mma");
		//generate random number between 10 and 20
		//to get by range: random.nextInt(max(20) - min(10) + 1) + min(10) since default is 0 to max
		if(location.equals("farm")) {
			random_num = random.nextInt(20 - 10 + 1) + 10;
		}
		//generate random number between 5 and 10
		else if(location.equals("cave")) {
			random_num = random.nextInt(10 - 5 + 1) + 5;
		}
		//generate random number between 2 and 5
		else if(location.equals("house")) {
			random_num = random.nextInt(5 - 2 + 1) + 2;
		}
		//generate random number between 0 and 50 could be - or +
		else if(location.equals("casino")) {
			random_num = random.nextInt(50 + 50 + 1) - 50;
		}
		//generate random number between 5 and 20 (-)
		else if(location.equals("spa")) {
			random_num = random.nextInt(-5 + 20 + 1) - 20;
		}
		
		
		if(random_num < 0) {
			activities.add(0, "You entered a " + location + " and lost "+ Math.abs(random_num) +" .. " + theFormatDate.format(theTime));
			//note: by adding argument 0, it is adding the new item to the FRONT of the list instead of the back
		}
		else {
			activities.add(0, "You entered a " + location + " and earned "+ random_num +" .. " + theFormatDate.format(theTime));
		}
		
		//update session variables
		session.setAttribute("thegold", gold + random_num);
		session.setAttribute("activities", activities);             
//		System.out.println(activities);
		
		//Send the ninja to a debtors prison on a second rendered page if the ninja falls too far into debt
//		if((int) session.getAttribute("gold") < -50) {
//			return "redirect:/prison";
//		}
		
		return "redirect:/";
	}

	
}

package com.fatimah.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String index() {
		return "index.jsp";
	}
	
	@RequestMapping("/displaydate")
	public String displaydate(Model model) {
		Date mydate = new Date();
		SimpleDateFormat formatMyDate = new SimpleDateFormat("EEEE', the ' d ' of ' MMMM', ' yyyy", Locale.ENGLISH);
		String myFormattedDate = formatMyDate.format(mydate);
		model.addAttribute("currentdate",myFormattedDate);
		return "displaydate.jsp";
	}
	
	
	@RequestMapping("/displaytime")
	public String displaytime(Model model) {
		Date mytime = new Date();
		SimpleDateFormat formatMytime = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
		String myFormattedTime = formatMytime.format(mytime);
		model.addAttribute("currenttime", myFormattedTime);
		return "displaytime.jsp";
	}
	
	

}

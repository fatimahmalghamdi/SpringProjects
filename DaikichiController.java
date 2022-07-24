package com.fatimah;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daikichi")
public class DaikichiController {

	@RequestMapping("")
	public String index1() {
		return "Welcome";
	}
	
	@RequestMapping("/today")
	public String index2() {
		return "Today you wil find luck, in all your endeavor";
	}
	
	@RequestMapping("/tomorrow")
	public String index3() {
		return "Tomorrow will be a good day";
	}
	
	@RequestMapping("/travel/{city}")
	public String index4(@PathVariable("city") String city) {
		return "Congratulation, you will soon travle to " +  city;
	}
	
	@RequestMapping("/lotto/{number}")
	public String index5(@PathVariable("number") String num) {
		int myNum = Integer.parseInt(num);
		if (myNum % 2 == 0)
			return "You will take a grand journy in the near future, but be wary of tempting offers";
		else 
			return "You have enjoyed the fruits of your labor but noe is a great time to spend with family and friends ";
		
	}
	
	
}


package com.example.adminDashboard.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.adminDashboard.models.User;
import com.example.adminDashboard.request.UserLoginRequests;
import com.example.adminDashboard.services.UserService;

@Controller
public class UserController {
	private UserService userService;
	public UserController (UserService userService) {
		this.userService = userService;
	}
	
	
	//Display Reg9istration page:
		@RequestMapping(value="/", method=RequestMethod.GET)
		public String createUserPage(Model model) {
			if (!model.containsAttribute("new_user")) {
				model.addAttribute("new_user",new User());
			}
			if (!model.containsAttribute("userLoginRequests")) {
				model.addAttribute("userLoginRequests",new UserLoginRequests());
			}
			return "index.jsp";
		}
		
		//Registration new user:
		@RequestMapping(value="/newuser", method=RequestMethod.POST)
		public String storeUser(@Valid @ModelAttribute("new_user") User newUser, BindingResult bindingResult,
				RedirectAttributes redirectAttributes,
				HttpSession session ,Model model) {
			User user = userService.registerUser(newUser, bindingResult);
			//new user null means has errors or already exist
			if (user == null) {
				redirectAttributes.addFlashAttribute("new_user", newUser);
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.new_user", bindingResult);
				return "redirect:/";
			}
			//new user has been registered successfully
			else {
				//check is there is an admin:
				if (userService.checkAdmins()) {
					//store the user in session as normal user
					session.setAttribute("user_id", user.getId());
					return "redirect:/logInChecker";
				}
				else {
					// put current user to be admin:
					session.setAttribute("admin_id", user.getId());
					userService.changeToAdmin(user.getId());
					List<User> allusers = userService.getAllUsers();
					model.addAttribute("allusers", allusers);
					model.addAttribute("currentUser", user);
					return "adminPage.jsp";
				}
			}
		}
		
		//Login user:
		@RequestMapping(value="/login", method=RequestMethod.POST)
		public String login(@Valid @ModelAttribute("userLoginRequests") UserLoginRequests userLoginRequests,
				BindingResult bindingResult, RedirectAttributes redirectAttributes,
				HttpSession session, Model model) {
			if (bindingResult.hasErrors()) {
				redirectAttributes.addFlashAttribute("userLoginRequests", userLoginRequests);
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginRequests", bindingResult);
				return "redirect:/";
			}
			User user = userService.loginUser(userLoginRequests, bindingResult);
			if (user == null) {
				redirectAttributes.addFlashAttribute("userLoginRequests", userLoginRequests);
				redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginRequests", bindingResult);
				return "redirect:/";
			}
			else {
				
				if (user.getUser_level().equals("Admin")) {
					session.setAttribute("admin_id", user.getId());
					return "redirect:/adminpage";
				}
				else {
					session.setAttribute("user_id", user.getId());
					return "redirect:/logInChecker";
				}
			}	
		}
		
		
		@RequestMapping(value="/logInChecker", method=RequestMethod.GET)
		public String logInChecker() {
			return "logIn.jsp";
		}
		
		
		//delete user:
		@GetMapping("/deleteuser/{userId}")
		public String deleteUser(@PathVariable("userId") Long user_id, Model model) {
			userService.deleteuserById(user_id);
			return "redirect:/adminpage";
		}
		
		
		
		//Display admin page:
		@GetMapping("/adminpage")
		public String displayAdminPage(Model model) {
			List<User> allusers = userService.getAllUsers();
			model.addAttribute("allusers", allusers);
			return "adminPage.jsp";
		}
		
		//edit user to be admin:
		@GetMapping("/editlevel/{userId}")
		public String editUser(@PathVariable("userId") Long user_id, Model model) {
			userService.edituserById(user_id);
			return "redirect:/adminpage";
		}
		
		//logout user:
		@GetMapping("/logoutuser")
		public String logoutUser(HttpSession session) {
			session.setAttribute("admin_id", "null");
			return "redirect:/";
		}
		

}

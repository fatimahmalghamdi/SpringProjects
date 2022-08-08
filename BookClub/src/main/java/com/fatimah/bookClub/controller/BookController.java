package com.fatimah.bookClub.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.aspectj.bridge.Message;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fatimah.bookClub.models.Book;
import com.fatimah.bookClub.models.Thoughts;
import com.fatimah.bookClub.models.User;
import com.fatimah.bookClub.requests.UserLoginRequests;
import com.fatimah.bookClub.services.BookService;
import com.fatimah.bookClub.services.ThoughtService;
import com.fatimah.bookClub.services.UserService;

@Controller
public class BookController {
	private UserService userService;
	private BookService bookService;
	private ThoughtService thoughtService;
	public BookController(UserService userServ, BookService bookServ, ThoughtService thoughtServ) {
		this.userService = userServ;
		this.bookService = bookServ;
		this.thoughtService = thoughtServ;
	}

	
	
	///////////////////////////// User Registration & LogIn Part//////////////////////////
	
	//Register new user page:
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String createUserPage(Model model) {
		System.out.println("inside the main method***********");
		if (!model.containsAttribute("new_user")) {
			model.addAttribute("new_user", new User());
		}
		if (!model.containsAttribute("userLoginRequests")) {
			model.addAttribute("userLoginRequests", new UserLoginRequests());
		}
		System.out.println("Heree***********");
		return "registration_login.jsp";
	}

	//Registration new user:
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String storeUser(@Valid @ModelAttribute("new_user") User newUser, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		User user = userService.registerUser(newUser, bindingResult);
		//new user null means has errors or already exist
		if (user == null) {
			redirectAttributes.addFlashAttribute("new_user", newUser);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.new_user",
					bindingResult);
			return "redirect:/";
		}
		//new user has been registered successfully
		else {
			//logIn the new user after registration put the user_id in session
			session.setAttribute("user_id", user.getId());
			return "redirect:/dashboard";
		}
	}

	//Login user:
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("userLoginRequests") UserLoginRequests userLoginRequests,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("userLoginRequests", userLoginRequests);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginRequests",
					bindingResult);
			return "redirect:/";
		}
		User user = userService.loginUser(userLoginRequests, bindingResult);
		if (user == null) {
			redirectAttributes.addFlashAttribute("userLoginRequests", userLoginRequests);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginRequests",
					bindingResult);
			return "redirect:/";
		} else {
			session.setAttribute("user_id", user.getId());
			return "redirect:/dashboard";
		}
	}
	
	//LogOut:
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("user_id") != null) {
			session.setAttribute("user_id", null);
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}
	
	
	
	////////////////////////////// Book Part //////////////////////////////////////////
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(HttpSession session, Model model) {
		//if user not login redirect to login page
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		//if user login, the dashboard page will appear
		else {
			if (!model.containsAttribute("userName")) {
				Long current_id = (Long) session.getAttribute("user_id");
				User current_user = userService.getUser(current_id);
				model.addAttribute("userName", current_user.getUsername());
			}
			List<Book> allBooks = bookService.getAllBooks();
			model.addAttribute("allBooks", allBooks);

			return "mainPage.jsp";
		}
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addShowPage(Model model, HttpSession session) {
		//if user not login redirect to login page
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		} else {
			if (!model.containsAttribute("addBook")) {
				model.addAttribute("addBook", new Book());
			}
			return "createBook.jsp";
		}
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addBookAction(@Valid @ModelAttribute("addBook") Book newbook, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, HttpSession session) {
		//if there's validation errors --> add them to Flash attr then redirect to create page
		if (bindingResult.hasErrors()) {
			System.out.println("Inside the bindingResult*******************");
			redirectAttributes.addFlashAttribute("addBook", newbook);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addBook", bindingResult);
			return "redirect:/addbook";
		}
		//no errors --> save and redirect to main page:
		Long creator_id = (Long) session.getAttribute("user_id");
		bookService.createBook(newbook, creator_id);
		redirectAttributes.addFlashAttribute("success", "The Book has been added successfully");
		return "redirect:/dashboard";
	}

	//get details about item:
	@RequestMapping("/display/{id}")
	public String displayDetails(@PathVariable("id") Long book_id, Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		} else {
			Book theBook = bookService.getCurrentBook(book_id);
			model.addAttribute("theBook", theBook);
			if (!model.containsAttribute("writethought")) {
				model.addAttribute("writethought", new Thoughts());
			}
			List<Thoughts> allthoughts = bookService.gettheseThoughts(book_id);
			model.addAttribute("allthoughts", allthoughts);
			model.addAttribute("theThought", new Thoughts());

			return "displayDetails.jsp";
		}
	}

	//display the update page of Movie:
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editPage(@PathVariable("id") Long book_id, Model model, HttpSession session) {
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		} else {
			if (!model.containsAttribute("current_book")) {
				Book current_book = bookService.getCurrentBook(book_id);
				model.addAttribute("current_book", current_book);
			}
			model.addAttribute("BookId", book_id);
			return "editBook.jsp";
		}
	}

	//update Movie:
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public String saveEdit(@PathVariable("id") Long book_id, @Valid @ModelAttribute("current_book") Book current_book,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		//check if there's an error --> redirect to display edit page
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("current_book", current_book);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.current_book",
					bindingResult);
			return "redirect:/edit/{id}";
		}
		//no errors --> update the item then redirect to main page
		bookService.editBook(book_id, current_book);
		redirectAttributes.addFlashAttribute("success", "The Book has been Updated successfully");
		return "redirect:/dashboard";
	}

	
	//add thought to the book:
	@RequestMapping(value = "/addthought/{id}", method = RequestMethod.POST)
	public String addThought(@PathVariable("id") Long boo_id, HttpSession session,
			@Valid @ModelAttribute("theThought") Thoughts theThought, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		//check if there's an error --> redirect to display details page
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("theThought", theThought);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.theThought",
					bindingResult);
			return "redirect:/dashboard";
		}
		//no errors --> save and redirect to main page:
		Long creator_id = (Long) session.getAttribute("user_id");
		thoughtService.createThought(theThought, creator_id, boo_id);
		redirectAttributes.addFlashAttribute("success", "The Thought has been added successfully");
		return "redirect:/dashboard";
	}
	
	
}

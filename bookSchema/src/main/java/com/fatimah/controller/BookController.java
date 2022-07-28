package com.fatimah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fatimah.models.Book;
import com.fatimah.services.BookService;

@Controller
public class BookController {
	private final BookService bookService;
	public BookController(BookService bookserv) {
		this.bookService = bookserv;
	}
	
	//display book by Id: 
	@RequestMapping("/api/books/{id}")
	public String viewBookById(@PathVariable("id") Long book_id, Model model) {
		Book theBook = bookService.findBook(book_id);
		model.addAttribute("theBook",theBook);
		return "displayBookInfo.jsp";
	}

}


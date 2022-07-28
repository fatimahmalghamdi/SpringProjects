package com.fatimah.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fatimah.models.Book;
import com.fatimah.services.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
	private final BookService bookService;
	public BookController(BookService bookserv) {
		this.bookService = bookserv;
	}
	
	
	//form to create book:
	@RequestMapping(value="/creat", method=RequestMethod.GET)
	public String creatBookPage() {
		return "createBook.jsp";
	}
	
	//create new book:
	@RequestMapping(value="/creat", method=RequestMethod.POST)
	public String creatBookDB(
			@RequestParam(value="title") String title,
			@RequestParam(value="description") String desc,
			@RequestParam(value="language") String lang,
			@RequestParam(value="numberPages") Integer numberPages,
			RedirectAttributes redirectAttributes
			) {
		Book book = new Book(title, desc, lang, numberPages);
		bookService.creatBook(book);
		redirectAttributes.addFlashAttribute("success", "Your book has been created");
		return "redirect:/books";
	}
	
	
	//display all books:
	@RequestMapping("")
	public String displayBooks(Model model) {
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);
		return "displayBooks.jsp";
	}
	
	//display book by Id: 
	@RequestMapping("/displayById/{book_id}")
	public String displayById(@PathVariable("book_id") Long book_id, Model model) throws Exception {
		Book theBook = bookService.findBook(book_id);
		if(theBook == null) {
			throw new Exception("Book is not found with the ID: " + book_id);
		}
		model.addAttribute("theBook",theBook);
		return "bookInfo.jsp";
	}
	
	//updateBook View:
	@RequestMapping(value="/updateById/{book_id}", method=RequestMethod.GET)
    public String updateView(@PathVariable("book_id") Long book_id, Model model) {
		Book bookToUpdate = bookService.findBook(book_id);
		model.addAttribute(bookToUpdate);
		return "bookToUpdate.jsp";
	}
	
	//update book:
	@RequestMapping(value="/updateById/{book_id}", method=RequestMethod.PUT)
    public String update(
    		@PathVariable("book_id") Long book_id, 
    		@RequestParam(value="title") String title, 
    		@RequestParam(value="description") String desc, 
    		@RequestParam(value="language") String lang,
    		@RequestParam(value="numberPages") Integer numOfPages,
    		RedirectAttributes redirectAttributes
    		) throws Exception {
       Book book = bookService.updateBook(book_id, title, desc, lang, numOfPages);
       if (book != null) {
    	   redirectAttributes.addFlashAttribute("success", "Your book has been created");    	   
       }
       else {
    	   redirectAttributes.addFlashAttribute("error", "Your book has been created"); 
       }
        return "redirect:/book";
    }
    
	
    @RequestMapping(value="/deleteById/{book_id}", method=RequestMethod.POST)
    public void destroy(@PathVariable("book_id") Long book_id) {
        bookService.deleteBook(book_id);
    }
	


}

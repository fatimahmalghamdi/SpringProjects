package com.fatimah.controller;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatimah.models.Book;
import com.fatimah.services.BookService;

@RestController
public class BookApi {
	private final BookService bookService;
	public BookApi(BookService bookserv) {
		this.bookService = bookserv;
	}
	
	
	//create new book:
	@RequestMapping(value="/api/books", method=RequestMethod.POST)
	public Book creatBookDB(
			@RequestParam(value="title") String title,
			@RequestParam(value="description") String desc,
			@RequestParam(value="language") String lang,
			@RequestParam(value="numberPages") Integer numberPages) {
		Book book = new Book(title, desc, lang, numberPages);
		return bookService.createBook(book);
	}
	
	//display all books:
	@RequestMapping("api/books")
	public List<Book> viewBooks() {
		return bookService.allBooks();
	}
	
	//display book by Id: 
	@RequestMapping("/api/books/{id}")
	public Book viewById(@PathVariable("book_id") Long book_id) {
		Book theBook = bookService.findBook(book_id);
		return theBook;
	}
	
	//update book:
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
    public Book update(
    		@PathVariable("id") Long id, 
    		@RequestParam(value="title") String title, 
    		@RequestParam(value="description") String desc, 
    		@RequestParam(value="language") String lang,
    		@RequestParam(value="pages") Integer numOfPages)
	{
		return bookService.updateBook(id, title, desc, lang, numOfPages);
    }
    
	
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
	

}

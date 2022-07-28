package com.fatimah.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fatimah.models.Book;
import com.fatimah.repositories.BookRepository;

@Service
public class BookService {
	
private final BookRepository bookRepo;
	
	public BookService(BookRepository bookRepository) {
		this.bookRepo = bookRepository;
	}
	
	//create new book
		public Book creatBook(Book b) {
			return bookRepo.save(b);
	}
		
	
	//return all books
	public List<Book> getAllBooks() {
		List<Book> books = bookRepo.findAll();
		return books;
	}
	//retrieves a book by Id
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			return book;
		}
		else
			return null;
	}
	
	//update a book by Id:
	public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setTitle(title);
			book.setDescription(desc);
			book.setLanguage(lang);
			book.setNumberOfPages(numOfPages);
			
			return bookRepo.save(book);
		}
		else
			return null;
	}
	
	//delete a book by Id:
	public void deleteBook(Long book_id) {
		bookRepo.deleteById(book_id);
	}

}

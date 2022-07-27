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
	//Constructor:
	public BookService(BookRepository bookRepository) {
		this.bookRepo = bookRepository;
	}

	//view all books:
	public List<Book> allBooks() {
		return bookRepo.findAll();
	}

	//creates a book:
	public Book createBook(Book b) {
		return bookRepo.save(b);
	}

	//retrieves a book:
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if (optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	//update book:
	public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if (optionalBook.isPresent()) {
			Book theBook =  optionalBook.get();
			theBook.setTitle(title);
			theBook.setDescription(desc);
			theBook.setLanguage(lang);
			theBook.setNumberOfPages(numOfPages);
			return bookRepo.save(theBook);
		}
		else 
			return null;
	}
	
	
	//delete book by Id:
	public void deleteBook(Long book_id) {
		Optional<Book> optionalBook = bookRepo.findById(book_id);
		if (optionalBook.isPresent())
			bookRepo.deleteById(book_id);
	}

}

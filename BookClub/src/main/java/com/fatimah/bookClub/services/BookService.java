package com.fatimah.bookClub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fatimah.bookClub.models.Book;
import com.fatimah.bookClub.models.Thoughts;
import com.fatimah.bookClub.models.User;
import com.fatimah.bookClub.repositories.BookRepository;
import com.fatimah.bookClub.repositories.UserRepository;


@Service
public class BookService {
	private BookRepository bookRepository;
	private UserRepository userRepository;
	public BookService(BookRepository bookRepo, UserRepository userRepo) {
		this.bookRepository = bookRepo;
		this.userRepository = userRepo;
	}
	

	
	//get all books:
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	//get book by id:
	public Book getCurrentBook(Long id) {
		Optional<Book> theBook = bookRepository.findById(id);
		return theBook.get();
	}
	
	
	//save method:
	public Book createBook(Book newbook, Long craetor_id) {
		Optional<User> theUser = userRepository.findById(craetor_id);
		newbook.setCreatorUser(theUser.get());
		Book addBook = bookRepository.save(newbook);
		return addBook;
	}
	
	public List<Thoughts> gettheseThoughts(Long book_id){
		Book theBook = getCurrentBook(book_id);
		List<Thoughts> currentThoughts = theBook.getBookThoughts();
		return currentThoughts;
	}
	
	
	//update Movie:
	public Book editBook(Long id, Book theBook) {
		Book edited_book = getCurrentBook(id);
		edited_book.setTitle(theBook.getTitle());
		edited_book.setAuthorName(theBook.getAuthorName());
		 return bookRepository.save(edited_book);
	}
	
	
	
	
	

}

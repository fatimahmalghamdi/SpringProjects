package com.fatimah.bookClub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fatimah.bookClub.models.Book;
import com.fatimah.bookClub.models.Thoughts;
import com.fatimah.bookClub.models.User;
import com.fatimah.bookClub.repositories.BookRepository;
import com.fatimah.bookClub.repositories.ThoughtRepository;
import com.fatimah.bookClub.repositories.UserRepository;

@Service
public class ThoughtService {
	private ThoughtRepository thoughtRepository;
	private UserRepository userRepository;
	private BookRepository bookRepository;
	public ThoughtService(ThoughtRepository thoughtRepo, UserRepository userRepo, BookRepository bookRepo) {
		this.thoughtRepository = thoughtRepo;
		this.userRepository = userRepo;
		this.bookRepository = bookRepo;
	}
	
	//create new thought from specific user to specific book:
	public void createThought(Thoughts mythought, Long cretor_id, Long book_id) {
		Optional<User> theUser = userRepository.findById(cretor_id);
		Optional<Book> theBook = bookRepository.findById(book_id);
		mythought.setUser(theUser.get());
		mythought.setBook(theBook.get());
		thoughtRepository.save(mythought);
		}
	
	

}

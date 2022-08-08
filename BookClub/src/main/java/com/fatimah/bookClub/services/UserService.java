package com.fatimah.bookClub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.fatimah.bookClub.models.User;
import com.fatimah.bookClub.repositories.UserRepository;
import com.fatimah.bookClub.requests.UserLoginRequests;

@Service
public class UserService {
	
	private UserRepository userRepository;
	public UserService(UserRepository userRepository ) {
		this.userRepository = userRepository;
	}
	
	//Registration method:
	public User registerUser(User user, BindingResult bindingResult) {
		Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
		//check if the email already exist
		if (optionalUser.isPresent()) {
			bindingResult.rejectValue("email","unique","The Email Address is already exist");
		}
		//check if passwords are matched
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			bindingResult.rejectValue("password","matches","The passwords are not matched");
		}
		//check if there are errors, otherwise start to register user
		if(bindingResult.hasErrors()) {
			return null;
		}
		else {
			//generate hash pass -> set pass -> then save
			String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(hashed);
			return userRepository.save(user);
		}
	}
	
	//LogIn method:
	public User loginUser(UserLoginRequests userLoginRequests, BindingResult bindingResult) {
		Optional<User> optionalUser = userRepository.findByEmail(userLoginRequests.getEmail());
		//validate the email address is exist
		if (!optionalUser.isPresent()) {
			bindingResult.rejectValue("email", "notExist","This email does not exist");
		}
		else {
			//validate the pass from DB and the userLoginRequest class
			User user = optionalUser.get();
			if(BCrypt.checkpw(userLoginRequests.getPassword(), user.getPassword())) {
				return user;
			}
			else {
				bindingResult.rejectValue("password", "doesn't_match","Wrong password");
			}
		}
		
		return null;
	}
	
	
	//get current User:
	public User getUser(Long id) {
		Optional<User> current_user = userRepository.findById(id);
		return current_user.get();
	}

}

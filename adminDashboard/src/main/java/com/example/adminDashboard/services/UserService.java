package com.example.adminDashboard.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.example.adminDashboard.models.User;
import com.example.adminDashboard.repositories.UserRepository;
import com.example.adminDashboard.request.UserLoginRequests;


@Service
public class UserService {
	private UserRepository userRepository;
	public UserService(UserRepository userRepository) {
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
		
	// login process	
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
	
	//check admin exists: if there is admin return true otherwise return false:
	public boolean checkAdmins(){
		List<User> allusers = userRepository.findAll();
		for (int i=0; i < allusers.size(); ++i) {
			String userLevel = allusers.get(i).getUser_level();
			if (userLevel != null) {
				return true;
			}
		}
		return false;
		
	}
	
	// update user to be admin:
	public User changeToAdmin(Long user_id) {
		Optional<User> current_user = userRepository.findById(user_id);
		User theUser = current_user.get();
		theUser.setUser_level("Admin");
		return userRepository.save(theUser);
	}
	
	
	//get all users:
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	//delete user:
	public void deleteuserById(Long user_id) {
		Optional<User> currentUser = userRepository.findById(user_id);
		User theUser = currentUser.get();
		userRepository.delete(theUser);
	}
	
	
	//edit user to be admin:
	public void edituserById(Long user_id) {
		Optional<User> currentUser = userRepository.findById(user_id);
		User theUser = currentUser.get();
		theUser.setUser_level("Admin");
		userRepository.save(theUser);	
	}

}

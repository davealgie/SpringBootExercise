package com.qa.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.models.User;
import com.qa.demo.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	@Autowired //triggers dependency injection
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") int id, @RequestBody User user) {
		return this.userService.updateUser(id, user);
	}
	
	@GetMapping
	public List<User> getUsers() {
		return this.userService.getUsers();
	}

	@GetMapping("/{id}")
	public User getUserbyId(@PathVariable("id")int id) {
		return this.userService.getUserbyId(id);
	}
	
	// localhost:8080/user/email?email=GeorgeEmail@bruh.com
	@GetMapping("/email")
    public List<User> getUsersByEmail(@RequestParam("email") String email) {
		return this.userService.getUsersByEmail(email);
	}
	
	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		return this.userService.createUser(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		this.userService.deleteUser(id);
	}
	
	

}

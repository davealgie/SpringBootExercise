package com.qa.demo.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
		User data = this.userService.updateUser(id, user);

		return new ResponseEntity<User>(data, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		List<User> data = this.userService.getUsers();
		
		return new ResponseEntity<List<User>>(data, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserbyId(@PathVariable("id")int id) {
		User data = this.userService.getUserbyId(id);
		
		return new ResponseEntity<User>(data, HttpStatus.OK);
	}
	
	// localhost:8080/user/email?email=GeorgeEmail@bruh.com
	@GetMapping("/email")
    public ResponseEntity<List<User>> getUsersByEmail(@RequestParam("email") String email) {
		List<User> data = this.userService.getUsersByEmail(email);
		
		if (data != null) {
			return new ResponseEntity<List<User>>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<User>>(data, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User data = this.userService.createUser(user);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setLocation(URI.create("localhost:8080/" + data.getId())); // setter for Location header already exists
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<User>(data, headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		this.userService.deleteUser(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}

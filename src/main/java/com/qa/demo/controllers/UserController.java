package com.qa.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

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

@RestController
@RequestMapping("/user")
public class UserController {
	
	private List<User> users;
	
	public UserController() {
		this.users = new ArrayList<>();
		this.users.add(new User(1, "Pat", 23 ,"PatsEmail@bruh.com"));
		this.users.add(new User(2, "Dave", 28 ,"DavesEmail@bruh.com"));
	
	}
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") int id, @RequestBody User user) {
		for (User a : users) {
			if (a.getId() == id) {
				a.setAge(user.getAge());
				a.setEmail(user.getEmail());
				a.setName(user.getName());
				
				return a;
			}
		}
		return null;
	}
	
	@GetMapping
	public List<User> getUsers() {
		return this.users;
	}

	@GetMapping("/{id}")
	public User getUserbyId(@PathVariable("id")int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	// localhost:8080/user/email?email=GeorgeEmail@bruh.com
	@GetMapping("/email")
    public List<User> getUsersByEmail(@RequestParam("email") String email) {
    return users.stream()
            .filter(u -> u.getEmail().equals(email))
            .collect(Collectors.toList());
	}
	
	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		this.users.add(user);
		return user;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		for (int i = 0; i < users.size(); i++) {
			User a = users.get(i);
			
			if (a.getId() == id) {
				this.users.remove(a);
				break;
			}
		}
	}
	
	/*
	 * @DeleteMapping("/{id}") - This works but throws "status": 500,"error": "Internal Server Error"!
	 * public void deleteUser(@PathVariable("id") int id) {
	 * this.users.forEach(a -> { if (a.getId() == id) {  
	 * this.users.remove(a); } });
	 * }
	 */

}

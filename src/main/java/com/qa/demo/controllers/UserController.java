package com.qa.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

}

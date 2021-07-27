package com.qa.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qa.demo.models.User;

@Service
public class UserService {
	
	private List<User> users;
	
	public UserService() {
		this.users = new ArrayList<>();
		this.users.add(new User(1, "Pat", 23 ,"PatsEmail@bruh.com"));
		this.users.add(new User(2, "Dave", 28 ,"DavesEmail@bruh.com"));
	}

	public List<User> getUsers() {
		return this.users;
	}

	public User updateUser(int id, User user) {
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
	

	public User getUserbyId(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	// localhost:8080/user/email?email=GeorgeEmail@bruh.com
    public List<User> getUsersByEmail(String email) {
    return users.stream()
            .filter(u -> u.getEmail().equals(email))
            .collect(Collectors.toList());
	}
	
	public User createUser(User user) {
		this.users.add(user);
		return user;
	}
	
	public void deleteUser( int id) {
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

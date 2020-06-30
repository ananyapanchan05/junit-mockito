package com.example.Junit_Crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.Junit_Crud.exceptions.ResourceNotFoundException;
import com.example.Junit_Crud.model.User;
import com.example.Junit_Crud.repo.UserRepository;
import com.example.Junit_Crud.service.UserService;

@RestController

public class UserController_RestAPI {
	
	  @Autowired
	  private UserService userService;
	  @GetMapping("/fetchUsers")
	  public List<User> getAllUsers() {
	    return userService.getUsers();
	  }

	  @GetMapping("/fetchUsers/{id}")
	  public ResponseEntity<User> getUsersById(@PathVariable(value = "id") int userId)
	      throws ResourceNotFoundException {
	    User user =
	    		userService.getUserbyId(userId);
	    return ResponseEntity.ok().body(user);
	  }

	  @PostMapping("/createUsers")
	  public User createUser(@Validated @RequestBody User user) {
	    return userService.addUser(user);
	  }

	  @PutMapping("/putUsers/{id}")
	  public ResponseEntity<User> updateUser(
	      @PathVariable(value = "id") int userId, @Validated @RequestBody User userDetails)
	      throws ResourceNotFoundException {
		  
	    final User updatedUser = userService.updateUser(userId, userDetails);
	    return ResponseEntity.ok(updatedUser);
	  }
	  
	  @DeleteMapping("/delUser/{id}")
	  
	  public Map<String, Boolean> deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
	    userService.deleteUser(userId);
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	  }

}

package com.example.Junit_Crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Junit_Crud.model.User;
import com.example.Junit_Crud.service.UserService;

@RestController
public class UserController_MVC {
	@Autowired
	private UserService service;
	
	
	@GetMapping("/getAllUsers")
	public List<User> findAllUsers() {
		return service.getUsers();
	}
	
	@GetMapping(value = "/users/{id}") 
	public ResponseEntity<User> getUserById (@PathVariable("id") int id)
	{
	    User u=service.getUserbyId(id);
	    return ResponseEntity.ok().body(u);
	}
	
	
	@PostMapping(value = "/saveUsers")
	public ResponseEntity<User> addUser (@Validated @RequestBody User userDetails)
	{
	    User us=service.addUser(userDetails);
	    return new ResponseEntity<User>(us, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/updateUsers/{id}")
	public ResponseEntity<User> updateUser (@PathVariable("id") int id, @Validated @RequestBody User userDetails)
	{
		User usr=service.updateUser(id, userDetails);
	    
	    return new ResponseEntity<User>(usr, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteUsers/{id}")
	public ResponseEntity<HttpStatus> removeUser (@PathVariable("id") int id)
	{
	    service.deleteUser(id);
	    return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}
	

}

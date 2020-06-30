package com.example.Junit_Crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Junit_Crud.model.User;
import com.example.Junit_Crud.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	

	public User addUser(User user) {
		return repository.save(user);
	}

	public List<User> getUsers() {
		List<User> users = repository.findAll();
		System.out.println("Getting data from DB : " + users);
		return users;
	}
	public User updateUser(int id,User userDetails)
	{
		User user=repository.findById(id);
		user.setName(userDetails.getName());
		user.setAge(userDetails.getAge());
		user.setAddress(userDetails.getAddress());
		User updatedUser =repository.save(user);
		return updatedUser;
	}

	public List<User> getUserbyAddress(String address) {
		return repository.findByAddress(address);
	}
	public User getUserbyId(int id)
	{
		return repository.findById(id);
	}

	public void deleteUser(int id) {
		User user =repository.findById(id);
		repository.delete(user);
	}


}

package com.example.Junit_Crud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Junit_Crud.model.User;



public interface UserRepository extends JpaRepository<User,Integer>{
	List<User> findByAddress(String address);
	User findById(int id);
	

}

//package com.example.Junit_Crud.Tests;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.example.Junit_Crud.model.User;
//import com.example.Junit_Crud.repo.UserRepository;
//import com.example.Junit_Crud.service.UserService;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class Junit_Crud_Tests {
//	@Autowired
//	private UserService userServ;
//	@MockBean
//	private UserRepository userRepo; //mocking the repository
//	@Test
//	public void getUsersTest()
//	{
//		//Stream.of returns a sequential stream containing single element.
//		//Collector collects all input elements into a list.
//		when(userRepo.findAll()).thenReturn(Stream
//				.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
//		assertEquals(2, userServ.getUsers().size());
//
//	}
//	@Test
//	public void getUserbyAddressTest() {
//		String address = "Bangalore";
//		when(userRepo.findByAddress(address))
//				.thenReturn(Stream.of(new User(376, "Danile", 31, "Bangalore")).collect(Collectors.toList()));
//		assertEquals(1, userServ.getUserbyAddress(address).size());
//		
//	}
//
//	@Test
//	public void saveUserTest() {
//		User user = new User(999, "Pranya", 33, "Pune");
//		when(userRepo.save(user)).thenReturn(user);
//		assertEquals(user, userServ.addUser(user));
//	}
//
//	@Test
//	public void deleteUserTest() {
//		User user = new User(999, "Pranya", 33, "Pune");
//		userServ.deleteUser(user);
//		verify(userRepo, times(1)).delete(user);
//	}
//
//}
//
//	
// 
//

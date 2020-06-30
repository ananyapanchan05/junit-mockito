//package com.example.Junit_Crud.Tests;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.example.Junit_Crud.controller.UserController;
//import com.example.Junit_Crud.model.User;
//import com.example.Junit_Crud.service.UserService;
//
//
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
//
//public class Junit_CrudMVCTest_1 {
//	@MockBean
//	private UserService UserServ;
//	@Autowired
//	private MockMvc mockMVC;
//	
//	String exampleUserJson = "{\"id\":1,\"name\":\"User_1\",\"age\":150,\"address\":\"Hyderabad\"}";
//
//	
//	@Test
//	public void getUsersTest() throws Exception
//	{
//		when(UserServ.getUsers()).thenReturn
//		
//		(Arrays.asList(new User(1,"User_1",50,"Hyderabad"),
//				new User(2,"User_2",20,"Hyderabad"),
//				new User(3,"User_3",30,"Chennai")
//				));
//		
//		
//		
//		//Just Mocking the Service class using @MockBean 
//		RequestBuilder request= MockMvcRequestBuilders.get("/getUsers")
//				.accept(MediaType.APPLICATION_JSON); 
//		MvcResult result =mockMVC.perform(request)
//						  .andExpect(status().isOk())
//						  .andExpect(content().json("\r\n" + 
//						  		"[{id:1,name:User_1,age:50,address:Hyderabad},{id:2,name:User_2,age:20,"
//						  		+ "address:Hyderabad},"
//						  		+ "{id:3,name:User_3,age:30,address:Hyderabad}]"))
//						  .andReturn();  //AssertEquals
//		//content().json doesnt require exact match.
//
//	}
//	@Test
//	public void createUserTest() throws Exception {
//		User mockUser = new User(1, "User_1", 50,"Hyderabad");
//
//		// UserService.addUser to respond back with mockUser
//		when(
//				UserServ.addUser(
//						Mockito.any(User.class))).thenReturn(mockUser);
//
//		// Send User as body to /save
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//				.post("/save")
//				.accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
//				.contentType(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMVC.perform(requestBuilder).andReturn();
//
//		MockHttpServletResponse response = result.getResponse();
//
//		assertEquals(HttpStatus.OK.value(), response.getStatus());
//		System.out.println(response.getStatus());
//
//	}
//	
//}

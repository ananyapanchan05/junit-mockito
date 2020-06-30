package com.example.Junit_Crud.Junit4_UnitTests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Junit_Crud.controller.UserController_MVC;
import com.example.Junit_Crud.model.User;
import com.example.Junit_Crud.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController_MVC.class)
public class ControllerTests {
	@MockBean
	private UserService UserServ;
	@Autowired
	private MockMvc mockMVC;
	
	String exampleUserJson = "{\"id\":2,\"name\":\"User_2\",\"age\":20,\"address\":\"Pune\"}";

	// Test Get All Users.
	@Test
	public void getAllUsersTest() throws Exception
	{
		when(UserServ.getUsers()).thenReturn
		(Arrays.asList(new User(1,"User_1",50,"Hyderabad"),
				new User(2,"User_2",20,"Hyderabad"),
				new User(3,"User_3",30,"Chennai")
				));
		
		mockMVC.perform( MockMvcRequestBuilders
			      .get("/getAllUsers")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
			      .andExpect(MockMvcResultMatchers.jsonPath("$..id").isEmpty());
			      
	}
	// Test Get Users by ID.
	@Test
	public void getUsersByIdAPI() throws Exception 
	{ 
		when(UserServ.getUserbyId(Mockito.anyInt())).thenReturn
		(new User(1,"User_1",50,"Hyderabad"));
		
	  mockMVC.perform(MockMvcRequestBuilders
	      .get("/users/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("User_1"));
	 }
	
	//Test Post Users
	@Test
	public void createUserAPI() throws Exception 
	{
		User mockUser = new User(1, "User_1", 50,"Hyderabad");

		// UserService.addUser to respond back with mockUser
		when(
			UserServ.addUser(Mockito.any(User.class))).thenReturn(mockUser);

		   mockMVC.perform( MockMvcRequestBuilders
	      .post("/saveUsers")
	      .content(exampleUserJson)
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("User_1"));
	}
	@Test
	public void updateUserAPI() throws Exception 
	{
		
		User updatedMock=new User(2, "User_2", 20,"Pune");
		when(UserServ.updateUser(Mockito.anyInt(),Mockito.any(User.class))).thenReturn(updatedMock);
		//Mockito.when(UserServ.updateUser(2,updatedMock)).thenReturn(updatedMock);
	      mockMVC.perform(MockMvcRequestBuilders
	      .put("/updateUsers/{id}",2)
	      .content(exampleUserJson)
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("User_2"))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(20))
	      .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Pune"));
	}
	@Test
	public void deleteUserAPI() throws Exception 
	{
		UserServ.deleteUser(1);
		verify(UserServ,times(1)).deleteUser(Mockito.anyInt());
	}
	
	
	
	

}

package com.example.Junit_Crud.Junit4_UnitTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Junit_Crud.model.User;
import com.example.Junit_Crud.repo.UserRepository;
import com.example.Junit_Crud.service.UserService;


@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {
	@Mock
	UserRepository UserRepoMock;
	@InjectMocks
	UserService user_serv;
	@Test
	public void getAllUsers()
	{
		
		when(UserRepoMock.findAll()).thenReturn((Arrays.asList(new User(1,"Name_1",10,"Hyd"),
				new User(2,"Name_2",20,"Banglore"),
				new User(3,"Name_3",50,"Pune")
				)));
		
		List<User> usrs=user_serv.getUsers();
		assertEquals(1,usrs.get(0).getId()); // If the first item in the list is equal to getValue.
		assertEquals("Name_2",usrs.get(1).getName()); // If the second item in the list is equal to getValue.
		assertEquals(30,usrs.get(2).getAge());  // If the third item in the list is equal to getValue.
	}
	@Test
	public void getUsersById() throws Exception 
	{ 
		when(UserRepoMock.findById(Mockito.anyInt())).thenReturn
		(new User(1,"User_1",50,"Hyderabad"));
		
		User usr=user_serv.getUserbyId(1);
		
		assertEquals("User_1",usr.getName());
        assertEquals(50,usr.getAge());
     }
	    @Test
	    public void saveUsers()
	    {
	        User u = new User(1,"Lokesh",30,"USA");
	        UserRepoMock.save(u);
	        verify(UserRepoMock, times(1)).save(u);
	    }
	    @Test
	    public void updateUsers()
	    {
	        User u = new User(1,"Lokesh",30,"USA");
	        UserRepoMock.save(u);
	        User u1=new User(1,"Anny",30,"USA");
	        UserRepoMock.save(u1);
	        verify(UserRepoMock, times(1)).save(u1);
	    }
	    
	    
	    @Test
	    public void deleteUsers()
	    {
	    	User u = new User(1,"Lokesh",30,"USA");
	        UserRepoMock.delete(u);
	        verify(UserRepoMock, times(1)).delete(u);
	    }

	

}

package com.example.Junit_Crud.Junit4_UnitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Junit_Crud.model.User;
import com.example.Junit_Crud.repo.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {
	@Autowired
	UserRepository userRepo;
	
	 @Test
	 public void testGetAllUsers() 
	 {
		
		 assertNotNull(userRepo.findAll());
		 List<User> user_list=userRepo.findAll();
		 assertEquals(5,user_list.size());
	 }
	 
	 @Test
	 public void testGetUsersById()
	 {
		 User user_obj=userRepo.findById(1004);
		 assertEquals("Ananya",user_obj.getName());
	 }
	 @Test
	 public void saveUsers()
	 {
	        User u = new User(1,"Lokesh",30,"USA");
	        userRepo.save(u);
	        User gotUser=userRepo.findById(1);
	        assertNotNull(u);
	        assertEquals(gotUser.getName(),u.getName());
	 }
	 @Test
	 public void deleteUser()
	 {
		 assertThat(userRepo.findAll().size(), is(5));
		 User deleted=userRepo.findById(1004);
		 userRepo.delete(deleted);
		 assertThat(userRepo.findAll().size(), is(4));
	 }
}

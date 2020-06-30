package com.example.Junit_Crud.Junit4_IntegrationTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.example.Junit_Crud.JunitCrudApplication;
import com.example.Junit_Crud.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,
				classes=JunitCrudApplication.class)
public class RestTemplateTests {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort       
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testGetAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/fetchUsers",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());
	}

	@Test
	public void testGetUserById() {
		User user = restTemplate.getForObject(getRootUrl() + "/fetchUsers/1001", User.class);
		System.out.println(user.getName());
		Assert.assertNotNull(user);
	}

	@Test
	public void testCreateUser() {
		User user = new User(1008,"User_8",45,"USA");

		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/createUsers", user, User.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePost() {
		int id = 1003;
		User user = restTemplate.getForObject(getRootUrl() + "/fetchUsers/" + id, User.class);
		user.setName("admin1");
		

		restTemplate.put(getRootUrl() + "/putUsers/" + id, user);

		User updatedUser = restTemplate.getForObject(getRootUrl() + "/fetchUsers/" + id, User.class);
		Assert.assertNotNull(updatedUser);
	}

	@Test
	public void testDeletePost() {
		int id = 1002;
		User user = restTemplate.getForObject(getRootUrl() + "/fetchUsers/" + id, User.class);
		Assert.assertNotNull(user);

		restTemplate.delete(getRootUrl() + "/delUser/" + id);

		try {
			user = restTemplate.getForObject(getRootUrl() + "/fetchUsers/" + id, User.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}


}

package com.example.Junit_Crud.Junit4_IntegrationTests;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Junit_Crud.JunitCrudApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,
				classes=JunitCrudApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class Example_integration_test {
	@Autowired
	MockMvc mvc;
	@Test
	public void exampleTest() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
			      .get("/getAllUsers")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
			      .andExpect(MockMvcResultMatchers.jsonPath("$..id").isNotEmpty());
			      
	}

}

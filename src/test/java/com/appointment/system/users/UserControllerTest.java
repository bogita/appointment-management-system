package com.appointment.system.users;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private UserController userController;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	private ParameterizedTypeReference<List<User>> requestType = new ParameterizedTypeReference<List<User>>(){};
	private ParameterizedTypeReference<User> singleReturnType = new ParameterizedTypeReference<User>(){};
	private String baseUrl;

	@Before
	public void setup(){
		this.baseUrl="http://localhost:" + port + "/user";
	}

	@Test
	public void contextLoads() throws Exception{
		assertThat(userController).isNotNull();
	}

	@Test
	public void testUserCRUD_Operations(){
		User user = getTestUSer();

		User newUser = this.restTemplate.postForObject(this.baseUrl+"/create", new HttpEntity<>(user), User.class);
		AssertionsForClassTypes.assertThat(newUser.getId()).isNotNull();

		newUser.setFirst_name("Bobby");
		ResponseEntity<User> updatedUser = this.restTemplate.exchange(this.baseUrl+"/update", HttpMethod.PUT, new HttpEntity<>(newUser), singleReturnType);
		Assert.assertEquals(newUser, updatedUser.getBody());

		ResponseEntity<List<User>> users = this.restTemplate.exchange(this.baseUrl+"/all", HttpMethod.GET, null, requestType);
		Assert.assertEquals(1, users.getBody().size());

		User acquiredUser = this.restTemplate.getForObject(this.baseUrl+"/"+updatedUser.getBody().getId(), User.class);
		Assert.assertEquals(newUser.getId(), acquiredUser.getId());

		this.restTemplate.delete(this.baseUrl+"/delete/"+acquiredUser.getId(), HttpMethod.DELETE, null);


	}

	private User getTestUSer(){
		return new User("Hiram", "Njogu", "M", "userTest@gmail.com", "254-555-5555", 21);
	}

}

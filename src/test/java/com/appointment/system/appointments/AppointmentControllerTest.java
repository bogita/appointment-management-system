package com.appointment.system.appointments;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import java.util.List;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppointmentControllerTest {

	@Autowired
	private AppointmentController appointmentController;

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	private ParameterizedTypeReference<List<Appointment>> requestType = new ParameterizedTypeReference<List<Appointment>>(){};
	private ParameterizedTypeReference<Appointment> singleReturnType = new ParameterizedTypeReference<Appointment>(){};
	private String baseUrl;

	@Before
	public void setup(){
		this.baseUrl="http://localhost:" + port + "/appointments";
	}

	@Test
	public void contextLoads() throws Exception{
		assertThat(appointmentController).isNotNull();
	}

	@Test
	public void testAppointmentCRUD_Operations() throws Exception {
		Appointment apt =  getTestAppointment();

		Appointment newApt = this.restTemplate.postForObject(this.baseUrl+"/create", new HttpEntity<>(apt), Appointment.class);
		assertThat(newApt.getId()).isNotNull();

		newApt.setDescription("Updating appointment record");
		ResponseEntity<Appointment> updatedApt = this.restTemplate.exchange(this.baseUrl+"/update", HttpMethod.PUT, new HttpEntity<>(newApt), singleReturnType);
		Assert.assertEquals(newApt, updatedApt.getBody());

		ResponseEntity<List<Appointment>> appointments = this.restTemplate.exchange(this.baseUrl+"/all", HttpMethod.GET, null, requestType);
		Assert.assertEquals(1, appointments.getBody().size());

		Appointment acquiredApt = this.restTemplate.getForObject(this.baseUrl+"/"+updatedApt.getBody().getId(), Appointment.class);
		Assert.assertEquals(newApt.getId(), acquiredApt.getId());

		this.restTemplate.delete(this.baseUrl+"/delete/"+acquiredApt.getId(), HttpMethod.DELETE, null);

	}

	private Appointment getTestAppointment(){
		return new Appointment(
				LocalDateTime.now(),
				LocalDateTime.now().plusDays(2),
				"Hiram",
				"Testing",
				"Appointment test",
				"none");
	}

}

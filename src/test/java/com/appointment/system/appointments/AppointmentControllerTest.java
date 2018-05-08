package com.appointment.system.appointments;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
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
	public void testCreatingAppointment() throws Exception {
		Appointment apt =  getTestAppointment();
		HttpEntity<Appointment> request = new HttpEntity<>(apt);
		assertThat(
				this.restTemplate.postForObject(this.baseUrl+"/create", request, Appointment.class)
		).isNotNull();
	}

	private Appointment getTestAppointment(){
		return new Appointment(
				Date.valueOf("2018-05-08"),
				Date.valueOf("2018-05-08"),
				"Hiram",
				"Testing",
				"Appointment test",
				"none");
	}


}

package com.wubi.nasathingy.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EarthDateControllerTest {
	private static final String REQUIRED_EARTH_DATES = "{\"requiredEarthDates\":[\"2017-02-27\",\"2018-06-02\",\"2016-07-13\",\"2018-05-01\"]}";
	@LocalServerPort
    private int port;

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	void testGetRequiredEarthDates() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" +  port + "/requiredearthdates", String.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), REQUIRED_EARTH_DATES);
	}

}

package com.example.demo;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
class SpringbootApplicationTests {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Value("${local.management.port}")
	private int mgt;

	@Test
	public void shouldReturn200WhenSendingRequestToController() throws Exception {
	  @SuppressWarnings("rawtypes")
	  ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
		  "http://localhost:" + this.port + "/greeting", Map.class);
  
	  then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
  
	@Test
	public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
	  @SuppressWarnings("rawtypes")
	  ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
		  "http://localhost:" + this.mgt + "/actuator/info", Map.class);
 
	  then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}

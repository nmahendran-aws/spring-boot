package com.example.demo.restservice;

import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.restservice.hateoas.GreetingHal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final AtomicLong counter = new AtomicLong();
    private static final String TEMPLATE = "Hello, %s!";

    @GetMapping("/hello")
	public String hello(@RequestParam(value="name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format("Hello %s!", name));
    }
    
    @RequestMapping("/greetingHal")
	public HttpEntity<GreetingHal> greetingHal(
		@RequestParam(value = "name", defaultValue = "World1") String name) {
            GreetingHal greeting = new GreetingHal(String.format(TEMPLATE, name));
            greeting.add(linkTo(methodOn(Controller.class).greetingHal(name)).withSelfRel());
            return new ResponseEntity<GreetingHal>(greeting, HttpStatus.OK);
	}
}

package com.example.demo.restservice.hateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.hateoas.RepresentationModel;

public class GreetingHal extends RepresentationModel<GreetingHal> {

    private final String content;

	@JsonCreator
	public GreetingHal(@JsonProperty("content") String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}

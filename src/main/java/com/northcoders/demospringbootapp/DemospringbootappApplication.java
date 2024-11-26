package com.northcoders.demospringbootapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.demospringbootapp.controller.DemoController;
import com.northcoders.demospringbootapp.model.Person;
import com.northcoders.demospringbootapp.model.SunsetAndSunriseResults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootApplication
public class DemospringbootappApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(DemospringbootappApplication.class, args);
		DemoController.getCityName();

	}

}

package com.northcoders.demospringbootapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.demospringbootapp.model.CoordinatesResults;
import com.northcoders.demospringbootapp.model.Person;
import com.northcoders.demospringbootapp.model.SunsetAndSunriseResults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DemoController {

    @GetMapping("/hello")
    public String greeterController(){
        return "Hello there!";
    }

    @GetMapping("/person")
    public static List<Person> personListController(){
        return List.of(new Person("Bob",34,"bob@gmail.com","Sweden","Pizza"),
                new Person("Bill", 21, "bill@hotmail.co.uk","USA","Burger"));

    }

    @GetMapping("/get-location")
    public static String getCoordinatesAPI(@RequestParam String cityName) throws JsonProcessingException {
        CoordinatesResults coordinatesResults = getCoordinates(cityName);
        return "Latitude: " + coordinatesResults.results().getFirst().latitude() +
                "\nLongitude: " + coordinatesResults.results().getFirst().longitude();
    }

    public static CoordinatesResults getCoordinates(String cityName) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String Base_Url = "https://geocoding-api.open-meteo.com/v1/search";
        WebClient webClient = WebClient.builder().baseUrl(Base_Url).build();
        String location = webClient
                .get()
                .uri("?name=" + cityName + "&count=1")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return mapper.readValue(location, CoordinatesResults.class);
    }

    @GetMapping("/get-sunrise-and-sunset")
    public static String getSunsetAndSunriseAPI(@RequestParam float lat, @RequestParam float lng) throws JsonProcessingException {
        SunsetAndSunriseResults sunsetAndSunriseResults = getSunsetAndSunrise(lat, lng);
        return "Sunrise: " + sunsetAndSunriseResults.results().sunrise() +
                "\nSunrise: " + sunsetAndSunriseResults.results().sunset();
    }

    public static SunsetAndSunriseResults getSunsetAndSunrise(float latitude, float longitude) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String Base_Url = "https://api.sunrisesunset.io/json";
        WebClient webClient = WebClient.builder().baseUrl(Base_Url).build();
        String location = webClient
                .get()
                .uri("?lat=" + latitude + "&lng=" + longitude)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return mapper.readValue(location, SunsetAndSunriseResults.class);
    }


}


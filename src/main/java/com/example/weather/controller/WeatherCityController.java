package com.example.weather.controller;

import com.example.weather.model.WeatherCity;
import com.example.weather.service.WeatherCityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class WeatherCityController {

    private final WeatherCityServiceImpl weatherCityService;

    public WeatherCityController(WeatherCityServiceImpl weatherCityService) {
        this.weatherCityService = weatherCityService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public WeatherCity getWeatherCity(@RequestParam("city") String city) throws IOException, InterruptedException {
        return weatherCityService.getWeatherCity(city);
    }
}

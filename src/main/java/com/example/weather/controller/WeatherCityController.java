package com.example.weather.controller;

import com.example.weather.model.WeatherCity;
import com.example.weather.service.WeatherCityServiceImpl;
import org.hibernate.HibernateException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

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

    @ExceptionHandler({PropertyValueException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> valueException(HibernateException e) {
        return Map.of("message", "city not find");
    }
}

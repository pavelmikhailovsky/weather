package com.example.weather.service;

import com.example.weather.model.WeatherCity;

import java.io.IOException;

public interface WeatherCityService {

    WeatherCity getWeatherCity(String city) throws IOException, InterruptedException;
}

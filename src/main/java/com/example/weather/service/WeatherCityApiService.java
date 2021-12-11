package com.example.weather.service;

import com.example.weather.model.WeatherCity;

import java.io.IOException;

public interface WeatherCityApiService {

    WeatherCity getCityFromApi(String city) throws IOException, InterruptedException;
}

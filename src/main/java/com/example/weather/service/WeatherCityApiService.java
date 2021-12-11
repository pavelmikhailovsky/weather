package com.example.weather.service;

import com.example.weather.model.WeatherCity;

public interface WeatherCityApiService {

    WeatherCity getCityFromApi(String city);
}

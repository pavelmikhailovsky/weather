package com.example.weather.dao;

import com.example.weather.model.WeatherCity;

public interface WeatherCityDao {

    void create(WeatherCity weatherCity);

    void update(WeatherCity weatherCity);

    WeatherCity get(String city);
}

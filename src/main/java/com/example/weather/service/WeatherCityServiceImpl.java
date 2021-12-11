package com.example.weather.service;

import com.example.weather.dao.WeatherCityDao;
import com.example.weather.model.WeatherCity;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WeatherCityServiceImpl implements WeatherCityService {

    private final WeatherCityApiService weatherCityApiService;
    private final WeatherCityDao weatherCityDao;

    public WeatherCityServiceImpl(WeatherCityApiService weatherCityApiService, WeatherCityDao weatherCityDao) {
        this.weatherCityApiService = weatherCityApiService;
        this.weatherCityDao = weatherCityDao;
    }

    @Override
    public WeatherCity getWeatherCity(String city) throws IOException, InterruptedException {
        WeatherCity weatherCity;

        try {
            weatherCity = weatherCityDao.get(city);
        } catch (HibernateException e) {
            weatherCity = weatherCityApiService.getCityFromApi(city);
            weatherCityDao.create(weatherCity);
            return weatherCity;
        }

        return weatherCity;
    }
}

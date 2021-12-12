package com.example.weather.service;

import com.example.weather.dao.WeatherCityDao;
import com.example.weather.model.WeatherCity;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.io.IOException;

@Service
public class WeatherCityServiceImpl implements WeatherCityService {

    private final WeatherCityApiService weatherCityApiService;
    private final WeatherCityDao weatherCityDao;
    private final UpdatingInformationDatabaseService updatingInformationDatabaseService;

    public WeatherCityServiceImpl(WeatherCityApiService weatherCityApiService, WeatherCityDao weatherCityDao, UpdatingInformationDatabaseService upd) {
        this.weatherCityApiService = weatherCityApiService;
        this.weatherCityDao = weatherCityDao;
        this.updatingInformationDatabaseService = upd;

    }

    @Override
    public WeatherCity getWeatherCity(String city) throws IOException, InterruptedException {
        updatingInformationDatabaseService.updating();
        WeatherCity weatherCity;

        try {
            weatherCity = weatherCityDao.get(city);
        } catch (PersistenceException e) {
            weatherCity = weatherCityApiService.getCityFromApi(city);
            weatherCityDao.create(weatherCity);
            return weatherCity;
        }

        return weatherCity;
    }
}

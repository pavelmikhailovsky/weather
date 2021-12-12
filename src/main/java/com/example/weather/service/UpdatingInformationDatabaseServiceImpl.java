package com.example.weather.service;

import com.example.weather.dao.WeatherCityDao;
import com.example.weather.model.WeatherCity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UpdatingInformationDatabaseServiceImpl implements UpdatingInformationDatabaseService {

    private final WeatherCityDao weatherCityDao;
    private final WeatherCityApiService weatherCityApiService;
    private LocalDateTime currentTime;
    private LocalDateTime timeUpgrade;

    public UpdatingInformationDatabaseServiceImpl(WeatherCityDao weatherCityDao, WeatherCityApiService weatherCityApiService) {
        this.weatherCityDao = weatherCityDao;
        this.weatherCityApiService = weatherCityApiService;
        setNewTime();
    }

    @Override
    public void updating() throws IOException, InterruptedException {
        if (currentTime.isAfter(timeUpgrade)) {
            setNewTime();
            runUpgradeProcess();
        }
    }

    private void setNewTime() {
        currentTime = LocalDateTime.now();
        timeUpgrade = LocalDateTime.of(
                currentTime.getYear(),
                currentTime.getMonth(),
                currentTime.getDayOfMonth(),
                currentTime.getHour() + 3,
                currentTime.getMinute(),
                currentTime.getSecond(),
                currentTime.getNano()
        );
    }

    private void runUpgradeProcess() throws IOException, InterruptedException {
        List<WeatherCity> cities = weatherCityDao.getAll();

        for (WeatherCity weatherCity : cities) {
            WeatherCity upgradeWeatherCity = weatherCityApiService.getCityFromApi(weatherCity.getCityName());
            weatherCityDao.update(upgradeWeatherCity);
        }

    }
}

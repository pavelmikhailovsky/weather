package com.example.weather.service;

import com.example.weather.dao.WeatherCityDao;
import com.example.weather.model.WeatherCity;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
public class WeatherCityServiceTests {

    @Mock
    private WeatherCityApiService weatherCityApiService;

    @Mock
    private WeatherCityDao weatherCityDao;

    @InjectMocks
    private WeatherCityServiceImpl weatherCityService;

    @Test
    void itShouldReturnWeatherCityWhichIsInDb() throws Exception {
        given(weatherCityDao.get(eq("London"))).willReturn(
                new WeatherCity("London", "clouds",
                        "1", "1", "1", "1", "1", "1", "1")
        );
        WeatherCity weatherCity = weatherCityService.getWeatherCity("London");
        assertThat(weatherCity.getCityName()).isEqualTo("London");
    }

    @Test
    void cityShouldBeAddedToDbIfDoesNotExist() throws Exception {
        given(weatherCityDao.get(anyString())).willThrow(HibernateException.class);
        given(weatherCityApiService.getCityFromApi(eq("Minsk"))).willReturn(
                new WeatherCity("Minsk", "snow",
                        "1", "1", "1", "1", "1", "1", "1")
        );

        WeatherCity weatherCity = weatherCityService.getWeatherCity("Minsk");
        assertThat(weatherCity.getCityName()).isEqualTo("Minsk");
    }
}

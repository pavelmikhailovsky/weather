package com.example.weather.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "City")
public class WeatherCity {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(strategy = "increment", name = "increment")
    private Long id;

    @Column(name = "city_name", unique = true, nullable = false)
    private String cityName;

    @Column(name = "weather_description", nullable = false)
    private String weatherDescription;

    @Column(name = "feels_like", nullable = false)
    private String feelsLike;

    @Column(name = "temp_max", nullable = false)
    private String tempMax;

    @Column(name = "pressure", nullable = false)
    private String pressure;

    @Column(name = "humidity", nullable = false)
    private String humidity;

    @Column(name = "wind_speed", nullable = false)
    private String windSpeed;

    @Column(name = "gust", nullable = false)
    private String gust;

    public WeatherCity() {}

    public WeatherCity(String cityName, String weatherDescription, String feelsLike, String tempMax,
                       String pressure, String humidity, String windSpeed, String gust) {
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.feelsLike = feelsLike;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.gust = gust;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getGust() {
        return gust;
    }

    public void setGust(String gust) {
        this.gust = gust;
    }
}

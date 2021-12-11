package com.example.weather.service;

import com.example.weather.model.WeatherCity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class WeatherCityApiServiceImpl implements WeatherCityApiService {

    @Value("${x-rapidapi-key.value}")
    private String key;

    @Override
    public WeatherCity getCityFromApi(String city) throws IOException, InterruptedException {
        HttpResponse<String> response;
        city = city.trim();
        String firstLetter = city.substring(0, 1);
        boolean isUpperCaseLetter = city.substring(0, 1).toUpperCase().equals(firstLetter);

        if (!isUpperCaseLetter) {
            String correctCityName = firstLetter.toUpperCase() + city.substring(1);
            response = responseApi(correctCityName);
        } else {
            response = responseApi(city);
        }

        Map<String, String> map = convertStringToMap(response.body());
        return new WeatherCity(
                map.get("name"),
                map.get("description"),
                map.get("feels_like"),
                map.get("temp_max"),
                map.get("pressure"),
                map.get("humidity"),
                map.get("speed")
        );
    }

    private HttpResponse<String> responseApi(String city) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://community-open-weather-map.p.rapidapi.com/weather?q="+city+"&lat=0&lon=0&lang=null&units=metric"))
                .header("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .header("x-rapidapi-key", key)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    private Map<String, String> convertStringToMap(String response) {
        List<String[]> list = Stream.of(response.split(","))
                .map((str) -> {
                    String string = StringUtils.remove(str, "{");
                    string = StringUtils.remove(string, "}");
                    string = StringUtils.remove(string, "[");
                    string = StringUtils.remove(string, "]");
                    string = StringUtils.remove(string, "\"");
                    string = StringUtils.remove(string, "\"main\":");
                    string = StringUtils.remove(string, "\"wind\":");
                    return string;
                })
                .filter(str -> StringUtils.startsWithAny(str, "description", "temp", "feels_like", "temp_min", "temp_max", "pressure", "humidity", "speed", "name"))
                .map(str -> StringUtils.split(str, ':')).toList();

        Map<String, String> map = new HashMap<>();

        for (String[] s1: list) {
            map.put(s1[0], s1[1]);
        }

        return map;
    }
}

package com.example.weather.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-integrationtest.properties")
@Sql(value = {"/script.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class WeatherCityControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void givenWeatherCityWithCorrectCityName() throws Exception {
        mockMvc.perform(get("/api/v1/?city=Tokyo")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].cityName", containsInAnyOrder("Tokyo")));
    }

}

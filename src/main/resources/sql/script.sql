CREATE TABLE city
(
    city_id BIGSERIAL PRIMARY KEY,
    city_name VARCHAR(150) UNIQUE NOT NULL,
    weather_description VARCHAR(100) NOT NULL,
    feels_like VARCHAR(100) NOT NULL,
    temp_max VARCHAR(100) NOT NULL,
    pressure VARCHAR(100) NOT NULL,
    humidity VARCHAR(100) NOT NULL,
    wind_speed VARCHAR(100) NOT NULL,
    gust VARCHAR(100) NOT NULL
);
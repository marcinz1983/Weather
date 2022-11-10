package com.example.pogoda;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebClient_Weather {
    public static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static final String API_KEY = "b1158a041000904f17c5556719dcec91";
    RestTemplate restTemplate = new RestTemplate();


    public <T> T getWeatherForCity(String city,Class<T> responseType) {
        return restTemplate.getForObject(WEATHER_URL+"{city}&appid={api_key}&units=metric&lang=pl",
                responseType, city,API_KEY);
    }

}

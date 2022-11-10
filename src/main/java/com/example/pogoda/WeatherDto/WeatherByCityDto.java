package com.example.pogoda.WeatherDto;

import lombok.Getter;

import java.util.List;

@Getter
public class WeatherByCityDto {
    private String name;
    private List<WeatherDescriptionDto> weather;
    private WeatherMainDto main;
    private WeatherWindDto wind;


}

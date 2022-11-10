package com.example.pogoda.WeatherDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherDtoResponse {
    private String miasto;
    private String zachmurzenie;
    private float temperatura;
    private float wiatr;
    private  int cisnienie;
    private  int wilgotnosc;
}

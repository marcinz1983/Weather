package com.example.pogoda;

import com.example.pogoda.Entity.WeatherEntity;
import com.example.pogoda.Repository.WeatherRepository;
import com.example.pogoda.WeatherDto.WeatherByCityDto;
import com.example.pogoda.WeatherDto.WeatherDtoResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class WeatherController {


    WeatherService weatherService;



    @GetMapping("/weather/{city}")
    public WeatherDtoResponse getWeather(@PathVariable String city){
        var result = weatherService.getWeatherByCity(city);

        return result;
    }
    @GetMapping
    public List<WeatherDtoResponse> getAllWeatherRecords(){
        return weatherService.getAllWeatherRecords();
    }
}

package com.example.pogoda;

import com.example.pogoda.Entity.WeatherEntity;
import com.example.pogoda.Repository.WeatherRepository;
import com.example.pogoda.WeatherDto.WeatherByCityDto;
import com.example.pogoda.WeatherDto.WeatherDtoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.sql.Array;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {


    private final WebClient_Weather webClient_weather;
    private final WeatherRepository weatherRepository;

    List<String> cities = Arrays.asList("Zakopane");


    // @PostConstruct
    @Scheduled(fixedRate = 100 * 1000)
    private void saveWeatherAnyOneMinute() {
        for (String city : cities)
            SaveWeatherToDB(city);
        //Thread.sleep(60*1000);
    }

    public void SaveWeatherToDB(String city) {
        var response = webClient_weather.getWeatherForCity(city, WeatherByCityDto.class);
        saveToDb(response);
    }

    public WeatherDtoResponse getWeatherByCity(String city) {
        var response = webClient_weather.getWeatherForCity(city, WeatherByCityDto.class);
        log.info(response.toString());
        return mapToResponse(response);
    }

    public List<WeatherDtoResponse> getAllWeatherRecords() {
        var response = weatherRepository.findAll();
        return response.stream()
                .map(w -> mapToResponseFromEntity(w))
                .collect(Collectors.toList());
    }

    private void saveToDb(WeatherByCityDto response) {
        var toSave = WeatherEntity.builder()
                .withMiasto(response.getName())
                .withZachmurzenie(response.getWeather().get(0).getDescription())
                .withTemperatura(response.getMain().getTemp())
                .withWiatr(response.getWind().getSpeed())
                .withCisnienie(response.getMain().getPressure())
                .withWilgotnosc(response.getMain().getHumidity())
                //.withCzas(new Date(System.currentTimeMillis()))
                .withCzas(DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss").format(LocalDateTime.now()))
                .build();
        weatherRepository.save(toSave);
    }

    private WeatherDtoResponse mapToResponseFromEntity(WeatherEntity entity) {
        return WeatherDtoResponse.builder()
                .miasto(entity.getMiasto())
                .temperatura(entity.getTemperatura())
                .cisnienie(entity.getCisnienie())
                .wiatr(entity.getWiatr())
                .zachmurzenie(entity.getZachmurzenie())
                .wilgotnosc(entity.getWilgotnosc())
                .build();
    }

    private WeatherDtoResponse mapToResponse(WeatherByCityDto response) {
        return WeatherDtoResponse.builder()
                .miasto(response.getName())
                .zachmurzenie(response.getWeather().get(0).getDescription())
                .temperatura(response.getMain().getTemp())
                .wiatr(response.getWind().getSpeed())
                .cisnienie(response.getMain().getPressure())
                .wilgotnosc(response.getMain().getHumidity())
                .build();
    }


}

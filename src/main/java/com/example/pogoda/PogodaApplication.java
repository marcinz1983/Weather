package com.example.pogoda;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PogodaApplication {



    public static void main(String[] args) {
        SpringApplication.run(PogodaApplication.class, args);
//        PogodaApplication pa = new PogodaApplication();
//        pa.weatherService.SaveWeatherToDB("krakow");
    }

}

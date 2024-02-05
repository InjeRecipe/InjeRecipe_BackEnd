package com.example.injerecipe.controller;

import com.example.injerecipe.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;
    @PostMapping("/get")
    void getWeather(@RequestParam String lat, @RequestParam String lon){
        weatherService.getWeatherString(lat, lon);
    }
}

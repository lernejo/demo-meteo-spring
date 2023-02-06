package fr.lernejo.meteo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MeteoController {

    private final MeteoService meteoService;

    MeteoController(MeteoService meteoService) {
        this.meteoService = meteoService;
    }

    @GetMapping("/api/meteo/temperature")
    double getTemperature(Position position) {
        return meteoService.getTemperature(position);
    }
}

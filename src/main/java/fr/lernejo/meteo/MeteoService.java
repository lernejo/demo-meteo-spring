package fr.lernejo.meteo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MeteoService {

    private final Logger logger = LoggerFactory.getLogger(MeteoService.class);

    private final MeteoClient client;

    public MeteoService(MeteoFranceClient client) {
        this.client = new RetryMeteoClient(client);
    }

    public double getTemperature(Position position) {
        if (!position.isValid()) {
            logger.warn("Position invalide: " + position);
            throw new InvalidPositionException(position);
        }
        logger.info("Role de l'utilisateur connecté: contributor");
        TemperatureResult temperatureResult = client
            .get(position.x(), position.y());
        return temperatureResult.temperaturesOverLastHour().get(0);
    }
}

package fr.lernejo.meteo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UncheckedIOException;

public class RetryMeteoClient implements MeteoClient {

    private final Logger logger = LoggerFactory.getLogger(RetryMeteoClient.class);

    private final MeteoClient delegate;

    public RetryMeteoClient(MeteoClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public TemperatureResult get(double latitude, double longitude) {
        long startTime = System.currentTimeMillis();
        int count = 0;
        RuntimeException storedEx = null;
        do {
            try {
                TemperatureResult temperatureResult = delegate.get(latitude, longitude);
                logger.info("Successful call to meteo client after " + (count + 1) + " attempts in " + (startTime - System.currentTimeMillis()) + " ms");
                return temperatureResult;
            } catch (UncheckedIOException e) {
                count++;
                storedEx = e;
            }
        } while (count < 3);
        logger.warn("Failed to call meteo client after max attempts (" + count + ")");
        throw storedEx;
    }
}

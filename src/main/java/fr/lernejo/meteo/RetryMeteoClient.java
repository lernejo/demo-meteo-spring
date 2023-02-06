package fr.lernejo.meteo;

import java.io.UncheckedIOException;

public class RetryMeteoClient implements MeteoClient {
    private final MeteoClient delegate;

    public RetryMeteoClient(MeteoClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public TemperatureResult get(double latitude, double longitude) {
        int count = 0;
        RuntimeException storedEx = null;
        do {
            try {
                return delegate.get(latitude, longitude);
            } catch (UncheckedIOException e) {
                count++;
                storedEx = e;
            }
        } while (count < 3);
        throw storedEx;
    }
}

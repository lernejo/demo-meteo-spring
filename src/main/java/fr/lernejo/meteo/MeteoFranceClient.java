package fr.lernejo.meteo;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Component
class MeteoFranceClient implements MeteoClient {

    private final Random random = new Random();

    public TemperatureResult get(double latitude, double longitude) {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(100) + 100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (random.nextInt(100) > 80) {
            throw new UncheckedIOException(new IOException("MeteoFrance down"));
        }
        return new TemperatureResult(
            UUID.randomUUID().toString(),
            IntStream.range(0, 60)
                .mapToDouble(i -> random.nextDouble())
                .boxed()
                .toList()
        );
    }
}

package fr.lernejo.meteo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyDouble;

class RetryMeteoClientTest {

    @Test
    void client_works_the_first_time() {
        MeteoClient meteoClient = Mockito.mock(MeteoClient.class);
        RetryMeteoClient retryMeteoClient = new RetryMeteoClient(meteoClient);

        TemperatureResult expectedResult = new TemperatureResult("toto", List.of());
        Mockito
            .when(meteoClient.get(anyDouble(), anyDouble()))
            .thenReturn(expectedResult);

        TemperatureResult actualResult = retryMeteoClient.get(-1000, 10);

        Assertions.assertThat(actualResult)
            .isSameAs(expectedResult);

        Mockito.verify(meteoClient, Mockito.times(1)).get(anyDouble(), anyDouble());
    }

    @Test
    void client_does_not_work_at_all() {
        MeteoClient meteoClient = Mockito.mock(MeteoClient.class);
        RetryMeteoClient retryMeteoClient = new RetryMeteoClient(meteoClient);

        Mockito
            .when(meteoClient.get(anyDouble(), anyDouble()))
            .thenThrow(new UncheckedIOException(new IOException("test")));

        Assertions.assertThatExceptionOfType(UncheckedIOException.class)
            .isThrownBy(() -> retryMeteoClient.get(0, 0));

        Mockito.verify(meteoClient, Mockito.times(3)).get(anyDouble(), anyDouble());
    }

    @Test
    void client_does_not_work_the_first_time() {
        MeteoClient meteoClient = Mockito.mock(MeteoClient.class);
        RetryMeteoClient retryMeteoClient = new RetryMeteoClient(meteoClient);

        TemperatureResult expectedResult = new TemperatureResult("toto", List.of());
        Mockito
            .when(meteoClient.get(anyDouble(), anyDouble()))
            .thenThrow(new UncheckedIOException(new IOException("test")))
            .thenReturn(expectedResult);

        TemperatureResult actualResult = retryMeteoClient.get(-1000, 10);

        Assertions.assertThat(actualResult)
            .isSameAs(expectedResult);

        Mockito.verify(meteoClient, Mockito.times(2)).get(anyDouble(), anyDouble());
    }
}

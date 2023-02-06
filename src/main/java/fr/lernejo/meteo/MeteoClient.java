package fr.lernejo.meteo;

public interface MeteoClient {

    TemperatureResult get(double latitude, double longitude);
}

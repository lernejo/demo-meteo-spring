package fr.lernejo.meteo;

import java.util.List;

public record TemperatureResult(String descr, List<Double> temperaturesOverLastHour) {
}

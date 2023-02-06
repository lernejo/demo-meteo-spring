package fr.lernejo.meteo;

public record Position(double x, double y) {
    public boolean isValid() {
        return x >= -90 && x <= 90 && y >= -180 && y <= 180;
    }
}

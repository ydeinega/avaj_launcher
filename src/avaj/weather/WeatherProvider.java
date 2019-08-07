package avaj.weather;

import avaj.aircraft.Coordinates;

import java.util.Random;

public final class WeatherProvider {
    private static final WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = new String[] {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getWeatherProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        long seed = coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude();
        Random random = new Random(seed);
        return (weather[random.nextInt(4)]);
    }
}

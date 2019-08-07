package avaj.weather;

import avaj.aircraft.Coordinates;
import avaj.aircraft.Flyable;

public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getWeatherProvider().getCurrentWeather(coordinates);
    }
    void changeWeather() {
        super.conditionsChanged();
    }

    @Override
    public void register(Flyable flyable) {
        super.register(flyable);
        flyable.registerTower(this);
    }
}

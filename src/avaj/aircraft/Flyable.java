package avaj.aircraft;

import avaj.weather.WeatherTower;

public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
    public String getInfo();
}

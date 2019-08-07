package avaj.aircraft;

import avaj.weather.Simulator;
import avaj.weather.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private static final String type = "Helicopter";
    private static final Map<String, String> hashMap;
    static {
        hashMap = new HashMap<>();
        hashMap.put("RAIN", "It's raining. Hope we can avoid storm.");
        hashMap.put("FOG", "Can you see anything?.");
        hashMap.put("SUN", "This is hot.");
        hashMap.put("SNOW", "My rotor is going to freeze!");
    }

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        Simulator.logFile.addLog(getInfo() + ": " + hashMap.get(weather));
        switch (weather) {
            case "SUN":
                coordinates.changeLongitude(10);
                coordinates.changeHeight(2);
                break;

            case "RAIN":
                coordinates.changeLongitude(5);
                break;

            case "FOG":
                coordinates.changeLongitude(1);
                break;

            case "SNOW":
                if (!coordinates.changeHeight(-12)) {
                    land();
                    weatherTower.unregister(this);
                }
                break;
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    @Override
    public String getInfo() {
        return (type + "#" + getName() + "(" + getId() + ")");
    }

    public static String getType() {
        return type;
    }

    private void land() {
        Simulator.logFile.addLog(getInfo() + " landing. Coordinates: " + coordinates.getLongitude() + " " + coordinates.getLatitude() + " " + coordinates.getHeight());
    }
}

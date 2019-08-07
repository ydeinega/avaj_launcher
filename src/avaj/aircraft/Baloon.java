package avaj.aircraft;

import java.util.HashMap;
import java.util.Map;

import avaj.weather.Simulator;
import avaj.weather.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private static final String type = "Baloon";
    private static final Map<String, String> hashMap;
    static {
        hashMap = new HashMap<>();
        hashMap.put("RAIN", "Damn you rain! You messed up my baloon.");
        hashMap.put("FOG", "So foggy! Can't see anything.");
        hashMap.put("SUN", "Let's enjoy weather and take some pics.");
        hashMap.put("SNOW", "It's snowing. We're gonna crash.");
    }


    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @java.lang.Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        Simulator.logFile.addLog(getInfo() + ": " + hashMap.get(weather));
        switch (weather) {

            case "SUN":
                coordinates.changeLongitude(2);
                coordinates.changeHeight(4);
                break;

            case "RAIN":
                if (!coordinates.changeHeight(-5)) {
                    land();
                    weatherTower.unregister(this);
                }
                break;

            case "FOG":
                if (!coordinates.changeHeight(-3)) {
                    land();
                    weatherTower.unregister(this);
                }
                break;

            case "SNOW":
                if (!coordinates.changeHeight(-15)) {
                    land();
                    weatherTower.unregister(this);
                }
                break;
        }
    }

    @java.lang.Override
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

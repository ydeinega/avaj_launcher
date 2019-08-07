package avaj.aircraft;

import avaj.weather.Simulator;
import avaj.weather.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    private static final String type = "JetPlane";
    private static final Map<String, String> hashMap;
    static {
        hashMap = new HashMap<>();
        hashMap.put("RAIN", "It's raining. Better watch out for lighting.");
        hashMap.put("FOG", "It's a little bit foggy. No need to worry.");
        hashMap.put("SUN", "Loving this kind of weather.");
        hashMap.put("SNOW", "OMG! Winter is coming!");
    }

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        Simulator.logFile.addLog(getInfo() + ": " + hashMap.get(weather));
        switch (weather) {
            case "SUN":
                coordinates.changeLatitude(10);
                coordinates.changeHeight(2);
                break;

            case "RAIN":
                coordinates.changeLatitude(5);
                break;

            case "FOG":
                coordinates.changeLatitude(1);
                break;

            case "SNOW":
                if (!coordinates.changeHeight(-7)) {
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

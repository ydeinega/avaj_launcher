package avaj.aircraft;

public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        if (type.compareToIgnoreCase("baloon") == 0)
            return new Baloon(name, new Coordinates(longitude, latitude, height));
        else if (type.compareToIgnoreCase("helicopter") == 0)
            return new Helicopter(name, new Coordinates(longitude, latitude, height));
        else if (type.compareToIgnoreCase("jetplane") == 0)
            return new JetPlane(name, new Coordinates(longitude, latitude, height));
        return null;
    }
}

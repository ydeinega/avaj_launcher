package avaj.aircraft;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

    public void changeLongitude(int longitude_delta) {
        this.longitude += longitude_delta;
    }

    public void changeLatitude(int latitude_delta) {
        this.latitude += latitude_delta;
    }

    public boolean changeHeight(int height_delta) {
        if (this.height + height_delta <= 0) {
            this.height = 0;
            return false;
        }
        else if (this.height + height_delta >= 100)
            this.height = 100;
        else
            this.height += height_delta;
        return true;
    }
}

package avaj.weather;

import avaj.aircraft.AircraftFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {
    private static WeatherTower tower;
    public static LogFile logFile;

    public static void main(String[] args) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            if (line != null) {
                tower = new WeatherTower();
                logFile = new LogFile();
                int numberOfSimulations = Integer.parseInt(line.split(" ")[0]);
                if (numberOfSimulations < 0) {
                    throw new Exception("Invalid number of simulations " + numberOfSimulations);
                }
                while ((line = reader.readLine()) != null) {
                    if (valid_line(line))
                        tower.register(AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                            Integer.parseInt(line.split(" ")[4])));
                }
                for (int i = 0; i < numberOfSimulations; i++) {
                    tower.changeWeather();
                }
                logFile.closeLogFile();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File " + args[0] + " not found");
        }
        catch (IOException e) {
            System.out.println("File " + args[0] + " couldn't be read");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please, provide the name of the simulation file");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static boolean valid_line(String line) throws Exception {

        if (line.length() - line.replaceAll(" ", "").length() != 4)
            throw new Exception("Information format should be provided in the following format: TYPE NAME LONGITUDE LATITUDE HEIGHT");
        try {
            if (!valid_name(line.split(" ")[0]))
                throw new Exception("Flyable type invalid");
            if (Integer.parseInt(line.split(" ")[2]) < 0)
                throw new Exception("Longitude value should be positive");
            if (Integer.parseInt(line.split(" ")[3]) < 0)
                throw new Exception("Latitude value should be positive");
            if (Integer.parseInt(line.split(" ")[4]) < 0)
                throw new Exception("Height value should be positive");
            if (Integer.parseInt(line.split(" ")[4]) > 100)
                throw new Exception("Height should be less than or equal to 100");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new Exception("Information format should be provided in the following format: TYPE NAME LONGITUDE LATITUDE HEIGHT");
        }
        return true;
    }

    private static boolean valid_name(String name) {

        switch (name) {
            case "Helicopter":
                return true;
            case "JetPlane":
                return true;
            case "Baloon":
                return true;
            default:
                return false;
        }
    }
}

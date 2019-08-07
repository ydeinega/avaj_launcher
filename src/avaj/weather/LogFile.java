package avaj.weather;

import java.io.FileWriter;
import java.io.IOException;

public class LogFile {
    private FileWriter fileWriter;

    public LogFile() {

        try {
            this.fileWriter = new FileWriter("simulation.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLog(String log) {

        try {
            fileWriter.write(log + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeLogFile() {

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

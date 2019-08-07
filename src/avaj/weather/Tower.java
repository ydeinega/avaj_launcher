package avaj.weather;

import avaj.aircraft.Flyable;

import java.util.ArrayList;

public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
        Simulator.logFile.addLog("Tower says: " + flyable.getInfo() + "registered to weather tower.");
    }
    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        Simulator.logFile.addLog("Tower says: " + flyable.getInfo() + "unregistered from weather tower.");
    }

    protected void conditionsChanged() {
        for(int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }
    }

}

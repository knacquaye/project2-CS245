import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final int floorNumber;
    private final List<Passenger> waitingPassengers; // List to store passengers currently waiting on this floor

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.waitingPassengers = new ArrayList<>();
    }

    //Adds a passenger to the waiting list on this floor.
    public void addPassenger(Passenger passenger) {
        waitingPassengers.add(passenger);
    }

    public List<Passenger> getWaitingPassengers() {
        return new ArrayList<>(waitingPassengers);
    }


    public void clearWaitingPassengers() {
        waitingPassengers.clear();
    }

    // Getters
    public int getFloorNumber() {
        return floorNumber;
    }
}


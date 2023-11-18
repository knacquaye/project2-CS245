public class Passenger {
    // Attributes to hold the passenger's current and destination floors
    private int currentFloor;
    private int destinationFloor;
    private int arrivalTime;  // Time when the passenger arrives and starts waiting
    private int deliveryTime; // Time when the passenger is delivered to the destination

    // Constructor to initialize a passenger with their current and destination floors
    public Passenger(int currentFloor, int destinationFloor,int arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        if (currentFloor == destinationFloor) {
            throw new IllegalArgumentException("Current and destination floors cannot be the same.");
        }
    }

    // Getter for arrivalTime
    public int getArrivalTime() {
        return arrivalTime;
    }
    // Setter for arrivalTime (if needed)
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }


    // Getter for the current floor
    public int getCurrentFloor() {
        return currentFloor;
    }

    // Getter for the destination floor
    public int getDestinationFloor() {
        return destinationFloor;
    }
    // Calculate the total time taken for this passenger's journey
    public int getTotalTime() {
        return deliveryTime - arrivalTime;
    }

    // Method to determine the direction the passenger wants to go
    public String getDirection() {
        // If the destination floor is greater than the current floor, the direction is "up"
        if (destinationFloor > currentFloor) {
            return "up";
        } else if (destinationFloor < currentFloor) {
            // If the destination floor is less than the current floor, the direction is "down"
            return "down";
        } else {
            // This case should not happen as the destination and current floors should not be the same
            // We can handle this with an exception or default to a no-op ("none")
            return "none";
        }
    }

    // toString method to output the passenger's details as a string
    @Override
    public String toString() {
        return "Passenger{" +
                "currentFloor=" + currentFloor +
                ", destinationFloor=" + destinationFloor +
                ", direction='" + getDirection() + '\'' +
                '}';
    }


}



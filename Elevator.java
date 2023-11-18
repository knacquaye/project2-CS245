import java.util.*;

public class Elevator {
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String IDLE = "idle";


    private int currentFloor;
    private final int capacity; // Maximum capacity of the elevator
    private String direction; // Current direction of the elevator
    private final PriorityQueue<Passenger> upHeap; // Min-heap for passengers going up
    private final PriorityQueue<Passenger> downHeap; // Max-heap for passengers going down

    // Statistics for the elevator operation
    private int totalTime = 0;
    private int currentTime = 0;
    private int longestTime = 0; // Longest travel time by any passenger
    private int shortestTime = Integer.MAX_VALUE; // Shortest travel time by any passenger
    private int passengersDelivered = 0; // Total number of passengers delivered


    /**
     * Constructor for the Elevator class.
     *
     * @param capacity The capacity of the elevator.
     */
    public Elevator(int capacity) {
        this.capacity = capacity;
        this.currentFloor = 1; // Assuming the elevator starts at the first floor
        this.direction = IDLE;

        // Initialize the min-heap for passengers going up
        this.upHeap = new PriorityQueue<>(new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                return Integer.compare(p1.getDestinationFloor(), p2.getDestinationFloor());
            }
        });

        // Initialize the max-heap for passengers going down
        this.downHeap = new PriorityQueue<>(new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                return Integer.compare(p2.getDestinationFloor(), p1.getDestinationFloor());
            }
        });
    }

    /**
     * Loads passengers onto the elevator.
     *
     * @param newPassengers List of new passengers to load.
     * @param currentTime   The current time in the simulation.
     */
    public void loadPassengers(List<Passenger> newPassengers, int currentTime) {
        for (Passenger passenger : newPassengers) {
            if (getTotalPassengerCount() < capacity) {
                // Set the arrival time for each passenger
                passenger.setArrivalTime(currentTime);

                if (passenger.getDestinationFloor() > currentFloor) {
                    upHeap.add(passenger);
                } else {
                    downHeap.add(passenger);
                }
            } else {
                break; // Elevator is full
            }
        }
        updateDirection();
    }

    /**
     * Unloads passengers at the current floor.
     *
     * @param currentTime The current time in the simulation.
     */

    public void unloadPassengers(int currentTime) {
        PriorityQueue<Passenger> currentHeap;
        if (direction.equals(UP)) {
            currentHeap = upHeap;
        } else {
            currentHeap = downHeap;
        }
        while (!currentHeap.isEmpty() && currentHeap.peek().getDestinationFloor() == currentFloor) {
            Passenger passenger = currentHeap.poll();
            int travelTime = currentTime - passenger.getArrivalTime(); // Calculate travel time
            totalTime += travelTime;
            longestTime = Math.max(longestTime, travelTime);
            shortestTime = Math.min(shortestTime, travelTime);
            passengersDelivered++;
        }
    }

    /**
     * Updates the direction of the elevator based on passenger destinations.
     */
    private void updateDirection() {
        if (upHeap.isEmpty() && downHeap.isEmpty()) {
            direction = IDLE;
        } else if (!upHeap.isEmpty() && (direction.equals(UP) || direction.equals(IDLE))) {
            direction = UP;
        } else if (!downHeap.isEmpty()) {
            direction = DOWN;
        }
    }

    /**
     * Moves the elevator to the specified floor and updates the current time.
     *
     * @param floorNumber The floor number to move to.
     * @param currentTime The current time in the simulation.
     */

    public void moveToFloor(int floorNumber, int currentTime) {
        // Move to the specified floor
        this.currentFloor = floorNumber;
        this.currentTime = currentTime;

        // Unload passengers whose destination is this floor
        unloadPassengers(currentTime);

        // Update direction or set to idle if no passengers
        updateDirection();
    }


    public int getTotalPassengerCount() {
        return upHeap.size() + downHeap.size();
    }

    // Getters
    public int getCurrentFloor() {
        return currentFloor;
    }

    public String getDirection() {
        return direction;
    }

    public boolean isFull() {
        return getTotalPassengerCount() >= capacity;
    }

    // New methods to get average, longest, and shortest times
    public double getAverageTime() {
        double averageTime;
        if (passengersDelivered == 0) {
            averageTime = 0;
        } else {
            averageTime = (double) totalTime / passengersDelivered;
        }
        return averageTime;
    }

    public int getLongestTime() {
        return longestTime;
    }

    public int getShortestTime() {
        return (shortestTime == Integer.MAX_VALUE) ? 0 : shortestTime;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Elevator{" +
                "currentFloor=" + currentFloor +
                ", capacity=" + capacity +
                ", direction='" + direction + '\'' +
                ", passengersGoingUp=" + upHeap.size() +
                ", passengersGoingDown=" + downHeap.size() +
                '}';
    }
//test case


//        public static void main(String[] args) {
//            // Step 1: Initialize Elevator
//            Elevator elevator = new Elevator(2);
//
//            // Step 2: Create Passengers
//            Passenger p1 = new Passenger(1, 5, 0); // floor 1 to 5, arrival time 0
//            Passenger p2 = new Passenger(1, 6, 3); // floor 1 to 6, arrival time 3
//            Passenger p3 = new Passenger(3, 2, 7); // floor 3 to 2, arrival time 7
//            Passenger p4 = new Passenger(3, 1, 9); // floor 3 to 1, arrival time 9
//
//            // Step 3: Simulate Elevator Movements
//            List<Passenger> firstBatch = new ArrayList<>(Arrays.asList(p1, p2));
//            List<Passenger> secondBatch = new ArrayList<>(Arrays.asList(p3, p4));
//
//            elevator.loadPassengers(firstBatch, 5); // Load at time 5
//            elevator.moveToFloor(5, 10); // Move to floor 5 at time 10
//            elevator.moveToFloor(6, 12); // Move to floor 6 at time 12
//            elevator.loadPassengers(secondBatch, 12); // Load at time 12
//            elevator.moveToFloor(2, 15); // Move to floor 2 at time 15
//            elevator.moveToFloor(1, 18); // Move to floor 1 at time 18
//
//            // Step 4: Check Results
//            System.out.println("Average Time: " + elevator.getAverageTime());
//            System.out.println("Longest Time: " + elevator.getLongestTime());
//            System.out.println("Shortest Time: " + elevator.getShortestTime());
//        }
//    }


}





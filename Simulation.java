import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulation {
    private final int numberOfFloors;
    private final double passengerProbability;
    private final int numberOfElevators;
    private final int elevatorCapacity;
    private final int duration;
    private final List<Floor> floors;
    private final List<Elevator> elevators;
    private final Random random;

    public Simulation(int numberOfFloors, double passengerProbability, int numberOfElevators, int elevatorCapacity, int duration) {
        this.numberOfFloors = numberOfFloors;
        this.passengerProbability = passengerProbability;
        this.numberOfElevators = numberOfElevators;
        this.elevatorCapacity = elevatorCapacity;
        this.duration = duration;
        this.floors = new LinkedList<>();
        this.elevators = new LinkedList<>();
        this.random = new Random();


        initializeFloors();
        initializeElevators();

    }

    private void initializeFloors() {
        for (int i = 1; i <= numberOfFloors; i++) {
            floors.add(new Floor(i));
        }
    }

    private void initializeElevators() {
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new Elevator(elevatorCapacity));
        }
    }

    public void runSimulation() {
        for (int currentTime = 0; currentTime < duration; currentTime++) {
            simulateTick(currentTime);
        }
        reportSimulationResults(elevators);
    }

    private void simulateTick(int currentTime) {
        generateNewPassengers(currentTime);
        for (Elevator elevator : elevators) {
            // Determine the next floor to move
            int nextFloor = determineNextFloor(elevator);
            if (nextFloor != -1) {
                elevator.moveToFloor(nextFloor, currentTime);
            }

            // Load and unload passengers
            Floor currentFloor = floors.get(elevator.getCurrentFloor() - 1);
            elevator.unloadPassengers(currentTime);
            if (!elevator.isFull()) {
                List<Passenger> waitingPassengers = currentFloor.getWaitingPassengers();
                elevator.loadPassengers(waitingPassengers, currentTime);
                currentFloor.clearWaitingPassengers();
            }
        }

    }

    private void generateNewPassengers(int currentTime) {
        for (Floor floor : floors) {
            if (random.nextDouble() < passengerProbability) {
                int destinationFloor;
                do {
                    destinationFloor = random.nextInt(numberOfFloors) + 1;
                } while (destinationFloor == floor.getFloorNumber());

                Passenger newPassenger = new Passenger(floor.getFloorNumber(), destinationFloor, currentTime);
                floor.addPassenger(newPassenger);
            }
        }
    }

    private int determineNextFloor(Elevator elevator) {
        String direction = elevator.getDirection();
        int currentFloor = elevator.getCurrentFloor();
        PriorityQueue<Passenger> passengers;

        if (direction.equals("up")) {
            passengers = elevator.getUpHeap();
            if (!passengers.isEmpty()) {
                return passengers.peek().getDestinationFloor(); // Next floor for the closest passenger going up
            }
        } else if (direction.equals("down")) {
            passengers = elevator.getDownHeap();
            if (!passengers.isEmpty()) {
                return passengers.peek().getDestinationFloor(); // Next floor for the closest passenger going down
            }
        }

        // If the elevator is idle or there are no more passengers in the current direction
        // Check if there are passengers in the opposite direction
        PriorityQueue<Passenger> oppositePassengers;
        if (direction.equals("up")) {
            oppositePassengers = elevator.getDownHeap();
        } else {
            oppositePassengers = elevator.getUpHeap();
        }
        if (!oppositePassengers.isEmpty()) {
            return oppositePassengers.peek().getDestinationFloor();
        }

        // If there are no passengers in either direction, remain on the current floor
        return currentFloor;
    }
    public void reportSimulationResults(List<Elevator> elevators) {
        double totalAverageTime = 0;
        int totalLongestTime = 0;
        int totalShortestTime = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            totalAverageTime += elevator.getAverageTime();
            totalLongestTime = Math.max(totalLongestTime, elevator.getLongestTime());
            totalShortestTime = Math.min(totalShortestTime, elevator.getShortestTime());
        }

        double averageOfAverages = totalAverageTime / elevators.size();

        System.out.println("Average of Average Times: " + averageOfAverages);
        System.out.println("Longest Time: " + totalLongestTime);
        System.out.println("Shortest Time: " + totalShortestTime);
    }





    public static void main(String[] args) {
        // Create a Simulation instance with the specified parameters
        Simulation simulation = new Simulation(32, 0.03, 2, 10, 500);
        simulation.runSimulation();

    }
}


//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class Building {
//    private int numberOfFloors;
//    private List<Elevator> elevators;
//    private List<Floor> floors;
//    private double passengerProbability;
//
//    public Building(int numberOfFloors, int numberOfElevators, double passengerProbability) {
//        this.numberOfFloors = numberOfFloors;
//        this.passengerProbability = passengerProbability;
//
//        // Initialize floors
//        floors = new ArrayList<>();
//        for (int i = 0; i < numberOfFloors; i++) {
//            floors.add(new Floor(i + 1)); // Floors are usually 1-indexed
//        }
//
//        // Initialize elevators
//        elevators = new ArrayList<>();
//        for (int i = 0; i < numberOfElevators; i++) {
//            elevators.add(new Elevator());
//        }
//    }
//
//    // Method to simulate one tick of time in the building
//    public void simulateTick() {
//        // Generate new passengers with given probability on each floor
//        generatePassengers();
//
//        // Simulate elevators for one tick: loading, unloading, and moving
//        for (Elevator elevator : elevators) {
//            elevator.simulateTick(floors);
//        }
//    }
//
//    // Method to generate passengers on floors based on the passenger probability
//    private void generatePassengers() {
//        Random rand = new Random();
//        for (Floor floor : floors) {
//            if (rand.nextDouble() < passengerProbability) {
//                // Passenger appears and wants to go to a random floor that is not the current floor
//                int destinationFloor;
//                do {
//                    destinationFloor = rand.nextInt(numberOfFloors) + 1;
//                } while (destinationFloor == floor.getFloorNumber());
//
//                // Create a new passenger and add to the floor
//                Passenger passenger = new Passenger(floor.getFloorNumber(), destinationFloor);
//                floor.addPassenger(passenger);
//            }
//        }
//    }
//}
//
//
//

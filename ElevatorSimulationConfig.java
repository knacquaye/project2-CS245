import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ElevatorSimulationConfig {

    // Default values as constants
    private static final String DEFAULT_STRUCTURE = "linked";
    private static final int DEFAULT_FLOORS = 32;
    private static final double DEFAULT_PASSENGERS = 0.03;
    private static final int DEFAULT_ELEVATORS = 1;
    private static final int DEFAULT_ELEVATOR_CAPACITY = 10;
    private static final int DEFAULT_DURATION = 500;

    // Variables to hold the actual values used by the simulation
    private String structures;
    private int floors;
    private double passengers;
    private int elevators;
    private int elevatorCapacity;
    private int duration;

    public ElevatorSimulationConfig(String propertiesFilePath) {
        Properties prop = new Properties();

        try {
            // Try to load the properties file if it exists
            prop.load(new FileInputStream(propertiesFilePath));
        } catch (IOException e) {
            System.out.println("Properties file not found. Using default values.");
        }

        // Parse properties using defaults
        structures = prop.getProperty("structures", DEFAULT_STRUCTURE);
        floors = Integer.parseInt(prop.getProperty("floors", String.valueOf(DEFAULT_FLOORS)));
        passengers = Double.parseDouble(prop.getProperty("passengers", String.valueOf(DEFAULT_PASSENGERS)));
        elevators = Integer.parseInt(prop.getProperty("elevators", String.valueOf(DEFAULT_ELEVATORS)));
        elevatorCapacity = Integer.parseInt(prop.getProperty("elevatorCapacity", String.valueOf(DEFAULT_ELEVATOR_CAPACITY)));
        duration = Integer.parseInt(prop.getProperty("duration", String.valueOf(DEFAULT_DURATION)));
    }

    // Getters for each configuration property
    public String getStructures() {
        return structures;
    }

    public int getFloors() {
        return floors;
    }

    public double getPassengers() {
        return passengers;
    }

    public int getElevators() {
        return elevators;
    }

    public int getElevatorCapacity() {
        return elevatorCapacity;
    }

    public int getDuration() {
        return duration;
    }


    public static void main(String[] args){
        ElevatorSimulationConfig config;

        // Use the first argument as the properties file path
        if (args.length > 0) {
            config = new ElevatorSimulationConfig(args[0]);
        } else {
            // No arguments, use default values
            config = new ElevatorSimulationConfig("");
        }

        // Print out the configuration to verify
        System.out.println("Structures: " + config.getStructures());
        System.out.println("Floors: " + config.getFloors());
        System.out.println("Passenger Probability: " + config.getPassengers());
        System.out.println("Number of Elevators: " + config.getElevators());
        System.out.println("Elevator Capacity: " + config.getElevatorCapacity());
        System.out.println("Duration in Ticks: " + config.getDuration());
    }
}


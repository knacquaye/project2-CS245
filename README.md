
Classes
Passenger Class
Represents individuals using the elevator.
Attributes include current floor, destination floor, arrival time, and delivery time.
Methods for calculating total travel time.
Floor Class
Represents each floor in the building.
Manages a list of waiting passengers.
Methods to add and remove passengers from the waiting list.

Elevator Class
Simulates the elevator's operation.
Manages passengers inside the elevator using priority queues for efficient service.
Methods for loading and unloading passengers, moving between floors, and calculating travel times.
PriorityQueue for Upward and Downward Travel:
Min-Heap for Upward Travel: Passengers going to higher floors are managed in a min-heap, which makes sure the passenger with the lowest destination floor is unloaded first. This aligns with the natural operation of an elevator traveling upwards.
Max-Heap for Downward Travel: Conversely, for passengers traveling to lower floors, a max-heap is used to ensure the passenger with the highest destination floor is unloaded first.
The heaps automatically reorder themselves when new passengers are added, ensuring the next passenger to be unloaded is always at the head of the queue.

# project2-CS245

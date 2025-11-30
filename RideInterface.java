/**
 * Interface RideInterface: Defines the core functionalities that a theme park ride (Ride class) must implement.
 * Includes methods for visitor queue management, ride history tracking, and ride cycle operation (as required by Part 2).
 */
public interface RideInterface {
    /**
     * Adds a visitor to the ride's waiting queue (FIFO order).
     * @param visitor The Visitor to be added to the queue
     */
    void addVisitorToQueue(Visitor visitor);

    /**
     * Removes and returns the first visitor from the waiting queue (FIFO).
     * @return The removed Visitor; null if the queue is empty
     */
    Visitor removeVisitorFromQueue();

    /**
     * Prints details of all visitors currently in the waiting queue.
     */
    void printQueue();

    /**
     * Adds a visitor to the ride's historical record (for visitors who have completed the ride).
     * @param visitor The Visitor to be added to the history
     */
    void addVisitorToHistory(Visitor visitor);

    /**
     * Checks if a specific visitor exists in the ride's historical record.
     * @param visitor The Visitor to check
     * @return true if the visitor is in the history; false otherwise
     */
    boolean checkVisitorFromHistory(Visitor visitor);

    /**
     * Returns the total number of visitors in the ride's historical record.
     * @return The count of historical visitors
     */
    int numberOfVisitors();

    /**
     * Prints details of all visitors in the ride's historical record (must use Iterator in implementation, per Part 4A).
     */
    void printRideHistory();

    /**
     * Runs one cycle of the ride: Transfers up to maxRider visitors from the queue to the history (per Part 5).
     */
    void runOneCycle();
    void exportRideHistory(String exportPath);
    void importRideHistory(String importPath);
}
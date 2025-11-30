import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Class Ride: Implements RideInterface, representing a theme park ride (e.g., roller coaster, carousel).
 * Manages waiting visitors (Queue), ride history (LinkedList), ride operator (Employee), and ride cycles.
 * Meets requirements from Part 1 (attributes), Part 3 (Queue), Part 4A (LinkedList), and Part 5 (ride cycles).
 */
public class Ride implements RideInterface {
    // Core attributes for the ride
    private String rideName;          // Name of the ride (e.g., "Roller Coaster")
    private int maxRider;             // Max visitors per ride cycle (Part 5 requirement)
    private int numOfCycles = 0;      // Total number of completed cycles (default: 0, Part 5)
    private Employee operator;        // Employee in charge of the ride (Part 1 requirement)

    // Collections for queue and history
    private Queue<Visitor> waitingLine = new LinkedList<>();    // FIFO queue for waiting visitors (Part 3)
    private LinkedList<Visitor> rideHistory = new LinkedList<>();// Records of visitors who rode (Part 4A)

    /**
     * Default no-argument constructor: Initializes ride with default values.
     */
    public Ride() {}

    /**
     * Parameterized constructor: Initializes ride name, max riders per cycle, and operator.
     * Collections (waitingLine, rideHistory) are initialized automatically.
     *
     * @param rideName Name of the ride
     * @param maxRider Max visitors allowed per cycle (≥1)
     * @param operator Employee responsible for operating the ride
     */
    public Ride(String rideName, int maxRider, Employee operator) {
        this.rideName = rideName;
        this.maxRider = (maxRider >= 1) ? maxRider : 2; // Ensure maxRider is at least 1 (default to 2 if invalid)
        this.operator = operator;
    }

    // ------------------------------
    // Implementation of RideInterface: Queue Management (Part 3)
    // ------------------------------
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[ERROR] Failed to add visitor: Visitor object cannot be null.");
            return;
        }
        waitingLine.offer(visitor); // Use offer() to avoid exceptions if queue is full (no max here)
        System.out.printf("[SUCCESS] Visitor '%s' (ID: %s) added to %s's waiting queue.%n",
                visitor.getName(), visitor.getId(), rideName);
    }

    @Override
    public Visitor removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.printf("[ERROR] Failed to remove visitor: %s's waiting queue is empty.%n", rideName);
            return null;
        }
        Visitor removedVisitor = waitingLine.poll(); // Poll() removes and returns front of queue (FIFO)
        System.out.printf("[SUCCESS] Visitor '%s' (ID: %s) removed from %s's waiting queue.%n",
                removedVisitor.getName(), removedVisitor.getId(), rideName);
        return removedVisitor;
    }

    @Override
    public void printQueue() {
        System.out.printf("%n--- %s's Waiting Queue ---%n", rideName);
        if (waitingLine.isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        int index = 1;
        for (Visitor visitor : waitingLine) { // Iterate through queue (maintains FIFO order)
            System.out.printf("%d. Name: %s | ID: %s | Type: %s | Visit Date: %s%n",
                    index++, visitor.getName(), visitor.getId(),
                    visitor.getVisitorType(), visitor.getVisitDate());
        }
    }

    // ------------------------------
    // Implementation of RideInterface: Ride History (Part 4A)
    // ------------------------------
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[ERROR] Failed to add to history: Visitor object cannot be null.");
            return;
        }
        rideHistory.add(visitor);
        System.out.printf("[SUCCESS] Visitor '%s' (ID: %s) added to %s's ride history.%n",
                visitor.getName(), visitor.getId(), rideName);
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("[ERROR] Failed to check history: Visitor object cannot be null.");
            return false;
        }
        boolean exists = rideHistory.contains(visitor); // Relies on Visitor's overridden equals()
        System.out.printf("[RESULT] Visitor '%s' (ID: %s) exists in %s's history: %b%n",
                visitor.getName(), visitor.getId(), rideName, exists);
        return exists;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.printf("[INFO] Total visitors in %s's history: %d%n", rideName, count);
        return count;
    }

    @Override
    public void printRideHistory() {
        System.out.printf("%n--- %s's Ride History ---%n", rideName);
        if (rideHistory.isEmpty()) {
            System.out.println("No ride history available.");
            return;
        }
        // Must use Iterator (Part 4A requirement)
        Iterator<Visitor> historyIterator = rideHistory.iterator();
        int index = 1;
        while (historyIterator.hasNext()) {
            Visitor visitor = historyIterator.next();
            System.out.printf("%d. Name: %s | ID: %s | Age: %d | Type: %s%n",
                    index++, visitor.getName(), visitor.getId(),
                    visitor.getAge(), visitor.getVisitorType());
        }
    }

    /**
     * Sorts the ride history using the custom VisitorComparator (Part 4B requirement).
     * Uses Collections.sort() with Comparator interface (Cannot use Comparable).
     */
    public void sortRideHistory() {
        // Check if ride history is empty to avoid unnecessary operation
        if (rideHistory.isEmpty()) {
            System.out.printf("[INFO] %s's ride history is empty — no sorting performed.%n", rideName);
            return;
        }

        // Sort the LinkedList using VisitorComparator (core logic for Part 4B)
        Collections.sort(rideHistory, new VisitorComparator());

        // Print success message with clear feedback
        System.out.printf("[SUCCESS] %s's ride history has been sorted by: Age (ascending) → Visit Date (ascending).%n", rideName);
    }
    // ------------------------------
    // Implementation of RideInterface: Ride Cycle (Part 5)
    // ------------------------------
    @Override
    public void runOneCycle() {
        System.out.printf("%n--- Starting %s's Ride Cycle ---%n", rideName);

        // Check 1: Ride has an operator
        if (operator == null) {
            System.out.println("[ERROR] Ride cycle failed: No operator assigned to the ride.");
            return;
        }
        // Check 2: Waiting queue is not empty
        if (waitingLine.isEmpty()) {
            System.out.println("[ERROR] Ride cycle failed: No visitors in the waiting queue.");
            return;
        }

        // Transfer up to maxRider visitors from queue to history
        int transferredCount = 0;
        while (transferredCount < maxRider && !waitingLine.isEmpty()) {
            Visitor visitor = waitingLine.poll();
            rideHistory.add(visitor);
            transferredCount++;
        }

        // Update cycle count and print result
        numOfCycles++;
        System.out.printf("[SUCCESS] Ride cycle completed! Transferred %d visitors to history. Total cycles: %d%n",
                transferredCount, numOfCycles);
    }

    // ------------------------------
    // Getters and Setters (for encapsulated attributes)
    // ------------------------------
    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = (maxRider >= 1) ? maxRider : this.maxRider; // Reject invalid maxRider
    }
    @Override
    public void exportRideHistory(String exportPath) {
        // Check if rideHistory is empty (to prevent the generation of an empty file during export)
        if (this.rideHistory.isEmpty()) {
            System.out.println("[ERROR] Failed to export: ride history is empty");
            return;
        }

        // Write to CSV file (try-with-resources automatically closes the stream, avoiding resource leakage)
        try (PrintWriter writer = new PrintWriter(new FileWriter(exportPath))) {
            // Write the CSV header (corresponding to the attributes of the Visitor: ID, Name, Age, Type, Visit Date)
            writer.println("VisitorID,Name,Age,VisitorType,VisitDate");

            // 2. Traverse the "rideHistory" and write down the data of each "Visitor" (separated by commas)
            for (Visitor visitor : this.rideHistory) {
                writer.printf("%s,%s,%d,%s,%s%n",
                        visitor.getId(),       // Obtain the ID from the Visitor class
                        visitor.getName(),     // Obtain the name
                        visitor.getAge(),      // Obtain age
                        visitor.getVisitorType(), // Obtain the type of visitor (such as Adult)
                        visitor.getVisitDate() // Obtain the access date
                );
            }

            System.out.println("[SUCCESS] The Ride history has been exported to: " + exportPath);
        } catch (IOException e) {
            // Capture file operation exception (such as the path not existing or insufficient permissions)
            System.err.println("[ERROR] Export failed: " + e.getMessage());
        }
    }
    @Override
    public void importRideHistory(String importPath) {
        // Check if the file path is empty
        if (importPath == null || importPath.trim().isEmpty()) {
            System.out.println("[ERROR] Import failed: File path cannot be empty!");
            return;
        }

        // Read the CSV file (using try-with-resources to automatically close the stream)
        try (BufferedReader reader = new BufferedReader(new FileReader(importPath))) {
            String line;
            boolean isFirstLine = true; // Skip the CSV header (the first row)
            int importedCount = 0;

            while ((line = reader.readLine()) != null) {
                // Skip the header row (VisitorID, Name, Age, VisitorType, VisitDate)
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Split CSV rows (separated by commas)
                String[] fields = line.split(",");

                // Verify whether the number of fields is correct (each row should contain 5 fields)
                if (fields.length != 5) {
                    System.out.printf("[WARNING] Skipping invalid line: Incorrect number of fields (expected 5, got %d) → Line: %s%n", fields.length, line);
                    continue;
                }

                try {
                    // Analyze each field (in accordance with the order of CSV table headers)
                    String visitorId = fields[0].trim();
                    String name = fields[1].trim();
                    int age = Integer.parseInt(fields[2].trim()); // 转换年龄为int
                    String visitorType = fields[3].trim();
                    String visitDate = fields[4].trim();

                    // Create a Visitor object (matching the parameterized constructor of the Visitor class)
                    Visitor importedVisitor = new Visitor(visitorId, name, age, visitorType, visitDate);

                    // Avoid duplicate addition (rely on the equals method rewritten by Visitor to determine by ID)
                    if (!rideHistory.contains(importedVisitor)) {
                        rideHistory.add(importedVisitor);
                        importedCount++;
                    } else {
                        System.out.printf("[WARNING] Skipping duplicate visitor: ID=%s (already exists in history)%n", visitorId);
                    }

                } catch (NumberFormatException e) {
                    System.out.printf("[WARNING] Skipping invalid line: Age format error → Line: %s, Error: %s%n", line, e.getMessage());
                }
            }

            // Print the import results
            System.out.printf("[SUCCESS] Import completed! Successfully imported %d valid visitors (duplicates/invalid lines skipped)%n", importedCount);

        } catch (IOException e) {
            // Handle file reading exceptions (such as when the file does not exist or there are insufficient permissions)
            System.err.printf("[ERROR] Import failed: File operation exception → Path: %s, Error: %s%n", importPath, e.getMessage());
        }
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
        System.out.printf("[INFO] %s's operator updated to '%s' (ID: %s).%n",
                rideName, operator.getName(), operator.getEmployeeId());
    }

    public Queue<Visitor> getWaitingLine() {
        return waitingLine;
    }

    public LinkedList<Visitor> getRideHistory() {
        return rideHistory;
    }
}
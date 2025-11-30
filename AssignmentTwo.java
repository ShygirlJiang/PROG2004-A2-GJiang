/**
 * Class AssignmentTwo: Entry point of the Park Rides Visitor Management System (PRVMS).
 * Contains demonstration methods for Parts 3-7 of the assignment, showing the functionality of
 * queue management, ride history, sorting, ride cycles, and file I/O.
 */
public class AssignmentTwo {

    /**
     * Main method: Executes all demonstration methods (Part3 to Part7) sequentially.
     * @param args Command-line arguments (not used in this project)
     */
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();
        System.out.println("==================================== PROG2004 A2 Demonstration ====================================");

        demo.partThree();    // Demonstrate Part3: Waiting Queue (Queue)
        demo.partFourA();    // Demonstrate Part4A: Ride History (LinkedList)
        demo.partFourB();    // Demonstrate Part4B: Sort Ride History (Comparator)
        demo.partFive();     // Demonstrate Part5: Run Ride Cycle
        demo.partSix();      // Demonstrate Part6: Export Ride History to CSV
        demo.partSeven();    // Demonstrate Part7: Import Ride History from CSV

        System.out.println("=====================================================================================================");
    }

    // ------------------------------
    // Part3: Demonstrate Waiting Queue (Queue<Visitor>)
    // Requirements: Add 5+ visitors → Remove 1 → Print queue
    // ------------------------------
    public void partThree() {
        System.out.println("\n==================================== Part3: Waiting Queue Demonstration =====================================");

        // 1. Create a ride operator (required for Ride object)
        Employee rollerCoasterOp = new Employee(
                "P001",    // Person ID (inherited)
                "John Doe",// Person name (inherited)
                35,        // Person age (inherited)
                "E001",    // Employee ID (unique)
                "Roller Coaster Section" // Department (unique)
        );

        // 2. Create Ride object (Roller Coaster, max 2 riders per cycle)
        Ride rollerCoaster = new Ride("Roller Coaster", 2, rollerCoasterOp);

        // 3. Create 5 Visitor objects
        Visitor v1 = new Visitor("V001", "Alice Smith", 28, "Adult", "2025-12-01");
        Visitor v2 = new Visitor("V002", "Bob Johnson", 22, "Student", "2025-12-01");
        Visitor v3 = new Visitor("V003", "Charlie Brown", 32, "Adult", "2025-12-01");
        Visitor v4 = new Visitor("V004", "Diana Prince", 19, "Student", "2025-12-01");
        Visitor v5 = new Visitor("V005", "Ethan Hunt", 40, "Adult", "2025-12-01");

        // 4. Add 5 visitors to the waiting queue
        System.out.println("\n[Step 1: Add 5 visitors to the queue]");
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);

        // 5. Print queue (should show 5 visitors)
        System.out.println("\n[Step 2: Print queue (before removal)]");
        rollerCoaster.printQueue();

        // 6. Remove 1 visitor from the queue (FIFO: removes v1)
        System.out.println("\n[Step 3: Remove 1 visitor from the queue]");
        rollerCoaster.removeVisitorFromQueue();

        // 7. Print queue again (should show 4 visitors)
        System.out.println("\n[Step 4: Print queue (after removal)]");
        rollerCoaster.printQueue();
    }

    // ------------------------------
    // Part4A: Demonstrate Ride History (LinkedList<Visitor>)
    // Requirements: Add 5+ visitors → Check 1 visitor → Print count → Print history
    // ------------------------------
    public void partFourA() {
        System.out.println("\n==================================== Part4A: Ride History Demonstration =====================================");

        // 1. Create Ride object (Carousel, no operator needed for history demo)
        Ride carousel = new Ride("Carousel", 4, null);

        // 2. Create 5 Visitor objects
        Visitor v1 = new Visitor("V006", "Fiona Gallagher", 10, "Child", "2025-12-01");
        Visitor v2 = new Visitor("V007", "George Costanza", 38, "Adult", "2025-12-01");
        Visitor v3 = new Visitor("V008", "Helen Keller", 29, "Adult", "2025-12-01");
        Visitor v4 = new Visitor("V009", "Ivan Drago", 15, "Student", "2025-12-01");
        Visitor v5 = new Visitor("V010", "Julia Roberts", 45, "Adult", "2025-12-01");

        // 3. Add 5 visitors to ride history
        System.out.println("\n[Step 1: Add 5 visitors to ride history]");
        carousel.addVisitorToHistory(v1);
        carousel.addVisitorToHistory(v2);
        carousel.addVisitorToHistory(v3);
        carousel.addVisitorToHistory(v4);
        carousel.addVisitorToHistory(v5);

        // 4. Check if a visitor (v2) exists in history
        System.out.println("\n[Step 2: Check if Visitor 'George Costanza' (V007) is in history]");
        carousel.checkVisitorFromHistory(v2);

        // 5. Check if a non-existent visitor is in history
        Visitor vNonExistent = new Visitor("V999", "Zoe Miller", 30, "Adult", "2025-12-01");
        System.out.println("\n[Step 3: Check if non-existent Visitor 'Zoe Miller' (V999) is in history]");
        carousel.checkVisitorFromHistory(vNonExistent);

        // 6. Print total number of visitors in history
        System.out.println("\n[Step 4: Print total visitors in history]");
        carousel.numberOfVisitors();

        // 7. Print full ride history (uses Iterator, per Part4A requirement)
        System.out.println("\n[Step 5: Print full ride history]");
        carousel.printRideHistory();
    }

    // ------------------------------
    // Part4B: Demonstrate Sorted Ride History (Comparator)
    // Requirements: Add 5+ visitors → Print before sort → Sort → Print after sort
    // ------------------------------
    public void partFourB() {
        System.out.println("\n==================================== Part4B: Sorted Ride History Demonstration =====================================");

        // 1. Create Ride object (Bumper Cars)
        Ride bumperCars = new Ride("Bumper Cars", 2, null);

        // 2. Create 5 Visitor objects (with mixed ages and dates for sorting)
        Visitor v1 = new Visitor("V011", "Kevin Hart", 25, "Adult", "2025-12-03"); // Age 25, Date 2025-12-03
        Visitor v2 = new Visitor("V012", "Lady Gaga", 37, "Adult", "2025-12-01"); // Age 37, Date 2025-12-01
        Visitor v3 = new Visitor("V013", "Mike Tyson", 25, "Adult", "2025-12-02"); // Age 25, Date 2025-12-02
        Visitor v4 = new Visitor("V014", "Natalie Portman", 18, "Student", "2025-12-02"); // Age 18, Date 2025-12-02
        Visitor v5 = new Visitor("V015", "Oscar Isaac", 18, "Student", "2025-12-01"); // Age 18, Date 2025-12-01

        // 3. Add visitors to ride history
        System.out.println("\n[Step 1: Add 5 visitors to ride history]");
        bumperCars.addVisitorToHistory(v1);
        bumperCars.addVisitorToHistory(v2);
        bumperCars.addVisitorToHistory(v3);
        bumperCars.addVisitorToHistory(v4);
        bumperCars.addVisitorToHistory(v5);

        // 4. Print history BEFORE sorting
        System.out.println("\n[Step 2: Ride history (BEFORE sorting)]");
        bumperCars.printRideHistory();

        // 5. Sort history (uses VisitorComparator: Age → Visit Date ascending)
        System.out.println("\n[Step 3: Sort ride history]");
        bumperCars.sortRideHistory();

        // 6. Print history AFTER sorting
        System.out.println("\n[Step 4: Ride history (AFTER sorting)]");
        bumperCars.printRideHistory();
    }

    // ------------------------------
    // Part5: Demonstrate Ride Cycle (runOneCycle())
    // Requirements: Add 10+ visitors → Print queue → Run cycle → Print queue + history
    // ------------------------------
    public void partFive() {
        System.out.println("\n==================================== Part5: Ride Cycle Demonstration =====================================");

        // 1. Create a ride operator (required to run the ride)
        Employee ferrisWheelOp = new Employee(
                "P002",
                "Maya Patel",
                29,
                "E002",
                "Ferris Wheel Section"
        );

        // 2. Create Ride object (Ferris Wheel, max 3 riders per cycle)
        Ride ferrisWheel = new Ride("Ferris Wheel", 3, ferrisWheelOp);

        // 3. Add 10 visitors to the waiting queue
        System.out.println("\n[Step 1: Add 10 visitors to the queue]");
        for (int i = 1; i <= 10; i++) {
            Visitor visitor = new Visitor(
                    "V" + String.format("%03d", 20 + i), // ID: V021 to V030
                    "Visitor " + (20 + i),              // Name: Visitor 21 to 30
                    15 + i,                             // Age: 16 to 25
                    (i % 2 == 0) ? "Adult" : "Student", // Type: Alternate Adult/Student
                    "2025-12-01"
            );
            ferrisWheel.addVisitorToQueue(visitor);
        }

        // 4. Print queue BEFORE running cycle (should show 10 visitors)
        System.out.println("\n[Step 2: Queue (BEFORE ride cycle)]");
        ferrisWheel.printQueue();

        // 5. Run one ride cycle (transfers up to 3 visitors to history)
        System.out.println("\n[Step 3: Run one ride cycle]");
        ferrisWheel.runOneCycle();

        // 6. Print queue AFTER running cycle (should show 7 visitors)
        System.out.println("\n[Step 4: Queue (AFTER ride cycle)]");
        ferrisWheel.printQueue();

        // 7. Print ride history AFTER running cycle (should show 3 visitors)
        System.out.println("\n[Step 5: Ride history (AFTER ride cycle)]");
        ferrisWheel.printRideHistory();
        System.out.printf("[INFO] Total ride cycles completed: %d%n", ferrisWheel.getNumOfCycles());
    }

    // ------------------------------
    // Part6: Demonstrate Export Ride History to CSV
    // Requirements: Add 5+ visitors → Export to CSV file
    // ------------------------------
    public void partSix() {
        System.out.println("\n==================================== Part6: Export History to CSV Demonstration =====================================");

        // 1. Create Ride object (Log Flume)
        Ride logFlume = new Ride("Log Flume", 4, null);

        // 2. Create 5 Visitor objects for export
        Visitor v1 = new Visitor("V031", "Quentin Tarantino", 59, "Adult", "2025-12-01");
        Visitor v2 = new Visitor("V032", "Ryan Reynolds", 46, "Adult", "2025-12-01");
        Visitor v3 = new Visitor("V033", "Sandra Bullock", 59, "Adult", "2025-12-01");
        Visitor v4 = new Visitor("V034", "Tom Hanks", 67, "Adult", "2025-12-01");
        Visitor v5 = new Visitor("V035", "Uma Thurman", 53, "Adult", "2025-12-01");

        // 3. Add visitors to ride history
        System.out.println("\n[Step 1: Add 5 visitors to ride history]");
        logFlume.addVisitorToHistory(v1);
        logFlume.addVisitorToHistory(v2);
        logFlume.addVisitorToHistory(v3);
        logFlume.addVisitorToHistory(v4);
        logFlume.addVisitorToHistory(v5);

        // 4. Export history to CSV (UPDATE THE FILE PATH TO MATCH YOUR SYSTEM!)
        String exportPath = "log_flume_history.csv"; // Relative path (saves in project root)
        // Alternative for Windows: "C:\\Users\\YourName\\Documents\\log_flume_history.csv"
        // Alternative for macOS/Linux: "/Users/YourName/Documents/log_flume_history.csv"

        System.out.printf("\n[Step 2: Export history to CSV file: %s]", exportPath);
        logFlume.exportRideHistory(exportPath);
        System.out.println("[INFO] Check the file path above to verify the exported CSV.");
    }

    // ------------------------------
    // Part7: Demonstrate Import Ride History from CSV
    // Requirements: Import CSV → Print count → Print imported history
    // ------------------------------
    public void partSeven() {
        System.out.println("\n==================================== Part7: Import History from CSV Demonstration =====================================");

        // 1. Create NEW Ride object (no pre-existing history)
        Ride logFlumeImport = new Ride("Log Flume (Import Test)", 4, null);

        // 2. Print history count BEFORE import (should be 0)
        System.out.println("\n[Step 1: Total visitors BEFORE import]");
        logFlumeImport.numberOfVisitors();

        // 3. Import history from CSV (use the SAME path as Part6!)
        String importPath = "log_flume_history.csv"; // Must match Part6's export path

        System.out.printf("\n[Step 2: Import history from CSV file: %s]", importPath);
        logFlumeImport.importRideHistory(importPath);

        // 4. Print history count AFTER import (should be 5)
        System.out.println("\n[Step 3: Total visitors AFTER import]");
        logFlumeImport.numberOfVisitors();

        // 5. Print imported history to verify details
        System.out.println("\n[Step 4: Print imported ride history]");
        logFlumeImport.printRideHistory();
    }
}

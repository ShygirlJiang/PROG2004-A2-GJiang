/**
 * Class Visitor: A subclass of Person, representing theme park visitors.
 * Contains visitor-specific attributes (visitor type, visit date) and overrides
 * the equals() method (required for Part 4A to check if a visitor exists in ride history).
 */
public class Visitor extends Person {
    // Type of the visitor (e.g., "Adult", "Child", "Senior")
    private String visitorType;

    // Date the visitor entered the park (format: "yyyy-MM-dd", e.g., "2025-12-01")
    private String visitDate;

    /**
     * Default no-argument constructor: Calls the parent class (Person) no-arg constructor.
     */
    public Visitor() {
        super();
    }

    /**
     * Parameterized constructor: Initializes Person's attributes and Visitor's specific attributes.
     *
     * @param id Person's unique ID (inherited from Person)
     * @param name Person's full name (inherited from Person)
     * @param age Person's age (inherited from Person)
     * @param visitorType Type of the visitor (e.g., "Adult")
     * @param visitDate Date the visitor entered the park (format: "yyyy-MM-dd")
     */
    public Visitor(String id, String name, int age, String visitorType, String visitDate) {
        super(id, name, age); // Initialize parent (Person) attributes first
        this.visitorType = visitorType;
        this.visitDate = visitDate;
    }

    /**
     * Overrides the default equals() method: Compares visitors by their unique ID (inherited from Person).
     * Required for Part 4A (checking if a visitor exists in ride history via LinkedList.contains()).
     *
     * @param o The object to compare with this Visitor
     * @return true if o is a Visitor with the same ID; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        // Check if the object is the same instance as this
        if (this == o) return true;
        // Check if o is null or not a Visitor instance
        if (o == null || getClass() != o.getClass()) return false;
        // Cast o to Visitor type
        Visitor visitor = (Visitor) o;
        // Compare unique IDs (inherited from Person)
        return getId().equals(visitor.getId());
    }

    /**
     * Gets the visitor's type (e.g., "Adult").
     * @return Visitor's type
     */
    public String getVisitorType() {
        return visitorType;
    }

    /**
     * Sets the visitor's type.
     * @param visitorType New type to assign (e.g., "Child")
     */
    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    /**
     * Gets the date the visitor entered the park.
     * @return Visit date (format: "yyyy-MM-dd")
     */
    public String getVisitDate() {
        return visitDate;
    }

    /**
     * Sets the date the visitor entered the park.
     * @param visitDate New visit date (format: "yyyy-MM-dd")
     */
    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }
}
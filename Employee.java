/**
 * Class Employee: A subclass of Person, representing park employees (e.g., ride operators).
 * Contains employee-specific attributes (employee ID, work department) in addition to Person's attributes.
 * Implements inheritance requirements from Part 1 of the assignment.
 */
public class Employee extends Person {
    // Unique identification number assigned to the employee (e.g., "E001")
    private String employeeId;

    // Department where the employee works (e.g., "Roller Coaster Area" or "Carousel Zone")
    private String department;

    /**
     * Default no-argument constructor: Calls the parent class (Person) no-arg constructor.
     */
    public Employee() {
        super(); // Explicitly call parent's no-arg constructor (optional but clear)
    }

    /**
     * Parameterized constructor: Initializes both Person's attributes and Employee's specific attributes.
     * Uses super() to pass parent-class parameters first.
     *
     * @param id Person's unique ID (inherited from Person)
     * @param name Person's full name (inherited from Person)
     * @param age Person's age (inherited from Person)
     * @param employeeId Employee's unique work ID
     * @param department Employee's assigned work department
     */
    public Employee(String id, String name, int age, String employeeId, String department) {
        super(id, name, age); // Initialize parent (Person) attributes first
        this.employeeId = employeeId;
        this.department = department;
    }

    /**
     * Gets the employee's unique work ID.
     * @return Employee's ID (e.g., "E001")
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the employee's unique work ID.
     * @param employeeId New work ID to assign
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets the department where the employee works.
     * @return Employee's work department (e.g., "Roller Coaster Area")
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department where the employee works.
     * @param department New work department to assign
     */
    public void setDepartment(String department) {
        this.department = department;
    }
}
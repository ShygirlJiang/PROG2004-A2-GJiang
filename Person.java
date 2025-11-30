/**
 * Abstract class Person: Serves as the parent class for Employee and Visitor,
 * storing common attributes (unique ID, name, age) of all persons in the theme park system.
 * This class cannot be instantiated directly (as per Part 2 requirements of the assignment).
 */
public abstract class Person {
    // Unique identifier for the person (e.g., system-generated ID or ID card number)
    private String id;

    // Full name of the person
    private String name;

    // Age of the person
    private int age;

    /**
     * Default no-argument constructor: Used for subclass initialization when no parameters are provided.
     */
    public Person() {}

    /**
     * Parameterized constructor: Initializes all core attributes of the Person class.
     * Subclasses (Employee/Visitor) will call this via the super() keyword.
     *
     * @param id Unique identifier of the person
     * @param name Full name of the person
     * @param age Age of the person
     */
    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    /**
     * Gets the unique ID of the person.
     * @return The person's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique ID of the person.
     * @param id New ID to assign
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the full name of the person.
     * @return The person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the person.
     * @param name New name to assign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the person.
     * @return The person's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the person.
     * @param age New age to assign
     */
    public void setAge(int age) {
        this.age = age;
    }
}
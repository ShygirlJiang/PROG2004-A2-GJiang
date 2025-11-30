import java.util.Comparator;
import java.time.LocalDate;

/**
 * Comparator for Visitor objects: Sorts visitors first by age (ascending), then by visit date (ascending).
 * Implements Comparator<Visitor> (Part 4B requirement: Cannot use Comparable interface).
 */
public class VisitorComparator implements Comparator<Visitor> {

    /**
     * Compares two Visitor objects using two attributes:
     * 1. First compare age (ascending order: younger visitors come first)
     * 2. If ages are equal, compare visit date (ascending order: earlier dates come first)
     *
     * @param v1 The first Visitor to compare
     * @param v2 The second Visitor to compare
     * @return Negative integer if v1 comes before v2; positive if v1 comes after v2; 0 if equal
     * @throws IllegalArgumentException If visit date format is invalid (requires "yyyy-MM-dd")
     */
    @Override
    public int compare(Visitor v1, Visitor v2) {
        // Step 1: Compare ages (int type, direct subtraction for ascending order)
        int ageComparison = v1.getAge() - v2.getAge();
        if (ageComparison != 0) {
            return ageComparison; // Ages differ: return result immediately
        }

        // Step 2: Ages are equal → Compare visit dates (convert String to LocalDate for valid date comparison)
        try {
            LocalDate date1 = LocalDate.parse(v1.getVisitDate()); // Parse "yyyy-MM-dd" to LocalDate
            LocalDate date2 = LocalDate.parse(v2.getVisitDate());
            return date1.compareTo(date2); // Ascending order: earlier date → smaller value
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid visit date format (required: yyyy-MM-dd). Error: " + e.getMessage());
        }
    }
}
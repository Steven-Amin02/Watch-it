package Model;
import java.util.HashSet;
import java.util.Random;

public class IDGenerator {
    private static final HashSet<String> existingIDs = new HashSet<>();
    private static final Random random = new Random();

    /**
     * Generate a unique ID.
     *
     * @param type 'U' for user, 'A' for admin
     * @return A unique ID string
     */
    public static String generateID(char type) {
        if (type != 'U' && type != 'A') {
            throw new IllegalArgumentException("Invalid type. Use 'U' for User and 'A' for Admin.");
        }

        String newID;
        do {
            int randomDigits = 10000 + random.nextInt(90000); // Generates a 5-digit number
            newID = type + String.valueOf(randomDigits);
        } while (existingIDs.contains(newID)); // Ensure uniqueness

        existingIDs.add(newID);
        return newID;
    }
}
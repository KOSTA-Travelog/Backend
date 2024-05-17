package kosta.travelog.util;
import java.util.UUID;

public class UUIDGenetator {

    // Method to generate and return a UUID as a string
    public String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

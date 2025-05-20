package dev.pabllopf.ecommerceprice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The PasswordHashes class is a utility class designed to generate hashed passwords.
 * It uses BCrypt hashing to securely encode passwords, which can then be stored in the database
 * for user authentication purposes. This class is intended to be run as a standalone application
 * to generate hashed password values for users.
 */
public class PasswordHashes {

    /**
     * The main method serves as the entry point for this utility class.
     * It creates an instance of the BCryptPasswordEncoder and uses it to generate hashed passwords
     * for various user roles such as "user1", "user2", "admin", and "superadmin".
     * <p>
     * The hashed passwords generated here can be used for user authentication within the system.
     *
     * @param args Command-line arguments, not used in this case.
     */
    public static void main(String[] args) {
        // Instantiate BCryptPasswordEncoder to hash passwords securely.
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Print out the hashed passwords for "user1", "user2", "admin", and "superadmin".
        // These hashed passwords can be used for setting up users with pre-defined passwords.
        System.out.println(encoder.encode("user1"));  // Hash for the password "user1"
        System.out.println(encoder.encode("user2"));  // Hash for the password "user2"
        System.out.println(encoder.encode("admin"));  // Hash for the password "admin"
        System.out.println(encoder.encode("superadmin"));  // Hash for the password "superadmin"
    }
}


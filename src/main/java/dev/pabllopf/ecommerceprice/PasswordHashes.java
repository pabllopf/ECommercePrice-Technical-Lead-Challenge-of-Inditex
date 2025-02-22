package dev.pabllopf.ecommerceprice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashes {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("user1"));  // Use this for each password
        System.out.println(encoder.encode("user2"));
        System.out.println(encoder.encode("admin"));
        System.out.println(encoder.encode("superadmin"));
    }
}

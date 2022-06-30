package com.basicauth.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The type Login service.
 */
@Service
public class LoginService {

    /**
     * The constant USER.
     */
    private static final String USER = "ADMIN";

    /**
     * The constant PASSWORD.
     */
    private static final String PASSWORD = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";

    /**
     * Check authentication boolean.
     *
     * @param user     the user
     * @param password the password
     * @return the boolean
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public boolean checkAuthentication(String user,String password) throws NoSuchAlgorithmException {
        String generatedHash = generateHash(password);
        if(PASSWORD.equals(generatedHash) && USER.equals(user))
            return true;
        return false;
    }

    /**
     * Generate hash string.
     *
     * @param password the password
     * @return the string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public  String generateHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash =  md.digest(password.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}

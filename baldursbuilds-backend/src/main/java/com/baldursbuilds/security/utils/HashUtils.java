package com.baldursbuilds.security.utils;

import lombok.Builder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtils {
    private static final String ENCRYPT_ALG = "SHA-256";

    public static String encryptToSha256(String str, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ENCRYPT_ALG);
            String saltedStr = str.concat(salt);

            byte[] hash = digest.digest(saltedStr.getBytes());

            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing the password", e);
        }
    }

    public static boolean compareHash(String rawStr, String hash, String salt) {
        String newHashedStr = encryptToSha256(rawStr, salt);
        return newHashedStr.equals(hash);
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
       byte[] salt = new byte[16];
       random.nextBytes(salt);
       return bytesToHex(salt);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);

            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

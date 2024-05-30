package com.korn.lakes.model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class M_Crypto {

    // Konfigurationsparameter für PBKDF2
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    public static long createSalt(){
        return new SecureRandom().nextLong();
    }
//
    public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        // Erzeuge den Hash
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();

        // Kombiniere Salt und Hash und konvertiere sie in einen Base64-codierten String
        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(salt) + ":" + enc.encodeToString(hash);
    }
//
//    public static boolean verifyPassword(String password, String storedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        // Teile den gespeicherten Hash in Salt und Hash auf
//        String[] parts = storedHash.split(":");
//        byte[] salt = Base64.getDecoder().decode(parts[0]);
//        byte[] hash = Base64.getDecoder().decode(parts[1]);
//
//        // Erzeuge den Hash des eingegebenen Passworts mit dem gleichen Salt
//        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        byte[] testHash = skf.generateSecret(spec).getEncoded();
//
//        // Vergleiche den eingegebenen Hash mit dem gespeicherten Hash
//        return java.util.Arrays.equals(hash, testHash);
    }

//    public static void main(String[] args) {
//        try {
//            String password = "meinSicheresPasswort";
//            String hash = hashPassword(password);
//            System.out.println("Gespeicherter Hash: " + hash);
//
//            // Teste die Passwort-Überprüfung
//            boolean isMatch = verifyPassword(password, hash);
//            System.out.println("Passwort korrekt: " + isMatch);
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//    }

//}


//Copilot:
//        import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class HashingExample {
//    public static void main(String[] args) {
//        String input = "meinegeheimeemail@example.com"; // Hier deine E-Mail-Adresse oder Passwort einsetzen
//
//        try {
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] hashBytes = digest.digest(input.getBytes());
//
//            // Konvertiere die Bytes in einen Hexadezimal-String
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : hashBytes) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//
//            System.out.println("Hashwert: " + hexString.toString());
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//}


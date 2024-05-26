package com.korn.lakes.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class M_hash {

    // Konfigurationsparameter für PBKDF2
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;

    public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Generiere ein Salt
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        sr.nextBytes(salt);

        // Erzeuge den Hash
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();

        // Kombiniere Salt und Hash und konvertiere sie in einen Base64-codierten String
        Base64.Encoder enc = Base64.getEncoder();
        return enc.encodeToString(salt) + ":" + enc.encodeToString(hash);
    }

    public static boolean verifyPassword(String password, String storedHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Teile den gespeicherten Hash in Salt und Hash auf
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);

        // Erzeuge den Hash des eingegebenen Passworts mit dem gleichen Salt
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        // Vergleiche den eingegebenen Hash mit dem gespeicherten Hash
        return java.util.Arrays.equals(hash, testHash);
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

}

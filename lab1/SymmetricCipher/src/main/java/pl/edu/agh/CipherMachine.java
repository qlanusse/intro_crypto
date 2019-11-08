package pl.edu.agh;


import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.KeySpec;


public class CipherMachine {
    private String password = "password";
    private String salt = "salt1234";
    private final static byte[] iv = {17, 132-32, 54, 98, 223-128,
            7, 42, 113, 135-32, 201-128, 59, 69, 11, 35, 148-21, 12};

    // Initialization vector
    private static final IvParameterSpec ivspec = new IvParameterSpec(iv);

    // Constructor
    public CipherMachine(){
        Security.addProvider(new BouncyCastleProvider());
    }

    public byte[] encrypt(byte [] message)  {
        byte[] ct = new byte[0];
        try {
            // Generating secret key
            SecretKeyFactory secretKeyFactory = SecretKeyFactory .getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
            SecretKey tmp = secretKeyFactory.generateSecret(keySpec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            // Cipher initialization
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

            // Encryption
            ct = cipher.doFinal(message);
            return ct;
        } catch (Exception e) {
            System.out.println("Error during encryption");
            e.printStackTrace();
        }

        return null;
    }
    public byte[] decrypt(byte [] message)  {
        byte[] ct = new byte[0];
        try {
            // Generating secret key
            SecretKeyFactory secretKeyFactory = SecretKeyFactory .getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
            SecretKey tmp = secretKeyFactory.generateSecret(keySpec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            // Cipher initialization
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

            // Decryption
            ct = cipher.doFinal(message);
            return ct;
        } catch (Exception e) {
            System.out.println("Error during decryption");
            e.printStackTrace();
        }

        return null;
    }

}

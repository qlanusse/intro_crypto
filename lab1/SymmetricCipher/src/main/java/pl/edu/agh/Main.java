package pl.edu.agh;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        long startTime = System.nanoTime();
	    if (args[0].contains("enc")) {
	        /* Encryption of file passed as Arg1 in to file passed as Arg2 */
	        System.out.println("Encrypting file!");

	        // Initialization of cipher machine
            CipherMachine machine = new CipherMachine();

            // Get bytes from the file to encrypt
            byte[] message = "".getBytes();
            try {
                message = Files.readAllBytes(Paths.get(args[1]));
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Encrypt message
            byte [] res = machine.encrypt(message);

            // Writing encrypted message as file passed as Arg2
            try {
                FileOutputStream fileOut = new FileOutputStream(args[2]);
                fileOut.write(res);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(args[0].contains("dec")) {
            /* Decryption of file passed as Arg1 in to file passed as Arg2 */
            System.out.println("Decrypting file!");

            // Cipher machine initialization
            CipherMachine machine = new CipherMachine();

            // Get the ciphered file
            byte[] cipheredMessage = "".getBytes();
            try {
                cipheredMessage = Files.readAllBytes(Paths.get(args[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Decrypt cipher file
            byte [] res = machine.decrypt(cipheredMessage);

            // Write decrypted file as arg2
            try {
                FileOutputStream fileOut = new FileOutputStream(args[2]);
                fileOut.write(res);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else System.out.println("Undefined command!");

        long endTime = System.nanoTime();

        double duration = (endTime-startTime)/1000000;

        System.out.println("The "+args[0] +"cryption took "+duration+" miliseconds.");


    }
}

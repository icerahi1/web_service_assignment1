package network;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Connecting to server...");
        try (Socket socket = new Socket("localhost", 5000);
             InputStream is = socket.getInputStream();
             FileOutputStream fos = new FileOutputStream("received_student.xml")) {

            System.out.println("Connected! Receiving file...");
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("File saved as received_student.xml");
        }
    }
}
package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server starting on port 5000...");
        try (ServerSocket serverSocket = new ServerSocket(5000);
             Socket clientSocket = serverSocket.accept();
             FileInputStream fis = new FileInputStream("src/resources/student.xml");
             OutputStream os = clientSocket.getOutputStream()) {

            System.out.println("Client connected! Sending student.xml...");
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            System.out.println("File sent successfully.");
        }
    }
}
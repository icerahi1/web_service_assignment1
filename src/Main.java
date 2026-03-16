import model.Student;
import network.Client;
import network.Server;
import transformer.JAXBTransformer;
import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.xml.accessExternalDTD", "all");
        // 1. Start Server in the background
        new Thread(() -> {
            try { Server.main(null); } catch (Exception e) { e.printStackTrace(); }
        }).start();

        Thread.sleep(1000); // Give server a second to start

        // 2. Run Client to receive the file
        Client.main(null);

        // 3. Transform XML to POJO
        System.out.println("\n--- Unmarshaling: XML to POJO ---");
        JAXBTransformer transformer = new JAXBTransformer();
        File receivedFile = new File("received_student.xml");
        Student student = transformer.transformToPOJO(receivedFile);
        System.out.println(student.toString());

        // 4. Transform POJO back to XML
        System.out.println("\n--- Marshaling: POJO to XML ---");
        File newXmlFile = new File("final_output.xml");
        transformer.transformToXML(student, newXmlFile);
    }
}
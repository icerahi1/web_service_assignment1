import model.Student;
import network.Client;
import network.Server;
import transformer.JAXBTransformer;
import java.io.File;
import model.DbManager;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("javax.xml.accessExternalDTD", "all");

        new Thread(() -> {
            try { Server.main(null); } catch (Exception e) { e.printStackTrace(); }
        }).start();

        Thread.sleep(1000);


        Client.main(null);

        // transform from xml to polo
        System.out.println("\n unmarshaling , xml to java object.....");
        JAXBTransformer transformer = new JAXBTransformer();
        File receivedFile = new File("received_student.xml");
        Student student = transformer.transformToPOJO(receivedFile);
        System.out.println(student.toString());

        // database
        System.out.println("\n saving to database ......");
        DbManager dbManager = new DbManager();
        dbManager.setupDatabase();
        dbManager.saveStudent(student);

        //transform polo back to xml
        System.out.println("\n java object to xml....");
        File newXmlFile = new File("final_output.xml");
        transformer.transformToXML(student, newXmlFile);
    }
}
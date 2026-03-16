package transformer;

import model.Student;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

/**
 * Handles marshaling and unmarshaling of Student objects using JAXB.
 */
public class JAXBTransformer {

    /**
     * Transforms a POJO to an XML file.
     * @param student The student object.
     * @param file The output XML file.
     * @throws JAXBException if marshaling fails.
     */
    public void transformToXML(Student student, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(student, file);
        marshaller.marshal(student, System.out);
    }

    /**
     * Transforms an XML file to a POJO with XSD validation.
     * @param file The input XML file.
     * @return The unmarshaled Student object.
     * @throws JAXBException if unmarshaling fails.
     * @throws SAXException if XSD schema processing fails.
     */
    public Student transformToPOJO(File file) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Apply XSD Validation
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("src/resources/student.xsd"));
        unmarshaller.setSchema(schema);

        return (Student) unmarshaller.unmarshal(file);
    }
}
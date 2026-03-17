package transformer;

import model.Student;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;

//simple unit tests for JAXBTransformer.
public class JAXBTransformerTest {

    @Test
    public void testTransformToPOJO() throws Exception {
        JAXBTransformer transformer = new JAXBTransformer();
        File xmlFile = new File("src/resources/student.xml");

        Student student = transformer.transformToPOJO(xmlFile);

        Assert.assertNotNull("Student object should not be null", student);
        Assert.assertEquals("Name should be Imran", "Imran", student.getName());
    }
}
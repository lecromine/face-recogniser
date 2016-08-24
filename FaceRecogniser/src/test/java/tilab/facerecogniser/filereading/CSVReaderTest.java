package tilab.facerecogniser.filereading;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lecromine
 */
public class CSVReaderTest {

    String filepath = "C:\\Users\\Lecromine\\Documents\\savedfiles\\";
    CSVReader csvReader = new CSVReader();

    public CSVReaderTest() {
    }

    @Test
    public void saveTest() throws IOException {
        
        long beginningTime = System.currentTimeMillis();
        
        double[][] testMatrix = new double[50][50];
        Random random = new Random();

        for (int i = 0; i < testMatrix.length; i++) {
            for (int j = 0; j < testMatrix[0].length; j++) {
                testMatrix[i][j] = random.nextDouble();
            }
        }

        csvReader.save(filepath + "testMatrix.csv", testMatrix);

        double[][] loadedTestMatrix = csvReader.load(
                filepath + "testMatrix.csv", 50, 50);

        for (int i = 0; i < testMatrix.length; i++) {
            if (!Arrays.equals(loadedTestMatrix[i], testMatrix[i])) {
                fail("after saving and loading the matrix, the saved one "
                        + "does not match the loaded one");
            }
        }
        
        long endingTime = System.currentTimeMillis();
        
        System.out.println("Duration of the operation: " + 
                (endingTime - beginningTime) + "ms.");
    
        
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

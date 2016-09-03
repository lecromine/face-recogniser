/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilab.facerecogniser.projection;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tilab.facerecogniser.filereading.CSVReader;
import tilab.facerecogniser.filereading.PGMReader;

/**
 *
 * @author Lecromine
 */
public class RandomProjectionTest {

    CSVReader csvReader = new CSVReader();

    public RandomProjectionTest() {
    }

    @Test
    public void bindTogetherTest() throws IOException {
        String filepath = "src/main/resources";
        File file = new File(filepath);
        RandomProjection randomProjection = new RandomProjection(file.getAbsolutePath());
        RandomMatrix rMatrix = new RandomMatrix(file.getAbsolutePath());
        PGMReader reader = new PGMReader(file.getAbsolutePath());
        
        randomProjection.setFilePath(filepath);

        double[] testVec = new double[10340];
        
        for (int i = 0; i < testVec.length; i++) {
            testVec[i] = 0.0;
        }

        randomProjection.bindTogether(testVec);

        if (!Arrays.equals(
                randomProjection.projectedFaceMat[randomProjection.projectedFaceMat.length - 1],
                testVec)) {

            fail("bindTogether method does not work");

        }

    }

    @Test
    public void loadProjectedFaceMatTest() throws IOException {

        String filepath = "src/main/resources";
        File file = new File(filepath);
        RandomProjection randomProjection = new RandomProjection(file.getAbsolutePath());
        RandomMatrix rMatrix = new RandomMatrix(file.getAbsolutePath());
        PGMReader pgmReader = new PGMReader(file.getAbsolutePath());

        pgmReader.initializeDatabase(randomProjection, rMatrix);

        double[][] testMat = csvReader.load(
                file.getAbsolutePath() + "/ProjectedFaceMatrix.csv",
                0, 500);

        double[][] projectedFaceMat = randomProjection.projectedFaceMat;

        for (int i = 0; i < projectedFaceMat.length; i++) {

            if (!Arrays.equals(projectedFaceMat[i], testMat[i])) {
                
                fail("projected face mat does not equal test mat");
            }
        }

    }

    @Test
    public void randomProjectionTest() throws IOException {
        String filepath = "src/main/resources";
        File file = new File(filepath);
        RandomProjection randomProjection = new RandomProjection(file.getAbsolutePath());
        RandomMatrix rMatrix = new RandomMatrix(file.getAbsolutePath());
        PGMReader reader = new PGMReader(file.getAbsolutePath());
        
        Random random = new Random();
        int[] testVec = new int[10340];

        for (int i = 0; i < 10; i++) {
            testVec[i] = random.nextInt(100);
        }

        double[] projectedTestVec = randomProjection.randomProjection(rMatrix, testVec);

        if (!Arrays.equals(rMatrix.multiplicator(testVec), projectedTestVec)) {
            fail("random projection does not work");
        }

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

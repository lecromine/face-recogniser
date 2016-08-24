/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilab.facerecogniser.projection;

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
    PGMReader pgmReader = new PGMReader();
    String filepath = "C:\\Users\\Lecromine\\Documents\\savedfiles\\";

    public RandomProjectionTest() {
    }

    @Test
    public void bindTogetherTest() throws IOException {
        RandomProjection randomProjection = new RandomProjection();
        
        randomProjection.setFilepath(filepath);

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

        RandomProjection randomProjection = new RandomProjection();
        RandomMatrix rMatrix = new RandomMatrix();

        randomProjection.setFilepath(filepath);
        rMatrix.setFilePath(filepath);

        pgmReader.addFaces(randomProjection, rMatrix);

        double[][] testMat = csvReader.load(
                "C:\\Users\\Lecromine\\Documents\\savedfiles\\ProjectedFaceMatrix.csv",
                0, 500);

        double[][] projectedFaceMat = randomProjection.projectedFaceMat;

        for (int i = 0; i < projectedFaceMat.length; i++) {

            if (!Arrays.equals(projectedFaceMat[i], testMat[i])) {
                
                fail("projected face mat does not equal test mat");
            }
        }

    }

    @Test
    public void setProjectedFaceMatFilePathTest() throws IOException {
        RandomProjection randomProjection = new RandomProjection();
        randomProjection.setFilepath(filepath);

        if (randomProjection.getFilepath().equals(
                "C:\\Users\\Lecromine\\ProjectedFaceMatrix.csv"));

    }

    @Test
    public void randomProjectionTest() throws IOException {
        RandomProjection randomProjection = new RandomProjection();
        RandomMatrix rMatrix = new RandomMatrix();
        randomProjection.setFilepath(filepath);
        rMatrix.setFilePath(filepath);
        
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

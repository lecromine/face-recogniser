/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilab.facerecogniser.projection;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tilab.facerecogniser.filereading.PGMReader;

/**
 *
 * @author Lecromine
 */
public class RandomMatrixTest {

    public RandomMatrixTest() {
    }

    @Test
    public void intializeMatrixTest1() throws IOException {

        String filepath = "src/main/resources";
        File file = new File(filepath);
        RandomProjection randomProjection = new RandomProjection(file.getAbsolutePath());
        RandomMatrix rMatrix = new RandomMatrix(file.getAbsolutePath());
        PGMReader reader = new PGMReader(file.getAbsolutePath());


        double[][] testMat = new double[500][10340];

        for (int i = 0; i < testMat.length; i++) {
            for (int j = 0; j < testMat[0].length; j++) {
                testMat[i][j] = 0.0;
            }
        }

        for (int i = 0; i < rMatrix.rMatrix.length; i++) {
            if (Arrays.equals(rMatrix.rMatrix[i], testMat[i])) {
                fail("something went wrong");
            }
        }
    }

    @Test
    public void initializeMatrixTest2() throws IOException {
        String filepath = "src/main/resources";
        File file = new File(filepath);
        RandomProjection randomProjection = new RandomProjection(file.getAbsolutePath());
        RandomMatrix rMatrix = new RandomMatrix(file.getAbsolutePath());
        PGMReader reader = new PGMReader(file.getAbsolutePath());

        rMatrix.initializeRMatrix();

        double[][] testrMatrix = Arrays.copyOf(rMatrix.rMatrix, rMatrix.rMatrix.length);

        rMatrix.initializeRMatrix();

        for (int i = 0; i < rMatrix.rMatrix.length; i++) {

            if (!Arrays.equals(rMatrix.rMatrix[i], testrMatrix[i])) {
                fail("The random matrix initializer changes the matrix");
            }
        }
    }

    @Test
    public void multiplicatorTest() {

//        int[][] A = {{4, 3}, {2, 1}};
//        int[][] B = {{1, 2}, {1, -2}};
//
//        RandomMatrix rand = new RandomMatrix();
//
//        double[][] C = rand.multiplicator(A, B);
//
//        double[][] checker = {{7.0, 2.0}, {3.0, 2.0}};
//               
//        if (C.equals(checker)) {
//            fail("The variables of the resulting matrix does not match to what was expected");
//
//        }
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

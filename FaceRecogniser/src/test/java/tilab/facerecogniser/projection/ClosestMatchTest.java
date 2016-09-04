/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilab.facerecogniser.projection;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tilab.facerecogniser.filereading.PGMReader;

/**
 *
 * @author Lecromine
 */
public class ClosestMatchTest {

    ClosestMatch closestMatch = new ClosestMatch();
    public ClosestMatchTest() {

    }

    @Test
    public void shortestEuclideanDistanceTest1() throws IOException {

        long beginningTime = System.currentTimeMillis();

        String filepath = "src/main/resources";

        File file = new File(filepath);
        
        RandomProjection randomProjection = new RandomProjection(new File(file.getAbsolutePath() + "/ProjectedFaceMatrix.csv"));
        RandomMatrix rMatrix = new RandomMatrix(new File(file.getAbsolutePath() + "/RandomMatrix.csv"));
        PGMReader pgmReader = new PGMReader(randomProjection, rMatrix, new File(file.getAbsolutePath()));
        
        

        int[] testFaceVec = pgmReader.readFile(
                new File(file.getAbsolutePath() + "/facegallery/s20/2.pgm"));

        double[] testProjectedVec = randomProjection.randomProjection(
                rMatrix, testFaceVec);

        int indexOfClosest = closestMatch.shortestEuclideanDistance(
                randomProjection.projectedFaceMat, testProjectedVec);

        if (indexOfClosest != 192) {
            fail("index of closest " + indexOfClosest + " != 192" + randomProjection.getFile().getAbsolutePath() + " " + rMatrix.getFile().getAbsolutePath());
        }

        long endingTime = System.currentTimeMillis();

        System.out.println("Duration of the operation: "
                + (endingTime - beginningTime) + "ms.");

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

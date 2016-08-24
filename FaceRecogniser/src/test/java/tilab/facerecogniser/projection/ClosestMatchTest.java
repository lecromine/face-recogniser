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
    PGMReader reader = new PGMReader();
    String filepath = "C:\\Users\\Lecromine\\Documents\\savedfiles\\";
    
    public ClosestMatchTest() {
    }
    
    @Test
    public void shortestEuclideanDistanceTest1() throws IOException {
        
        long beginningTime = System.currentTimeMillis();
        
        RandomProjection randomProjection = new RandomProjection();
        RandomMatrix rMatrix = new RandomMatrix();
        
        randomProjection.setFilepath(filepath);
        rMatrix.setFilePath(filepath);
        
        int[] testFaceVec = reader.readFile(
                new File("C:\\Users\\Lecromine\\Documents\\facegallery\\s20\\2.pgm"));
        
        double[] testProjectedVec = randomProjection.randomProjection(
                rMatrix, testFaceVec);
        
        int indexOfClosest = closestMatch.shortestEuclideanDistance(
                randomProjection.projectedFaceMat, testProjectedVec);
        
        if (indexOfClosest != 192) {
            fail("index of closest " + indexOfClosest + " != 10");
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

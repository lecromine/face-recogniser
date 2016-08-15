/*
This class is called by the main class and it binds together all the operations that are distributed to other classes.
 */
package tilab.facerecogniser;

import java.io.IOException;

public class Recogniser {

    Reader reader = new Reader();
    RandomProjection RP = new RandomProjection();
    RandomMatrix rMatrix = new RandomMatrix();

    public Recogniser() {
        rMatrix.initializeRMatrix();
    }

    public int[] recogniseFace() throws IOException {
        int[] closestMatch = new int[0];

        int[][] faceMat = new int[0][0];
        double[][] projectedFaceMat = new double[0][0];
        
        double[] projectedFaceVec = new double[0];

        faceMat = reader.readATTFiles(faceMat);
        
        int[] faceVec = faceMat[1];

        projectedFaceVec = RP.randomProjection(rMatrix, faceVec);

        for (int i = 0; i < projectedFaceVec.length; i++) {
                System.out.print(projectedFaceVec[i] + ", ");
        }
        
        return closestMatch;
    }

}

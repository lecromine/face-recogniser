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

        faceMat = reader.readATTFiles(faceMat);

        projectedFaceMat = RP.randomProjection(rMatrix, faceMat);

        for (int i = 0; i < projectedFaceMat.length; i++) {
            for (int j = 0; j < projectedFaceMat[0].length; j++) {
                System.out.print(projectedFaceMat[i][j] + ", ");
            }
            System.out.println("");
        }
        
        return closestMatch;
    }

}

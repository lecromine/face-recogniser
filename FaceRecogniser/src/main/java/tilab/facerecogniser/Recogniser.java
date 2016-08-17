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
    
    /*
    This method adds new faces to the database. All the uploaded faces are listed in the parameter projectedFaceMat in the RandomProjection class.
    */

    public void addFaces() throws IOException {

        double[] projectedFaceVec = new double[0];

        reader.readATTFiles();

        for (int i = 0; i < reader.faceMat.length; i++) {

            int[] faceVec = reader.faceMat[i];

            projectedFaceVec = RP.randomProjection(rMatrix, faceVec);

            RP.bindTogether(projectedFaceVec);

        }

        for (int i = 0; i < RP.projectedFaceMat.length; i++) {
            for (int j = 0; j < 50; j++) {
                System.out.print(RP.projectedFaceMat[i][j] + ", ");
            }
            System.out.println("");
        }

    }

}

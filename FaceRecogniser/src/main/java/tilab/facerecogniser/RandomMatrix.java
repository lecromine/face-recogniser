package tilab.facerecogniser;

import java.util.Random;

/*
This class contains information about the random matrix that is used in random projections.
Each element of the RMatrix corresponds to a random variable from normal distribution.
@param int[][] RMatrix  the random matrix that is used to project faces to the lower dimension
 */
public class RandomMatrix {

    double[][] RMatrix = new double[500][10340];

    public RandomMatrix() {

    }

    /*
    This method checks if there's already an existing random matrix. This speeds up the recognition process significantly:
    the user does not need to create a new random matrix per every recognition.
     */
    public void getSavedRMatrix() {

    }

    /*
    This method checks if random matrix already exists.
     */
    public boolean rMatrixExists() {
        return false;
    }

    /* 
    This method saves the random matrix.
     */
    public void saveRMatrix() {

    }

    /*
    This method initializes the random matrix that is used throughout the execution. In the final version
    this matrix will be created only once and then saved for later use. The random matrix will be k*10340
    where k << 10340 (meaning that k is significantly smaller than 10340).
     */
    public void initializeRMatrix() {

        if (rMatrixExists() == false) {
            Random r = new Random();

            for (int i = 0; i < RMatrix.length; i++) {
                for (int j = 0; j < RMatrix[i].length; j++) {
                    RMatrix[i][j] = r.nextGaussian();
                }
            }

            saveRMatrix();
        } else {
        }
    }

    /*
    This method casts the matrix multiplication -operation on the matrixes it is given.
    @param int[] A     vector in C = RMatrix * A
    @return            solution
     */
    public double[] multiplicator(int[] A) {
        double[] projectedVector = new double[RMatrix.length];

        int rRows = RMatrix.length;
        int rColumns = RMatrix[0].length;
        int aRows = A.length;
        int aColumns = 1;

        if (rColumns != aRows) {
            throw new IllegalArgumentException("Rows (" + rColumns + ") did not match columns (" + aRows + ").");
        }

        for (int i = 0; i < 2; i++) {
            projectedVector[i] = 0.00000;
        }

        for (int i = 0; i < rRows; i++) {
            for (int k = 0; k < rColumns; k++) {
                projectedVector[i] += RMatrix[i][k] * A[k];
            }

        }

        return projectedVector;
    }

    public double[][] getRMatrix() {
        return this.RMatrix;
    }

    public int getWidth() {
        return this.RMatrix[1].length;
    }

    public int getHeight() {
        return this.RMatrix.length;
    }
}

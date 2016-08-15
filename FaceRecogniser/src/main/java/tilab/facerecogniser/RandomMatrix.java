package tilab.facerecogniser;

import java.util.Random;

/*
This class contains information about the random matrix that is used in random projections.
Each element of the RMatrix corresponds to a random variable from normal distribution.
@param int[][] RMatrix  the random matrix that is used to project faces to the lower dimension
 */
public class RandomMatrix {

    double[][] RMatrix = new double[10340][400];

    public RandomMatrix() {

    }

    /*
    This method initializes the random matrix that is used throughout the execution. In the final version
    this matrix will be created only once and then saved for later use. The random matrix will be k*10340
    where k << 10340 (meaning that k is significantly smaller than 10340).
     */
    public void initializeRMatrix() {
        Random r = new Random();
        
        for (int i = 0; i < RMatrix.length; i++) {
            for (int j = 0; j < RMatrix[i].length; j++) {
                RMatrix[i][j] = r.nextGaussian();
            }
        }

    }
    
    /*
    This method casts the matrix multiplication -operation on the matrixes it is given.
    @param int[][] A    first matrix in C = AB
    @param int[][] B    second matrix in C = AB
    @return             solution
    */
    
    public double[][] multiplicator(int[][] A) {
        double[][] projectedMatrix = new double[RMatrix.length][A[0].length];
        
        int rRows = RMatrix.length;
        int rColumns = RMatrix[0].length;
        int aRows = A.length;
        int aColumns = A[0].length;

        if (rColumns != aRows) {
            throw new IllegalArgumentException("Rows (" + rColumns + ") did not match columns (" + aRows + ").");
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                projectedMatrix[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < rRows; i++) { // aRow
            for (int j = 0; j < aColumns; j++) { // bColumn
                for (int k = 0; k < rColumns; k++) { // aColumn
                    projectedMatrix[i][j] += RMatrix[i][k] * A[k][j];
                }
            }
        }

        
        return projectedMatrix;
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



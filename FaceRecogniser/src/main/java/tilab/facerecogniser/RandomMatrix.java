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
    
    public double[][] multiplicator(int[][] A, int[][] B) {
        double[][] C = new double[A.length][B[0].length];
        
        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("Rows (" + aColumns + ") did not match columns (" + bRows + ").");
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        
        return C;
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



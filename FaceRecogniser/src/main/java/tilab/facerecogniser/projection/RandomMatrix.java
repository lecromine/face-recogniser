package tilab.facerecogniser.projection;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import tilab.facerecogniser.filereading.CSVReader;

/**
 * This class contains information about the random matrix that is used in random 
 * projections. Each element of the RMatrix corresponds to a random variable from 
 * normal distribution.
 * @author Lecromine
 */
public final class RandomMatrix {

    double[][] rMatrix = new double[500][10340];
    CSVReader writer = new CSVReader();
    File rMatrixFile;

    public RandomMatrix(File file) throws IOException {
        this.rMatrixFile = file;
        initializeRMatrix();
    }

    /**
     * This method initializes the random matrix that is used throughout the 
     * execution. In the final version this matrix will be created only once and 
     * then saved for later use. The random matrix will be k*10340 where k is
     * significantly smaller integer than 10340.
     * @throws IOException fails if PrintWriter fails
     */
    
    public void initializeRMatrix() throws IOException {
        
        if (!rMatrixFile.exists()) {
            Random r = new Random();

            for (double[] rVector : rMatrix) {
                for (int j = 0; j < rVector.length; j++) {
                    rVector[j] = r.nextGaussian();
                }
            }

            writer.save(rMatrixFile, rMatrix);
        } else {
            this.rMatrix = writer.load(rMatrixFile, rMatrix.length, rMatrix[0].length);
        }
    }
    
    /**
     * This method casts the matrix multiplication -operation on the matrices 
     * it is given.
     * @param A an int array that needs to be projected to the subspace R^500
     * @return projected array as a double array
     */
    public double[] multiplicator(int[] A) {
        double[] projectedVector = new double[rMatrix.length];

        int rRows = rMatrix.length;
        int rColumns = rMatrix[0].length;
        int aRows = A.length;
        int aColumns = 1;

        if (rColumns != aRows) {
            throw new IllegalArgumentException(
                    "Rows (" + rColumns + ") did not match columns (" + aRows + ").");
        }

        for (int i = 0; i < projectedVector.length; i++) {
            projectedVector[i] = 0.00000;
        }

        for (int i = 0; i < rRows; i++) {
            for (int k = 0; k < rColumns; k++) {
                projectedVector[i] += rMatrix[i][k] * A[k];
            }
        }

        return projectedVector;
    }
    
    /**
     * Returns the random matrix that is used in projections.
     * @return random matrix
     */

    public double[][] getRMatrix() {
        return this.rMatrix;
    }
    
    /**
     * @return the file path to the RandomMatrix.csv file.
     */
    
    public File getFile() {
        return this.rMatrixFile;
    }
    
    /**
     * 
     * @return width of the random matrix 
     */

    public int getWidth() {
        return this.rMatrix[1].length;
    }
    
    /**
     * 
     * @return height of the random matrix
     */

    public int getHeight() {
        return this.rMatrix.length;
    }
}

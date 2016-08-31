package tilab.facerecogniser.projection;

import java.io.IOException;
import java.util.Random;
import tilab.facerecogniser.filereading.CSVReader;

/*
This class contains information about the random matrix that is used in random 
projections. Each element of the RMatrix corresponds to a random variable from 
normal distribution.
@param int[][] RMatrix  the random matrix that is used to project faces to the 
                        lower dimension
 */
public final class RandomMatrix {

    double[][] rMatrix = new double[500][10340];
    CSVReader writer = new CSVReader();
    String filepath;

    public RandomMatrix(String filepath) throws IOException {
        this.filepath = filepath + "/RandomMatrix.csv";
        initializeRMatrix();
    }

    /*
    This method checks if there's already an existing random matrix. This speeds 
    up the recognition process significantly: the user does not need to create a 
    new random matrix per every recognition.
     */
    public void getSavedRMatrix() {
        
    }

    /*
    This method initializes the random matrix that is used throughout the 
    execution. In the final version this matrix will be created only once and 
    then saved for later use. The random matrix will be k*10340 where k << 10340 
    (meaning that k is significantly smaller than 10340).
     */
    public void initializeRMatrix() throws IOException {
        
        if (writer.doesFileExist(filepath)== false) {
            Random r = new Random();

            for (int i = 0; i < rMatrix.length; i++) {
                for (int j = 0; j < rMatrix[i].length; j++) {
                    rMatrix[i][j] = r.nextGaussian();
                }
            }

            writer.save(filepath, rMatrix);
        } else {
            this.rMatrix = writer.load(filepath, rMatrix.length, rMatrix[0].length);
        }
    }

    /*
    This method casts the matrix multiplication -operation on the matrixes it is 
    given.
    @param int[] A     vector in C = RMatrix * A
    @return            solution
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

    public double[][] getRMatrix() {
        return this.rMatrix;
    }
    
    public void setFilePath(String filepath) throws IOException {
        this.filepath = filepath + "\\RandomMatrix.csv";
        initializeRMatrix();
    }
    
    public String getFilePath() {
        return this.filepath;
    }

    public int getWidth() {
        return this.rMatrix[1].length;
    }

    public int getHeight() {
        return this.rMatrix.length;
    }
}

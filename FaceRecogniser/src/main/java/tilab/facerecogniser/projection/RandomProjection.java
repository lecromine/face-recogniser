/*
This class projects the matrixes to desired lower dimension R^k. Random matrix 
and the face matrix/vector are given to the class from the calling class.
 */
package tilab.facerecogniser.projection;

import java.io.IOException;
import java.util.Arrays;
import tilab.facerecogniser.filereading.CSVReader;

public class RandomProjection {

    CSVReader csvReader = new CSVReader();
    double[][] projectedFaceMat = new double[0][0];
    String filepath;

    public RandomProjection(String filepath) {
        this.filepath = filepath + "/ProjectedFaceMatrix.csv";

    }

    /**
     * This method binds together the face vector and face matrix where last
     * mentioned is a collection of already projected face vectors.
     *
     * @param projectedFaceVec the face vector that has been projected to R^k.
     */
    public void bindTogether(double[] projectedFaceVec) {

        int index = projectedFaceMat.length;

        projectedFaceMat = Arrays.copyOf(projectedFaceMat, projectedFaceMat.length + 1);
        projectedFaceMat[index] = projectedFaceVec;

    }

    /**
     * Saves the projected face matrix to a .csv file.
     *
     * @throws IOException fails if PrintWriter fails.
     */
    public void saveProjectedFaceMat() throws IOException {
        csvReader.save(filepath, projectedFaceMat);
    }

    /**
     * This method projects the face vector to lower dimension R^k; in this case
     * k = 500. This is done by using the operation called matrix multiplication
     * on random matrix RMatrix and face vector faceVec.
     *
     * @param RMatrix random matrix
     * @param faceVec a corresponding vector for a face
     * @return
     */
    public double[] randomProjection(RandomMatrix RMatrix, int[] faceVec) {

        double[] projectedFaceVec = new double[RMatrix.getHeight()];

        projectedFaceVec = RMatrix.multiplicator(faceVec);

        return projectedFaceVec;
    }

    /**
     * A matrix that contains all the projected face vectors from the library
     *
     * @return projected face matrix
     */
    public double[][] getProjectedFaceMat() {
        return this.projectedFaceMat;
    }

    /**
     * This method reads the ProjectedFaceMatrix.csv -file if it exists and
     * saves it in to a 2D double array.
     *
     * @throws IOException if PrintWriter fails
     */
    public void loadProjectedFaceMat() throws IOException {
        if (!filepath.equals("")) {
            this.projectedFaceMat = csvReader.load(filepath, 0, 500);
        } else {
            System.out.println("Save folder not initialized. Try again.");
        }
    }

    /**
     * File path to ProjectedFaceMatrix.csv
     *
     * @return filepath
     */
    public String getFilepath() {
        return this.filepath;
    }

    /**
     * Changes the matrix file path
     *
     * @param filepath the path to the ProjectedFaceMatrix.csv file
     */
    public void setFilePath(String filepath) {
        this.filepath = filepath;
    }

}

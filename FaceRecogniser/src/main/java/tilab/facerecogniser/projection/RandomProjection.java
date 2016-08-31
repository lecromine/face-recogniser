/*
This class projects the matrixes to desired lower dimension R^k. Random matrix 
and the face matrix/vector are given to the class from the calling class.
 */
package tilab.facerecogniser.projection;

import java.io.IOException;
import java.util.Arrays;
import tilab.facerecogniser.filereading.CSVReader;
import tilab.facerecogniser.filereading.PGMReader;

public class RandomProjection {

    CSVReader csvReader = new CSVReader();
    double[][] projectedFaceMat = new double[0][0];
    String filepath;

    public RandomProjection(String filepath) {
        this.filepath = filepath + "\\ProjectedFaceMatrix.csv";
        
    }


    /*
    This method binds together the face vector and face matrix where last 
    mentioned is a collection of already projected face vectors.
    @param faceMat      collection of projected face vectors
    @param projectedFaceVec the face vector that has been projected to R^k.
     */
    public void bindTogether(double[] projectedFaceVec) {

        int index = projectedFaceMat.length;

        projectedFaceMat = Arrays.copyOf(projectedFaceMat, projectedFaceMat.length + 1);
        projectedFaceMat[index] = projectedFaceVec;

    }

    public void saveProjectedFaceMat() throws IOException {
        csvReader.save(filepath, projectedFaceMat);
    }

    /*
    This method projects the face vector to lower dimension R^k; in this case 
    k = 500. This is done by using the operation called matrix multiplication on 
    random matrix RMatrix and face vector faceVec.
    @param faceVec              face vector
    @return projectedFaceVec    a face matrix projection on the lower dimension k.
     */
    public double[] randomProjection(RandomMatrix RMatrix, int[] faceVec) {

        double[] projectedFaceVec = new double[RMatrix.getHeight()];

        projectedFaceVec = RMatrix.multiplicator(faceVec);

        return projectedFaceVec;
    }

    public double[][] getProjectedFaceMat() {
        return this.projectedFaceMat;
    }

    public void loadProjectedFaceMat() throws IOException {
        if (!filepath.equals("")) {
            this.projectedFaceMat = csvReader.load(filepath, 0, 500);
        } else {
            System.out.println("Save folder not initialized. Try again.");
        }
    }

    public void setFilepath(String filepath) throws IOException {

        loadProjectedFaceMat();
    }

    public String getFilepath() {
        return this.filepath;
    }

}

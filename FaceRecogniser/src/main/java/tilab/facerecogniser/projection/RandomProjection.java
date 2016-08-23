/*
This class projects the matrixes to desired lower dimension R^k. Random matrix 
and the face matrix/vector are given to the class from the calling class.
 */
package tilab.facerecogniser.projection;

import java.io.IOException;
import java.util.Arrays;
import tilab.facerecogniser.filereading.ArrayFileWriter;

public class RandomProjection {

    ArrayFileWriter writer = new ArrayFileWriter();
    double[][] projectedFaceMat = new double[0][0];
    String projectedFaceMatrixFilePath = "C:\\Users\\Lecromine\\Documents\\savedfiles\\ProjectedFaceMatrix.csv";

    public RandomProjection() {

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
        writer.save(projectedFaceMatrixFilePath, projectedFaceMat);
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
        this.projectedFaceMat = writer.load(projectedFaceMatrixFilePath, 0, 500);
    }
    
    public String getProjectedFaceMatrixFilePath() {
        return this.projectedFaceMatrixFilePath;
    }

}
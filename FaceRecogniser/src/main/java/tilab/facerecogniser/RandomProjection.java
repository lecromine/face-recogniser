/*
This class projects the matrixes to desired lower dimension R^k. Random matrix and 
the face matrix/vector are given to the class from the calling class.
*/

package tilab.facerecogniser;

public class RandomProjection {
    
    public RandomProjection() {
        
    }
    
    /*
    This method projects the face matrix to lower dimension R^k; in this case k = 500.
    This is done by using the operation called matrix multiplication on random matrix RMatrix
    and face matrix faceMat.
    @param faceMat  collection of all the face vectors
    @return projectedFaceMat    a face matrix projection on the lower dimension k.
    */
    
    public double[][] randomProjection(RandomMatrix RMatrix, int[][] faceMat) {
        
        double[][] projectedFaceMat = new double[RMatrix.getHeight()][faceMat.length];
        
        
        return projectedFaceMat;
    }
    
    /*
    This method works the same as the previous one, but with vectors.
    */
    
    public double[] randomProjection(RandomMatrix RMatrix, int[] faceVec) {
        
        double[] projectedFaceVec = new double[RMatrix.getHeight()];
        
        projectedFaceVec = RMatrix.multiplicator(faceVec);
        
        return projectedFaceVec;
    }
    
}

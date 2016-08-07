
package tilab.facerecogniser;

/*
This class contains information about the random matrix that is used in random projections.
Each element of the RMatrix corresponds to a random variable from normal distribution.
@param int[][] RMatrix  the random matrix that is used to project faces to the lower dimension
*/

public class RandomMatrix {
    int[][] RMatrix;
    
    public RandomMatrix() {
        
    }
    
    /*
    This method initializes the random matrix that is used throughout the execution. In the final version
    this matrix will be created only once and then saved for later use.
    */
    
    public void initializeRMatrix() {
        
    }
    
}

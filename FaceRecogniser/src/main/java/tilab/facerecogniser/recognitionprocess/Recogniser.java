/*
This class is called by the main class and it binds together all the operations that are distributed to other classes.
 */
package tilab.facerecogniser.recognitionprocess;

import tilab.facerecogniser.filereading.Reader;
import tilab.facerecogniser.projection.RandomProjection;
import tilab.facerecogniser.projection.RandomMatrix;
import java.io.IOException;

public class Recogniser {

    Reader reader = new Reader();
    RandomProjection RP = new RandomProjection();
    RandomMatrix rMatrix = new RandomMatrix();

    public Recogniser() throws IOException {
        rMatrix.initializeRMatrix();
    }
    
    /*
    This method finds the closest match for the uploaded face. It resolves the shortest Euclidean distance between
    the face and the faces in the database. 
    
    */
    
    public static double[][] recognise(double[][] recognisable) {
        
        double[][] closestMatch = new double[0][0];
        
        return closestMatch;
    }

    

}

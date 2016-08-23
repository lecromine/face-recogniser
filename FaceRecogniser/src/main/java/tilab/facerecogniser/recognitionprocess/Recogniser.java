/*
This class is called by the main class and it binds together all the operations 
that are distributed to other classes.
 */
package tilab.facerecogniser.recognitionprocess;

import tilab.facerecogniser.filereading.FaceFileReader;
import tilab.facerecogniser.projection.RandomProjection;
import tilab.facerecogniser.projection.RandomMatrix;
import java.io.IOException;

public class Recogniser {

    FaceFileReader reader = new FaceFileReader();
    RandomProjection RP = new RandomProjection();

    public Recogniser() throws IOException {
        
    }
    
    /*
    This method finds the closest match for the uploaded face. It resolves the 
    shortest Euclidean distance between the face and the faces in the database. 
    
    */
    
    public static double[][] recognise(double[][] recognisable) {
        
        double[][] closestMatch = new double[0][0];
        
        return closestMatch;
    }

    

}

package tilab.facerecogniser;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        FaceList theList = new FaceList();

        Reader reader = new Reader();
        
        int[][] faceMat = new int[0][0];
        
        reader.readFiles(faceMat);
        
    }

}

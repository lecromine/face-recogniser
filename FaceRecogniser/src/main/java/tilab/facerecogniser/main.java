 package tilab.facerecogniser;

import tilab.facerecogniser.ui.MainFrame;
import tilab.facerecogniser.projection.RandomProjection;
import tilab.facerecogniser.projection.RandomMatrix;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {

//        Recogniser recogniser = new Recogniser();
//        FaceFileReader reader = new FaceFileReader();
        RandomProjection RP = new RandomProjection();
        RandomMatrix rMatrix = new RandomMatrix();
//        ArrayFileWriter writer = new ArrayFileWriter();
//        
        MainFrame mainFrame = new MainFrame(rMatrix, RP);
        
        mainFrame.setVisible(true);
                    
//        reader.addFaces(RP, rMatrix);
//        
//        File file = new File("C:\\Users\\Lecromine\\Documents\\savedfiles\\10.pgm");
//        
//        double[] recognise = RP.randomProjection(rMatrix, reader.readFile(file));
//        
//        ClosestMatch closestMatch = new ClosestMatch();
//        
//        System.out.println(closestMatch.shortestEuclideanDistance(RP.getProjectedFaceMat(), recognise));
//          
//        
//        

        // writer.save("C:\\Users\\Lecromine\\face-recogniser\\FaceRecogniser\\savedfiles\\projectedFaceMat.txt", RP.getProjectedFaceMat());
        
//        FaceList theList = new FaceList();
//
//        Reader reader = new Reader();
//        
//        int[][] faceMat = new int[0][0];
//        
//        reader.readATTFiles(faceMat);
//      
//        int[][] A = {{4, 3}, {2, 1}};
//        int[][] B = {{1, 2}, {1, -2}};
//
//        RandomMatrix rand = new RandomMatrix();
//        double[][] C = rand.multiplicator(A, B);
//
//        for (int i = 0; i < C.length; i++) {
//            for (int j = 0; j < C[0].length; j++) {
//                System.out.print(C[i][j] + ", ");
//            }
//            System.out.println("");
//        }
    }

}

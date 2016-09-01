package tilab.facerecogniser;

import java.io.File;
import tilab.facerecogniser.ui.MainFrame;
import tilab.facerecogniser.projection.RandomProjection;
import tilab.facerecogniser.projection.RandomMatrix;
import java.io.IOException;
import tilab.facerecogniser.filereading.CSVReader;
import tilab.facerecogniser.filereading.PGMReader;
import tilab.facerecogniser.projection.ClosestMatch;

public class main {

    public static void main(String[] args) throws IOException {

        String filepath = "src/main/resources";

        File file = new File(filepath);
        
        RandomProjection RP = new RandomProjection(file.getAbsolutePath());
        RandomMatrix rMatrix = new RandomMatrix(file.getAbsolutePath());
        CSVReader reader = new CSVReader();
        PGMReader pgmReader = new PGMReader(file.getAbsolutePath());

        pgmReader.initializeDatabase(RP, rMatrix);
//
//        File fileRecognise = new File(file.getAbsolutePath() + "/facegallery/s30/2.pgm");
//
//        int[] facevec = pgmReader.readFile(fileRecognise);
//        int[][] facemat = new int[92][112];
//        
//        for (int i = 0; i < 92; i++) {
//            for (int j = 0; j < 112; j++) {
//                facemat[i][j] = facevec[i+j];
//            }
//        }
//        
//        DisplayPGM disp = new DisplayPGM();
//        
//        disp.show(facemat);
////        
//        double[] recognise = RP.randomProjection(rMatrix, pgmReader.readFile(fileRecognise));
////        
//        ClosestMatch closestMatch = new ClosestMatch();
////        
//        System.out.println(closestMatch.shortestEuclideanDistance(
//                RP.getProjectedFaceMat(), recognise));

        MainFrame mainFrame = new MainFrame(rMatrix, RP, pgmReader,file.getAbsolutePath() + "\\facegallery\\");
        mainFrame.setVisible(true);
//         writer.save("C:\\Users\\Lecromine\\face-recogniser\\FaceRecogniser\\savedfiles\\projectedFaceMat.txt", RP.getProjectedFaceMat());
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

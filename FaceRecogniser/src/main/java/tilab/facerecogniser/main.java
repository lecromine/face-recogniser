package tilab.facerecogniser;

import java.io.File;
import tilab.facerecogniser.ui.MainFrame;
import tilab.facerecogniser.projection.RandomProjection;
import tilab.facerecogniser.projection.RandomMatrix;
import java.io.IOException;
import tilab.facerecogniser.filereading.CSVReader;
import tilab.facerecogniser.filereading.PGMReader;

public class main {

    public static void main(String[] args) throws IOException {
        
        /* Create/load matrices that are used in the recognition process. */

        String filepath = "src/main/resources";

        File file = new File(filepath);
        
        RandomProjection randomProjection = new RandomProjection(
                new File(file.getAbsolutePath() + "/ProjectedFaceMatrix.csv"));
        
        RandomMatrix rMatrix = new RandomMatrix(
                new File(file.getAbsolutePath() + "/RandomMatrix.csv"));
        
        CSVReader reader = new CSVReader();
        
        PGMReader pgmReader = new PGMReader(randomProjection, rMatrix, new File(file.getAbsolutePath()));
        
        System.out.println(randomProjection.getFile().getAbsolutePath()+ " " 
                + rMatrix.getFile().getAbsolutePath());
        
        /* Open UI. */
        
        

        MainFrame mainFrame = new MainFrame(rMatrix, randomProjection, 
                pgmReader, file.getAbsolutePath() + "/facegallery/");
        mainFrame.setVisible(true);

    }

}

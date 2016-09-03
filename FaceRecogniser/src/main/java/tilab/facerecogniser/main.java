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

        String filepath = "src/main/resources";

        File file = new File(filepath);
        
        RandomProjection RP = new RandomProjection(file.getAbsolutePath());
        RandomMatrix rMatrix = new RandomMatrix(file.getAbsolutePath());
        CSVReader reader = new CSVReader();
        PGMReader pgmReader = new PGMReader(file.getAbsolutePath());

        pgmReader.initializeDatabase(RP, rMatrix);
        System.out.println(RP.getFilepath() + " " + rMatrix.getFilePath());

        MainFrame mainFrame = new MainFrame(rMatrix, RP, pgmReader,file.getAbsolutePath() + "/facegallery/");
        mainFrame.setVisible(true);

    }

}

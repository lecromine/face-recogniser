package tilab.facerecogniser.filereading;

import tilab.facerecogniser.projection.RandomProjection;
import tilab.facerecogniser.projection.RandomMatrix;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class PGMReader {

    CSVReader csvReader = new CSVReader();
    String[] pathsToFaces = new String[400];
    String filepath;

    public PGMReader(String filepath) {
        this.filepath = filepath;
    }

    /**
     * This method adds new faces to the database. All the uploaded faces are
     * listed in the parameter projectedFaceMat in the RandomProjection class.
     * These faces are projected to R^k dimension and that's why it needs the
     * RandomProjection class as a parameter.
     *
     * @param RP This is used to project the new face to the R^k subspace.
     * @param rMatrix Random matrix defines the projected face vector.
     * @throws java.io.IOException if PrintWriter fails
     */
    public void initializeDatabase(RandomProjection RP, RandomMatrix rMatrix) throws IOException {

        if (!csvReader.doesFileExist(RP.getFilepath())) {

            int[][] faceMat = new int[0][0];

            for (int[] faceVec : readATTFiles(faceMat)) {
                double[] projectedFaceVec = RP.randomProjection(rMatrix, faceVec);

                RP.bindTogether(projectedFaceVec);
            }

            RP.saveProjectedFaceMat();

        } else {
            RP.loadProjectedFaceMat();
            this.pathsToFaces = csvReader.load(filepath + "/PathsToFaces.csv", pathsToFaces.length);
        }

    }

    /**
     * This method initialises the face library for testing purposes.
     * We use the ATT face database.
     * @param faceMat empty matrix where ATT images are added to.
     * @return the face matrix with all the ATT files in it.
     * @throws java.io.IOException if PrintWriter fails
     */
    public int[][] readATTFiles(int[][] faceMat) throws IOException {

        int index = 0;

        for (int i = 1; i <= 40; i++) {
            File dir = new File(filepath + "/facegallery/s" + i + "/");
            for (File file : dir.listFiles()) {
                faceMat = Arrays.copyOf(faceMat, faceMat.length + 1);
                faceMat[index] = readFile(file);
                pathsToFaces[index] = file.getAbsolutePath();
                index++;
            }
        }

        csvReader.save(filepath + "/PathsToFaces.csv", pathsToFaces);

        return faceMat;
    }

    /**
     * This method reads the file .pgm from the location given to it.
     *
     * @param file the file that needs to be read
     * @return pgm file as an int array
     */
    public int[] readFile(File file) {

        int[] faceVec = new int[10340];

        FileInputStream f;
        try {
            f = new FileInputStream(file);
            
            try (BufferedReader br = new BufferedReader(new InputStreamReader(f))) {
                // format -variable has either value P5 or P2: in my implementation we only consider the P5 case.
                String format = br.readLine();
                String line = br.readLine();
                
                Scanner s = new Scanner(line);
                int width = s.nextInt();
                if (!s.hasNext()) {
                    line = br.readLine();
                    s = new Scanner(line);
                    int height = s.nextInt();
                } else {
                    int height = s.nextInt();
                }
                s.close();
                
                line = br.readLine();
                s = new Scanner(line);
                
                s.close();
            }
            f.close();

            int pixelValue = 0;
            int counter = 0;
            f = new FileInputStream(file);
            try (DataInputStream dis = new DataInputStream(f)) {
                dis.readLine();
                dis.readLine();
                dis.readLine();
                while ((pixelValue = dis.read()) >= 0) {
                    faceVec[counter] = pixelValue;
                    counter++;
                }
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return faceVec;
    }
 
    public String[] getPathsToFiles() {
        return this.pathsToFaces;
    }

}

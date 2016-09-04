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
    File filepath;

    public PGMReader(RandomProjection randomProjection, RandomMatrix rMatrix, File file) throws IOException {
        this.filepath = file;
        initializeDatabase(randomProjection, rMatrix);
    }

    /**
     * This method adds new faces to the database. All the uploaded faces are
     * listed in the 2D double array projectedFaceMat in the RandomProjection class.
     * These faces are projected to R^k dimension and that's why it needs the
     * RandomProjection class as a parameter.
     *
     * @param randomProjection This is used to project the new face to the R^k subspace.
     * @param rMatrix Random matrix defines the projected face vector.
     * @throws java.io.IOException if PrintWriter fails
     */
    public void initializeDatabase(RandomProjection randomProjection, RandomMatrix rMatrix) throws IOException {

        if (!randomProjection.getFile().exists()) {

            int[][] faceMat = new int[0][0];

            for (int[] faceVec : readATTFiles(faceMat)) {
                double[] projectedFaceVec = randomProjection.randomProjection(rMatrix, faceVec);

                randomProjection.bindTogether(projectedFaceVec);
            }

            randomProjection.saveProjectedFaceMat();

        } else {
            randomProjection.loadProjectedFaceMat();
            this.pathsToFaces = csvReader.load(new File(filepath.getAbsolutePath() + "/PathsToFaces.csv"), pathsToFaces.length);
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
            File dir = new File(filepath.getAbsolutePath() + "/facegallery/s" + i + "/");
            for (File file : dir.listFiles()) {
                faceMat = Arrays.copyOf(faceMat, faceMat.length + 1);
                faceMat[index] = readFile(file);
                pathsToFaces[index] = file.getAbsolutePath();
                index++;
            }
        }

        csvReader.save(new File(filepath.getAbsolutePath() + "/PathsToFaces.csv"), 
                pathsToFaces);

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
                /* Format -variable has either value P5 or P2: in my 
                implementation we only consider the P5 case. */
                String format = br.readLine();
                /* We read the line that contains width and height to the line-variable */
                String line = br.readLine();
                /* Scanner divides the line-variable to width and height. In some cases
                height is not on the same row as width in the .pgm files so we need to
                take that into account: hence the if-function. */
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
                
                /* We consume the remaining lines. */
                
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
                
                /* The following while-loop reads the color values 0-256 to
                pixelValue-variable and then copies them to the faceVec. */
                
                while ((pixelValue = dis.read()) >= 0) {
                    faceVec[counter] = pixelValue;
                    counter++;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Compile error");
        }
        return faceVec;
    }
    
    /**
     * This method returns all the paths to the .pgm files in the
     * facegallery directory.
     * @return file paths as a string array
     */
 
    public String[] getPathsToFiles() {
        return this.pathsToFaces;
    }

}

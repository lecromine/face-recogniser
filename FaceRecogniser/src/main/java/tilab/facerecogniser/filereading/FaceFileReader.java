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

public class FaceFileReader {

    ArrayFileWriter writer = new ArrayFileWriter();

    public FaceFileReader() {

    }

    /**
     * This method adds new faces to the database. All the uploaded faces are 
     * listed in the parameter projectedFaceMat in the RandomProjection class.
     * These faces are projected to R^k dimension and that's why it needs the
     * RandomProjection class as a parameter.
     * @param RP        This is used to project the new face to the R^k subspace.
     * @param rMatrix   Random matrix defines the projected face vector.
     */
    public void addFaces(RandomProjection RP, RandomMatrix rMatrix) throws IOException {

        double[] projectedFaceVec = new double[0];

        if (!writer.doesFileExist(RP.getProjectedFaceMatrixFilePath())) {
            
            int[][] faceMat = new int[0][0];

            for (int[] faceVec : readATTFiles(faceMat)) {
                projectedFaceVec = RP.randomProjection(rMatrix, faceVec);

                RP.bindTogether(projectedFaceVec);
            }
            
            RP.saveProjectedFaceMat();

        } else {
            RP.loadProjectedFaceMat();
        }

    }

    /**
     * With this method we can initilaize the face matrix for testing purposes.
     * This is done by uploading AT&T face gallery for further examination.
     * http://www.cl.cam.ac.uk/research/dtg/attarchive/facedatabase.html
     * 
     * @param faceMat   empty matrix where AT&T images are added to.
     * @return the face matrix with all the AT&T files in it
     */
    public int[][] readATTFiles(int[][] faceMat) throws IOException {

        int index = 0;

        for (int i = 1; i <= 40; i++) {
            String path = "C:/Users/Lecromine/Documents/facegallery/s" + i;
            File dir = new File(path);
            for (File file : dir.listFiles()) {
                faceMat = Arrays.copyOf(faceMat, faceMat.length + 1);
                faceMat[index] = readFile(file);
                index++;
            }
        }

        return faceMat;
    }

    /**
     * This method reads the file .pgm from the location given to it.
     * @param file  the file that needs to be read
     * @return
     */
    public int[] readFile(File file) throws FileNotFoundException, IOException {

        int[] faceVec = new int[10340];

        FileInputStream f;
        try {
            f = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(f));
            // format -variable has either value P5 or P2: in my implementation we only consider the P5 case.
            String format = br.readLine();  
            String line = br.readLine();

            Scanner s = new Scanner(line);
            int width = s.nextInt();
            int height = s.nextInt();
            s.close();

            line = br.readLine();
            s = new Scanner(line);

            s.close();
            br.close();
            f.close();

            int pixelValue = 0;
            int counter = 0;
            f = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(f);

            dis.readLine();
            dis.readLine();
            dis.readLine();
            while ((pixelValue = dis.read()) >= 0) {
                faceVec[counter] = pixelValue;
                counter++;
            }

            dis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return faceVec;
    }

}

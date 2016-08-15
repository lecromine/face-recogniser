package tilab.facerecogniser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Reader {

    public Reader() {

    }

    /*
     * This method only exists for debugging purposes. It wont be in the final version.
     * @param int[][] faceMat   the matrix of the face vectors that the new face needs to be binded with
     * @return int[][] faceMat  new matrix with the new face
     */
    public int[][] readATTFiles(int[][] faceMat) throws IOException {

        int index = 0;
        

        for (int i = 1; i < 41; i++) {
            String path = "C:/Users/Lecromine/face-recogniser/FaceRecogniser/facegallery/s" + i;
            File dir = new File(path);
            for (File file : dir.listFiles()) {
                faceMat = Arrays.copyOf(faceMat, faceMat.length + 1);
                faceMat[index] = readFile(file);
                index++;
            }
        }

//        System.out.println("width " + faceMat.length);
//        System.out.println("height " + faceMat[1].length);
//
//        int nonZeros = 0;
//
//        for (int j = 0; j < 41; j++) {
//            for (int i = 0; i < 50; i++) {
//                System.out.print(faceMat[j][i] + ", ");
//                if (faceMat[j][i] > 256) {
//                    nonZeros++;
//                }
//            }
//            System.out.println("");
//
//        }
//        
//        System.out.println("index " + index);
//
//        System.out.println(nonZeros);
        return faceMat;
    }

    /*
 
    This file reads the .pgm files from facegallery and copies the values to a vector.
    This operation is done only once at the beginning when running the program.
    @param File file        the file that needs to be converted to an 1D array
    @return int[] faceVec   vector representation of the .pgm file
    
     */
    public int[] readFile(File file) throws FileNotFoundException, IOException {

        int[] faceVec = new int[10340];

        FileInputStream f;
        try {
            f = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(f));
            String magic = br.readLine();
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

            int b = 0;
            int counter = 0;
            f = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(f);

            dis.readLine();
            dis.readLine();
            dis.readLine();
            while ((b = dis.read()) >= 0) {
                faceVec[counter] = b;
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

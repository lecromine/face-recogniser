package tilab.facerecogniser;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * This file reads the .pgm files from facegallery and copies the values to a vector.
 * This operation is done only once at the beginning when running the program.
 */
public class Reader {

    public Reader() {

    }

    public void readFiles() throws IOException {
        
        File dir = new File("C:/Users/Lecromine/face-recogniser/FaceRecogniser/facegallery/s1");

        byte[][] faceVec = readFile();
        
        
        System.out.println("width " + faceVec.length);
        System.out.println("height " + faceVec[1].length);
        
        int nonZeros = 0;
        
        for (int i = 0; i < faceVec[1].length; i++) {
            for (int j = 1000; j < 1050; j++) {
                System.out.print(faceVec[j][i] + ", ");

            }
            System.out.println("");

        }
        
        System.out.println(nonZeros);
    }

    /**
     * This method reads the .pgm face file.
     *
     * @param path The end of the file path of the face image.
     *
     */
    public byte[][] readFile() throws FileNotFoundException, IOException {

        //String imagePath = "C:/Users/Lecromine/face-recogniser/FaceRecogniser/facegallery/" + path;

        File dir = new File("C:/Users/Lecromine/face-recogniser/FaceRecogniser/facegallery/s1");
        
        int index = 0;
        
        byte[][] faceVec = new byte[10340][10];
        
        for (File file : dir.listFiles()) {
            try {
                FileReader faceImage = new FileReader(file);
                BufferedReader theReader = new BufferedReader(faceImage);

                String type = theReader.readLine();
                String heightAndWidth = theReader.readLine();
                while (heightAndWidth.startsWith("#")) {
                    heightAndWidth = theReader.readLine();
                }
                Scanner s = new Scanner(heightAndWidth);
                int width = s.nextInt();
                int height = s.nextInt();
                heightAndWidth = theReader.readLine();
                s = new Scanner(heightAndWidth);
                int maxVal = s.nextInt();
                

                int count = 0;
                int b = 0;
                try {
                    while (count < 10340) {
                        b = theReader.read();
                        if (b < 0) {
                            break;
                        }

                        if (b == '\n') {

                        } else if ("P5".equals(type)) {
                            faceVec[count][index] = (byte) ((b >> 8) & 0xFF);
                            count++;
                            faceVec[count][index] = (byte) (b & 0xFF);
                            count++;
                        } else {
                            faceVec[count][index] = (byte) b;
                            count++;
                        }
                        
                    }
                    index++;
                    
                } catch (EOFException eof) {
                    eof.printStackTrace(System.out);
                }

            } catch (Throwable t) {
                t.printStackTrace(System.err);
            }

        }
        return faceVec;
    }
}

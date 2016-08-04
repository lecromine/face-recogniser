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

        int[][] faceVec = readFileB();

        System.out.println("width " + faceVec.length);
        System.out.println("height " + faceVec[1].length);

        int nonZeros = 0;

        for (int i = 0; i < faceVec[1].length; i++) {
            for (int j = 0; j < 50; j++) {
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
    public int[][] readFileB() throws FileNotFoundException, IOException {

        File dir = new File("C:/Users/Lecromine/face-recogniser/FaceRecogniser/facegallery/s1");

        int[][] faceVec = new int[0][0];

        for (File file : dir.listFiles()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scan = new Scanner(fileInputStream);
// Discard the magic number
            scan.nextLine();
// Discard the comment line
            scan.nextLine();
// Read pic width, height and max value
            int picWidth = scan.nextInt();
            String picHeight = scan.nextLine();
            String maxvalue = scan.nextLine();

            fileInputStream.close();

            // Now parse the file as binary data
            fileInputStream = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fileInputStream);

            // look for 4 lines (i.e.: the header) and discard them
            int numnewlines = 4;
            while (numnewlines > 0) {
                char c;
                do {
                    c = (char) (dis.readUnsignedByte());
                } while (c != '\n');
                numnewlines--;
            }

            // read the image data
            faceVec = new int[112][picWidth];
            for (int row = 0; row < 112; row++) {
                for (int col = 0; col < picWidth; col++) {
                    faceVec[row][col] = dis.readUnsignedByte();
                    System.out.print(faceVec[row][col] + " ");
                }
                System.out.println();
            }
        }

        return faceVec;
    }

    public byte[][] readFileA() throws FileNotFoundException, IOException {

        //String imagePath = "C:/Users/Lecromine/face-recogniser/FaceRecogniser/facegallery/" + path;
        File dir = new File("C:/Users/Lecromine/face-recogniser/FaceRecogniser/facegallery/s1");

        int index = 0;

        byte[][] faceVec = new byte[10340][10];

        for (File file : dir.listFiles()) {
            
                InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream("C:/Users/Lecromine/face-recogniser/FaceRecogniser/facegallery/s1/1.pgm");
                BufferedReader d = new BufferedReader(new InputStreamReader(f));
                String magic = d.readLine();    // first line contains P2 or P5
                String line = d.readLine();     // second line contains height and width
                while (line.startsWith("#")) {
                    line = d.readLine();
                }
                Scanner s = new Scanner(line);
                int width = s.nextInt();
                int height = s.nextInt();
                line = d.readLine();// third line contains maxVal
                s = new Scanner(line);
                int maxVal = s.nextInt();
                faceVec = new byte[height][width];

                int count = 0;
                int b = 0;
                try {
                    while (count < height * width) {
                        b = d.read();
                        if (b < 0) {
                            break;
                        }

                        if (b == '\n') { // do nothing if new line encountered
                        } //                  else if (b == '#') {
                        //                      d.readLine();
                        //                  } 
                        //                  else if (Character.isWhitespace(b)) { // do nothing if whitespace encountered
                        //                  } 
                        else if ("P5".equals(magic)) { // Binary format
                            faceVec[count / width][count % width] = (byte) ((b >> 8) & 0xFF);
                            count++;
                            faceVec[count / width][count % width] = (byte) (b & 0xFF);
                            count++;
                        } else {  // ASCII format
                            faceVec[count / width][count % width] = (byte) b;
                            count++;
                        }
                    }
                } catch (EOFException eof) {
                    eof.printStackTrace(System.out);
                }
                System.out.println("Height=" + height);
                System.out.println("Width=" + height);
                System.out.println("Required elements=" + (height * width));
                System.out.println("Obtained elements=" + count);
            

        }
        return faceVec;
    }
}

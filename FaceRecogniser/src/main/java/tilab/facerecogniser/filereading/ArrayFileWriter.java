package tilab.facerecogniser.filereading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is used to save and load matrices for further use.
 *
 * @author Lecromine
 */
public class ArrayFileWriter {

    public ArrayFileWriter() {

    }

    /**
     * This method saves the matrix to the given location. Matrices are saved in
     * .csv format.
     *
     * @param filepath path to the saving folder
     * @param matrix matrix that needs to be saved
     * @throws IOException
     */
    public void save(String filepath, double[][] matrix) throws IOException {

        if (filepath.equals("")) {
            System.out.println("Saving folder for temporary files not initialized");
        } else {

            File f = new File(filepath);

            PrintWriter pw = new PrintWriter(new File(filepath));
            StringBuilder sb = new StringBuilder();

            for (double[] vector : matrix) {
                for (double x : vector) {
                    sb.append(Double.toString(x));
                    sb.append(',');
                }
                sb.append('\n');
            }
            pw.write(sb.toString());
            pw.close();
        }

    }

    /*
    this method reads the .txt file and converts it back to double array.
    @param String filename  the path to .txt
    @param double[][] projectedFaceMat  empty double array in which the .txt file is written
    @return projectedFaceMat    successfully converted double array.
     */
    public double[][] load(String filename, int matrixRows, int matrixCols) throws FileNotFoundException, IOException {
        double[][] savedArray = new double[matrixRows][matrixCols];

        if (filename.equals("")) {
            System.out.println("Saving folder for temporary files not initialized");
        } else {

            Scanner scanIn = null;
            int rows = 0;
            String inputLine = "";

            try {

                scanIn = new Scanner(new BufferedReader(new FileReader(filename)));

                while (scanIn.hasNextLine()) {
                    inputLine = scanIn.nextLine();
                    String[] InArray = inputLine.split(",");

                    if (savedArray.length == rows) {
                        savedArray = Arrays.copyOf(savedArray, savedArray.length + 1);
                        savedArray[rows] = new double[InArray.length];
                    }

                    for (int x = 0; x < InArray.length; x++) {

                        savedArray[rows][x] = Double.parseDouble(InArray[x]);

                    }

                    rows++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return savedArray;
    }

    /*
    this method checks if the file is empty and returns true if so, otherwise false
    @param String filename  path to the file
    @return true/false
     */
    public boolean doesFileExist(String filename) throws FileNotFoundException, IOException {

        File f = new File(filename);

        return f.exists();

    }

}

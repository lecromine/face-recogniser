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
public class CSVReader {

    public CSVReader() {

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

    /**
     * This method does the same as the previous one but for 1D string arrays.
     *
     * @param filepath path to the save location
     * @param pathsToFaces string array containing the paths to faces in
     * ProjectedFaceMatrix
     * @throws IOException
     */
    public void save(String filepath, String[] pathsToFaces) throws IOException {

        if (filepath.equals("")) {
            System.out.println("Saving folder for temporary files not initialized");
        } else {

            File f = new File(filepath);

            PrintWriter pw = new PrintWriter(new File(filepath));
            StringBuilder sb = new StringBuilder();

            for (String x : pathsToFaces) {
                sb.append(x);
                sb.append(',');
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
                System.out.println("exception" + e);
            }
        }

        return savedArray;
    }

    /**
     * This method does the same as the previous one but for String arrays.
     *
     * @param filename path to the location
     * @param length length of the array
     * @return string array with paths to the faces
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String[] load(String filename, int length) throws FileNotFoundException, IOException {
        String[] savedArray = new String[length];

        if (filename.equals("")) {
            System.out.println("Saving folder for temporary files not initialized");
        } else {

            Scanner scanIn = null;
            String inputLine = "";

            try {

                scanIn = new Scanner(new BufferedReader(new FileReader(filename)));

                inputLine = scanIn.nextLine();
                savedArray = inputLine.split(",");

            } catch (Exception e) {
                System.out.println("exception" + e);
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

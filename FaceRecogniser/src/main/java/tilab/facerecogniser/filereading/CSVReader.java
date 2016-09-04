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
     * @param file path to the saving folder
     * @param matrix matrix that needs to be saved
     * @throws IOException fail if printwriter fails
     */
    public void save(File file, double[][] matrix) throws IOException {

        try (PrintWriter pw = new PrintWriter(file)) {
            StringBuilder sb = new StringBuilder();

            for (double[] vector : matrix) {
                for (double x : vector) {
                    sb.append(Double.toString(x));
                    sb.append(',');
                }
                sb.append('\n');
            }
            pw.write(sb.toString());

        }

    }

    /**
     * This method does the same as the previous one but for 1D string arrays.
     *
     * @param file path to the save location
     * @param pathsToFaces string array containing the paths to faces in
     * ProjectedFaceMatrix
     * @throws IOException fail if PrintWriter fails
     */
    public void save(File file, String[] pathsToFaces) throws IOException {

        if (!file.exists()) {
            try (PrintWriter pw = new PrintWriter(file)) {
                StringBuilder sb = new StringBuilder();

                for (String x : pathsToFaces) {
                    sb.append(x);
                    sb.append(',');
                }

                pw.write(sb.toString());
            }
        } else {
            System.out.println("Saving folder for temporary files not initialized");
        }

    }

    /**
     * this method reads the .csv file and converts it to a double array.
     *
     * @param file File we want to load
     * @param matrixRows amount of rows in the .csv file
     * @param matrixCols amount of columns in the .csv file
     * @return .csv file as a 2D double array
     */
    public double[][] load(File file, int matrixRows, int matrixCols) {
        double[][] savedArray = new double[matrixRows][matrixCols];

        if (!file.exists()) {
            System.out.println("Saving folder for temporary files not initialized");
        } else {

            int rows = 0;

            try {

                Scanner scanIn = new Scanner(new BufferedReader(new FileReader(file.getAbsolutePath())));

                while (scanIn.hasNextLine()) {
                    String inputLine = scanIn.nextLine();
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
            } catch (FileNotFoundException | NumberFormatException e) {
                System.out.println("exception" + e);
            }
        }

        return savedArray;
    }

    /**
     * This method does the same as the previous one but for String arrays.
     *
     * @param file path to the location
     * @param length length of the array
     * @return string array with paths to the faces
     */
    public String[] load(File file, int length) {
        String[] savedArray = new String[length];

        if (!file.exists()) {
            System.out.println("Saving folder for temporary files not initialized");
        } else {

            try {

                Scanner scanIn = new Scanner(new BufferedReader(new FileReader(file.getAbsolutePath())));

                String inputLine = scanIn.nextLine();
                savedArray = inputLine.split(",");

            } catch (Exception e) {
                System.out.println("exception" + e);
            }
        }

        return savedArray;
    }

    /**
     * this method checks if the file is empty and returns true if so, otherwise
     * false
     *
     * @param filepath path to the file
     * @return true if file exists, otherwise false
     */
    public boolean doesFileExist(String filepath) {

        File f = new File(filepath);

        return f.exists();

    }

}

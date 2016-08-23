package tilab.facerecogniser.filereading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ArrayFileWriter {

    public ArrayFileWriter() {

    }

    /*
    This method saves the face matrix to a .txt file for further use.
    @param String filename      path to the .txt file used for saving
    @param double[][] faceMat   projected face matrix
     */
    public static void save(String filename, double[][] faceMat) throws IOException {

        File f = new File(filename);
        
        PrintWriter pw = new PrintWriter(new File(filename));
        StringBuilder sb = new StringBuilder();
        
         for (double[] faceVec : faceMat) {
            for (double x : faceVec) {
                sb.append(Double.toString(x));
                sb.append(',');
            }
            sb.append('\n');
        }
        pw.write(sb.toString());
        pw.close();
        
        System.out.println("done!");
        
//        File f = new File(filename);
//
//        BufferedWriter outputWriter = null;
//        outputWriter = new BufferedWriter(new FileWriter(filename));
//
//        for (double[] faceVec : faceMat) {
//            for (double x : faceVec) {
//                outputWriter.write(Double.toString(x) + " ");
//                outputWriter.newLine();
//            }
//            
//        }
//        outputWriter.flush();
//        outputWriter.close();
    }

    /*
    this method reads the .txt file and converts it back to double array.
    @param String filename  the path to .txt
    @param double[][] projectedFaceMat  empty double array in which the .txt file is written
    @return projectedFaceMat    successfully converted double array.
     */
    public double[][] load(String filename, double[][] savedArray) throws FileNotFoundException, IOException {

        FileReader fileReader = new FileReader(filename);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line = null;

            int i = 0;
            int j = 0;

            while ((line = br.readLine()) != null) {

                if (j == savedArray[0].length) {
                    i++;
                    j = 0;
                } else {
                    savedArray[i][j] = Double.parseDouble(line);
                    j++;

                }
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

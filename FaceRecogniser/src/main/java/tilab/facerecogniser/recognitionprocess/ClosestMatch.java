/**
 * This class finds the closest face vector to the one we want to recognise.
 *
 */
package tilab.facerecogniser.recognitionprocess;

public class ClosestMatch {

    public ClosestMatch() {

    }

    /**
     * This method returns the vector in the projected face matrix which has the
     * shortest Euclidean distance compared to the face
     *
     * @param projectedFaceMat collection of face vectors
     * @param faceVecRecognisable uploaded face vector
     * @return distance between the two vectors
     */
    public double shortestEuclideanDistance(double[][] projectedFaceMat, double[] faceVecRecognisable) {

        double[] closestSoFar = new double[0];
        double minDistance = Double.MAX_VALUE;
        double distance = 0;
        int minRow = 0;
        int indexOfClosest = 0;

        int index = 0; // debugging
        int jndex = 0; // debugging

        try {
            for (int i = 0; i < projectedFaceMat.length; i++) {
                distance = 0;

                innerloop:
                for (int j = 0; j < projectedFaceMat[i].length; j++) {
                    index = i;
                    jndex = j;

                    double p = faceVecRecognisable[j];
                    double q = projectedFaceMat[i][j];
                    distance = distance + Math.pow(p - q, 2);
                    if (distance > minDistance) {
                        break;
                    }

                }

                if (distance < minDistance) {
                    closestSoFar = projectedFaceMat[i];
                    indexOfClosest = i;
                    minDistance = distance;

                }

            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("i " + index + " j " + jndex);
        }

        System.out.println("index of closest " + indexOfClosest);

        return Math.sqrt(distance);
    }

}

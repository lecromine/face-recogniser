/*
 * This class 
 */
package tilab.facerecogniser;


public class Face {

    int keyNumber;
    Face next;

    public Face() {
        keyNumber = 0;
        next = null;
    }

    public Face(int keyNumber) {
        this.keyNumber = keyNumber;
        this.next = next;
    }
    
    public void setNext(Face next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + keyNumber;
    }

    public Face getNext() {
        return next;
    }

}

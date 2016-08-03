/*
 * This class 
 */
package tilab.facerecogniser;


public class Node {

    int keyNumber;
    Node next;

    public Node() {
        keyNumber = 0;
        next = null;
    }

    public Node(int keyNumber) {
        this.keyNumber = keyNumber;
        this.next = next;
    }
    
    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "" + keyNumber;
    }

    public Node getNext() {
        return next;
    }

}

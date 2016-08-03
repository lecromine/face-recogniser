/*
 * In this class I will implement a graph (like linkedlist) where the face database can be uploaded.
 */
package tilab.facerecogniser;

public class List {

    private Node head;
    private int count;

    public List() {
        head = new Node();
        count = 0;
    }

    public void add(int keyNumber) {
        Node listTemp = new Node(keyNumber);
        Node listCurrent = head;

        while (listCurrent.getNext() != null) {
            listCurrent = listCurrent.getNext();
        }

        listCurrent.setNext(listTemp);
        count++;
    }

    /*
     * This method returns the value that corresponds to the index value.
     * @param   index   value that is used to find the node in the list
     * @return the node that corresponds to the index
     */
    public Node get(int index) {

        if (index <= 0) {
            return null;
        }
        Node listCurrent = null;
        if (head != null) {
            listCurrent = head.getNext();
            for (int i = 0; i < index; i++) {
                if (listCurrent.getNext() == null) {
                    return null;
                }

                listCurrent = listCurrent.getNext();
            }

        }
        return listCurrent;

    }

    /* 
     * Remove a node from the list.
     * @param   index   index of the parameter that is to be removed
     * @return  true/false  true if the node is removed successfully, otherwise false.
     */
    public boolean remove(int index) {

        if (index < 1 || index > getCount()) {
            return false;
        }

        Node listCurrent = head;
        if (head != null) {
            for (int i = 0; i < index; i++) {
                if (listCurrent.getNext() == null) {
                    return false;
                }

                listCurrent = listCurrent.getNext();
            }
            listCurrent.setNext(listCurrent.getNext().getNext());

            decCount();
            return true;

        }
        return false;
    }

    public String toString() {
        String output = "";

        if (head != null) {
            Node listCurrent = head.getNext();
            while (listCurrent != null) {
                output += "[" + listCurrent.toString() + "]";
                listCurrent = listCurrent.getNext();
            }

        }
        return output;
    }

    public int getCount() {
        return count;
    }

    public void incCount() {
        count++;
    }

    public void decCount() {
        count--;
    }
}

package tilab.facerecogniser;

public class main {

    public static void main(String[] args) {
        List theList = new List();

        // add more elements to LinkedList
        theList.add(1234);
        theList.add(5555);
        theList.add(741);
        theList.add(20202);
        theList.add(54421);

        /*
		 * Please note that primitive values can not be added into LinkedList directly. They must be converted to their
		 * corresponding wrapper class.
         */
        System.out.println("Print: crunchifyList: \t\t" + theList);
        System.out.println(".size(): \t\t\t\t" + theList.getCount());
        System.out.println(".get(3): \t\t\t\t" + theList.get(3) + " (get element at index:3 - list starts from 0)");
        System.out.println(".remove(2): \t\t\t\t" + theList.remove(2) + " (element removed)");
        System.out.println(".get(3): \t\t\t\t" + theList.get(3) + " (get element at index:3 - list starts from 0)");
        System.out.println(".size(): \t\t\t\t" + theList.getCount());
        System.out.println("Print again: crunchifyList: \t" + theList);
    }

}

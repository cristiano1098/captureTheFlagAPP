package Structures;

import Interfaces.OrderedListADT;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * Create an empty list using the default capacity
     */
    public ArrayOrderedList() {
        super();
    }

    /**
     * Create an empty list using the specified capacity
     */
    public ArrayOrderedList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void add(Comparable element) {
        if (size() == array.length) {
            expandCapacity();
        }

        int scan = 0;
        while (scan < rear && element.compareTo(array[scan]) > 0) {
            scan++;
        }

        for (int scan2 = rear; scan2 > scan; scan2--) {
            array[scan2] = array[scan2 - 1];
        }

        array[scan] = (T) element;
        rear++;
    }

    public void linearSearch(Comparable target) {
        int counter = 0;
        boolean found = false;
        for (int i = 0; i < size(); i++) {
            if (target.compareTo(array[i]) == 0) {
                found = true;
                System.out.println("Found !!");
                System.out.println("N iteracoes: " + counter + "\n");
                System.out.println(array[i].toString());

            } else {
                counter++;
            }
        }

        if (!found) {
            System.out.print("Car not found !!");
        }
    }

    public boolean binarySearch(Comparable target,int min, int max) {
        boolean found = false;
        int midpoint = (min + max) / 2; // determine the midpoint
        
        System.out.println("ITERACAO!");
        System.out.println(array[midpoint].toString());
        System.out.println("\n");
        
        if (target.compareTo(array[midpoint]) == 0) {
            found = true;
        } else if (target.compareTo(array[midpoint]) > 0) {
            found = binarySearch(target, midpoint + 1, max);
        } else if (midpoint + 1 <= max) {
            if (min <= midpoint - 1) {
                found = binarySearch(target, min, midpoint - 1);
            }
        }
  
        return found;
    }
}

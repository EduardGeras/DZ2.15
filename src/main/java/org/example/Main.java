package org.example;

import java.util.Arrays;

public class Main {
    private static final String[] otherList = new String[6];


    public static void main(String[] args) {
        IntegerList integerList = new IntegerListImpl();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        System.out.println("1. " + Arrays.toString(integerList.toArray()));

        integerList.add(4, 5);
        System.out.println("2. " + Arrays.toString(integerList.toArray()));

        integerList.set(2, 19);
        System.out.println("3. " + Arrays.toString(integerList.toArray()));

        integerList.remove(2);
        System.out.println("4. " + Arrays.toString(integerList.toArray()));

        integerList.toArray();
        System.out.println("5. " + Arrays.toString(integerList.toArray()));

        integerList.remove(1);
        System.out.println("6. " + Arrays.toString(integerList.toArray()));

        System.out.println("7. " + Arrays.toString(new boolean[]{integerList.contains(1)}));
        System.out.println("8. " + Arrays.toString(new boolean[]{integerList.contains(20)}));

        System.out.println("9. " + Arrays.toString(new int[]{integerList.indexOf(1)}));
        System.out.println("10. " + Arrays.toString(new int[]{integerList.indexOf(21)}));

        System.out.println("11. " + Arrays.toString(new int[]{integerList.lastIndexOf(2)}));
        System.out.println("12. " + Arrays.toString(new int[]{integerList.lastIndexOf(4)}));

        System.out.println("13. " + Arrays.toString(new Integer[]{integerList.get(1)}));

        System.out.println("14. " + Arrays.toString(new boolean[]{integerList.equals(otherList)}));

        System.out.println("15. " + Arrays.toString(new int[]{integerList.size()}));

        System.out.println("16. " + Arrays.toString(new boolean[]{integerList.isEmpty()}));

        integerList.clear();
        System.out.println("17. " + Arrays.toString(integerList.toArray()));
    }
}
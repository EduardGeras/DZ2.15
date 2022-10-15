package org.example;

import java.util.Random;

public class SortingImpl{

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        System.out.println("Время сортировки пузырьком: " + timeForBubbleSort() + " мс.");
        System.out.println("Время сортировки выбором: " + timeForSelectionSort() + " мс.");
        System.out.println("Время сортировки вставками: " + timeForInsertionSort() + " мс.");
    }

    public static double timeForBubbleSort() {

        int[] array = generateArray(100_000);
        long start = System.currentTimeMillis();
        sortBubble(array);
        long ind = System.currentTimeMillis() - start;
        return ind;
    }

    public static double timeForSelectionSort() {
        int[] array = generateArray(100_000);
        long start = System.currentTimeMillis();
        sortSelection(array);
        long ind = System.currentTimeMillis() - start;
        return ind;
    }


    public static double timeForInsertionSort() {
        int[] array = generateArray(100_000);
        long start = System.currentTimeMillis();
        sortInsertion(array);
        long ind = System.currentTimeMillis() - start;
        return ind;
    }


    public static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt();
        }
        return array;
    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }


    public static void swapElements(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }


    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swapElements(arr, i, min);
        }
    }


    public static void sortInsertion(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}

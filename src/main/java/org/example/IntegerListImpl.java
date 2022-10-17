package org.example;

import java.util.Arrays;
import java.util.Objects;

public class IntegerListImpl implements IntegerList{

    private Integer[] data;

    private int capacity;

    public IntegerListImpl() {
        data = new Integer[20];
    }

    public IntegerListImpl(int a) {
        data = new Integer[a];
    }

    @Override
    public Integer add(Integer item) {
        enlargeTheList();
        doNotAddNull(item);
        data[capacity] = item;
        capacity++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        enlargeTheList();
        doNotAddNull(item);
        indexError(index);
        // Копирует и сдвигает
        // Пример. а б в г д null null null ... null
        // а б в в г д null ... null
        System.arraycopy(data, index, data, index + 1, capacity - index);
        // Ставим ш вместо первой в
        // а б ш в г д null ... null
        data[index] = item;
        capacity++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        doNotAddNull(item);
        indexError(index);
        // Пишем вместо старого значения новое на тоже место
        return data[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        int removing = indexOf(item);
        return remove(removing);
        // Возвращаем remove(index) и работаем в следующем remove (public String remove (int index))
    }

    @Override
    public Integer remove(int index) {
        indexError(index);
        Integer removed = data[index];
        // Копирует и сдвигает
        // Пример. а б в г д null null ... null
        // Удаляем в, копируем и сдвигаем г д: а б г д д null null ... null
        System.arraycopy(data, index + 1, data, index, capacity - 1 - index);
        capacity--;
        // Ставим вместо второй д null
        // Получилось: а б г д null null null ... null
        data[capacity] = null;
        return removed;
    }

    @Override
    public boolean contains(Integer item) {
        doNotAddNull(item);
        // Создаём копию и сортируем
        Integer []arrayForSearch = toArray();
        sortInsertion(arrayForSearch);
        // и используем бинарный поиск
        return binarySearch(arrayForSearch, item);
    }

    @Override
    public int indexOf(Integer item) {
        doNotAddNull(item);
        int index = -1;
        for (int i = 0; i < capacity; i++) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        doNotAddNull(item);
        int index = -1;
        for (int i = capacity - 1; i >= 0; i--) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public Integer get(int index) {
        indexError(index);
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList, Object list) {
        if (Objects.isNull(otherList)) {
            throw new IllegalArgumentException("null не проверяем");
        }
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!data[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        // Если это условие выполняется, то возвращается true
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        capacity = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] result = new Integer[capacity];
        System.arraycopy(data, 0, result, 0, capacity);
        return result;
    }

    private void enlargeTheList() {
        if (capacity >= data.length) {
            grow();
        }
    }

    private void doNotAddNull(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Добавлять null нельзя");
        }
    }

    private void indexError(int index) {
        if (index < 0 || index > capacity) {
            throw new IllegalArgumentException("Неверный индекс");
        }
    }

    public static void sortInsertion(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static boolean binarySearch(Integer[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (element == arr[mid]) {
                return true;
            }
            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void grow() {
        data = Arrays.copyOf(data, capacity + capacity / 2);
    }
}

package org.example;

import java.util.Objects;

public class IntegerListImpl implements IntegerList{

    private static final int INITIAL_SIZE = 20;

    private final Integer[] data;

    private int capacity;

    public IntegerListImpl() {
        if (INITIAL_SIZE <= 0) {
            throw new IllegalArgumentException("Размер списка должен быть положительным");
        }
        data = new Integer[INITIAL_SIZE];
        capacity = 0;
    }

    @Override
    public Integer add(Integer item) {
        theListIsFull();
        doNotAddNull(item);
        data[capacity] = item;
        capacity++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        theListIsFull();
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
        // и проверяем наличие
        for (int i = 0; i < capacity; i++) {
            if (arrayForSearch[i].equals(item)) {
                return true;
            }
        }
        return false;
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

    private void theListIsFull() {
        if (capacity >= data.length) {
            throw new IllegalArgumentException("Список полон");
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

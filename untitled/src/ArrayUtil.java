import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Random;

public class ArrayUtil {

    // Двоичный поиск (только для отсортированных массивов)
    public static void binarySearch(int value, int[] sortedArray) {
        int iterCount = 0;
        int minBound = 0;
        int maxBound = sortedArray.length - 1;
        while(true) {
            int currentIndex = (maxBound + minBound) / 2;
            int currentValue = sortedArray[currentIndex];

            System.out.println("Iteration " + ++iterCount);

            if (value == currentValue) {
                System.out.println("Found index is " + currentIndex);
                break;
            }

            // Выходим за границы массива
            // Либо max - отрицательный,
            // либо min - становиться больше max.
            if (minBound > maxBound) {
                System.out.println("Value wasn't found");
                break;
            }

            // Всегда устанавливаем границы либо слева от текущего элемента,
            // либо справа.
            if (currentValue > value) {
                maxBound = currentIndex - 1;
            } else {
                minBound = currentIndex + 1;
            }
        }
    }

    // Сортировка пузырьком
    public static void bubbleSort(int[] array) {
        int iterCount = 0;
        for (int i = array.length - 1; i > 1 ; i--) {
            for (int j = 0; j < i; j++) {
                iterCount++;
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println("IterCount = " + iterCount);
    }


    // Сортировка методом выбора
    // Kаждый раз находим мин. элемент и помещаем его в начало.
    public static void selectionSort(int[] array) {
        int iterCount = 0;
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
                iterCount++;
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        System.out.println("IterCount = " + iterCount);
    }

    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > temp) {
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }



    public static void display(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println("\n");
    }

    public static int[] createSortedArray() {
        int[] a = new int[100];
        for (int i = 0; i < a.length * 2; i++) {
            if (i % 2 == 0) {
                a[i/2] = i;
            }
        }
        return a;
    }

    public static int[] createRandomArray() {
        int[] a = new int[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Random().nextInt(1000);
        }
        return a;
    }

}

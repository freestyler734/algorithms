import arraySort.*;

public class Main {

    public static void main(String[] args) {

        int[] array = {80, 45, 87, 98, 12, 54, 83, 96, 45, 26, 28, 6, 31, 7, 83, 43, 8, 50, 83, 80};


        ArraySort.countSort(array, 98);

        display(array);

        }

    public static void display(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

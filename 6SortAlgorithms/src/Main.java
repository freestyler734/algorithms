import arraySort.ArraySort;

public class Main {

    public static void main(String[] args) {

        double[] array = {10,9,-8,72,16,-5,4,3,12,0};
        display(array);

        ArraySort.bubbleSort(array);

        System.out.println("---------------------------");

        display(array);
    }

    public static void display(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

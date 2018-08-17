import ArrayTrees.ArrayBinaryHeap;
import ArrayTrees.ArrayBinaryTree;
import arraySort.ArraySort;

public class Main {

    public static void main(String[] args) {

        double[] array = {1,5,2,3,5,2,12,57,32,9};
        ArraySort.heapSort(array);

        display(array);

        }

    public static void display(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}

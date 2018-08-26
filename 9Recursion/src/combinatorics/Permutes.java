package combinatorics;

import java.util.LinkedList;
import java.util.List;

/**
 * Реализация простых комбинаторных алгоритмов,
 * таких как размещения (Важен порядок).
 */
public class Permutes {

    /**
     * Реализация алгоритма нахождения размещений с повторениями.
     * A(k, n) = n^k
     * @param list
     * @param result
     * @param k
     */
    private static void innerPermutesOf(List list, List result, String permute, int k) {

        for (int i = 0; i < list.size(); i++) {
            if (k > 0) {
                innerPermutesOf(list, result, permute + list.get(i) + ", ", k - 1);
            } else {
                result.add("(" + permute + list.get(i) + ")");
            }
        }
    }

    public static List permutesOf(List list, int k) {
        List result = new LinkedList();
        innerPermutesOf(list, result, "", k - 1);
        return result;
    }

    /**
     * Реализация алгоритма нахождения размещений без повторений.
     * A(k, n) = n! / (n - k)!
     * При k = n - получаем перестановку.
     * @param list
     * @param result
     * @param k
     */
    private static void innerUniquePermutesOf(List list, List result, String permute, int k, int index) {

        for (int i = index; i < list.size(); i++) {
            if (k > 0) {
                innerUniquePermutesOf(list, result, permute + list.get(i) + ", ", k - 1, index + 1);
            } else {
                result.add("(" + permute + list.get(i) + ")");
            }
        }
    }

    public static List uniquePermutesOf(List list, int k) {
        List result = new LinkedList();
        innerUniquePermutesOf(list, result, "", k - 1, 0);
        return result;
    }

    public static void displayList(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("Count: " + list.size());
    }
}

package combinatorics;

import java.util.LinkedList;
import java.util.List;

/**
 * Реализация простых комбинаторных алгоритмов,
 * таких как сочетания(Важны сами элементы, но не порядок).
 */
public class Combinations {

    /**
     * Реализация алгоритма сочетаний 3-х элементов из заданного множества с повторениями.
     * С(k, n) = (n + k - 1)!/k!(n - 1)!
     * @param list
     * @return
     */
    public static List combinationsOf3 (List list) {
        List<String> result = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                for (int k = j; k < list.size(); k++) {
                    result.add(String.format("{%s, %s, %s}", list.get(i), list.get(j), list.get(k)));
                }
            }
        }

        return result;
    }


    /**
     * Реализация алгоритма сочетаний 3-х элементов из заданного множества без повторений.
     * С(k, n) = n!/k!(n! - k!)
     * @param list
     * @return
     */
    public static List uniqueCombinationsOf3 (List list) {
        List<String> result = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                for (int k = j + 1; k < list.size(); k++) {
                    result.add(String.format("{%s, %s, %s}", list.get(i), list.get(j), list.get(k)));
                }
            }
        }

        return result;
    }


    /**
     * Реализация алгоритма сочетаний k элементов из заданного множества с повторениями.
     * С(k, n) = (n + k - 1)!/k!(n - 1)!
     * @param list
     * @return
     */
    private static void innerCombinationsOf(List list, List result, String combination, int k, int index) {
        for (int i = index; i < list.size(); i++) {
            if (k != 0) {
                innerCombinationsOf(list, result,  combination + list.get(i) + ", " , k - 1, i);
            } else {
                result.add("{" + combination + list.get(i) + "}");
            }

        }
    }

    public static List combinationsOf(List list, int k) {
        List result = new LinkedList();
        innerCombinationsOf(list, result, "", k - 1, 0);
        return result;
    }


    /**
     * Реализация алгоритма сочетаний k элементов из заданного множества с повторениями.
     * С(k, n) = n!/k!(n! - k!)
     * @param list
     * @return
     */
    private static void innerUniqueCombinationsOf(List list, List result, String combination, int k, int index) {
        for (int i = index; i < list.size(); i++) {
            if (k != 0) {
                innerUniqueCombinationsOf(list, result,  combination + list.get(i) + ", " , k - 1, i + 1);
            } else {
                result.add("{" + combination + list.get(i) + "}");
            }

        }
    }

    public static List uniqueCombinationsOf(List list, int k) {
        List result = new LinkedList();
        innerUniqueCombinationsOf(list, result, "", k - 1, 0);
        return result;
    }

    public static void displayList(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("Count: " + list.size());
    }

}

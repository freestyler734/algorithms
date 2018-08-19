/**
 * Алгоритмы поиска элементов в массивах.
 */
public class ArraySearchAlgorithms {

    /**
     * Возвращает индекс первого найденного элемента value.
     * Подходит как для отсортированных, так и неотсортированных структур.
     * -----------------------------
     * Сложность - O(N).
     * @param array
     * @param value
     * @return
     */
    public static int linearSearch(double[] array, double value) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) return i;
        }

        return -1;
    }

    /**
     * Возвращает индекс первого найденного элемента value.
     * Подходит только для отсортированных массивов.
     * -----------------------------
     * Сложность - O(log(N))
     * @param array
     * @param value
     * @return
     */
    public static int binarySearch(double[] array, double value) {
        int start = 0;
        int end = array.length - 1;

        // на каждом шаге делим массив на 2 части,
        // и если value > середины, то смотрим правую чать,
        // елси value < середины, то смотрим леуч часть,
        // иначе искомый элемент - середина.
        while (start <= end) {
            int middle = (end + start) / 2;

            if (value > array[middle]) start = middle + 1;
            else if (value < array[middle]) end = middle - 1;
            else return middle;
        }

        return -1;
    }

    /**
     * Возвращает индекс первого найденного элемента value.
     * Подходит только для отсортированных массивов.
     * Суть - если занчения массива распределены равномерно,
     * то можно предугадать часть нахождения элемента по значению.
     * -----------------------------
     * Сложность - O(log(log(N)))
     * @param array
     * @param value
     * @return
     */
    public static int interpolationSearch(double[] array, double value) {
        int start = 0;
        int end = array.length - 1;

        // Чтобы найти значение middle, к текущему значению start добавляется расстояние от start до end,
        // масштабируемое по ожидаемой доле расстояния от array[start] до array[end], где должен находиться value.
        while (start <= end) {
            int middle = (int) (start + (end - start) * (value - array[start]) / (array[end] - array[start]));

            // из-за погрешности расчета, middle может выйти за пределы массива.
            if (middle < start) middle = start;
            if (middle > end) middle = end;

            if (value > array[middle]) start = middle + 1;
            else if (value < array[middle]) end = middle - 1;
            else return middle;

        }

        return -1;
    }
}

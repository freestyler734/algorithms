package arraySort;

public class ArraySort {

    /**
     * Сортировка вставкой.
     * Выбираем элемент и вставляем его в нужное место
     * отсортированной части массива.
     * Для удобства, сразу смещаем элементы большие текущего
     * отсортированной части массива на ячейку вперед.
     * ---------------------------------------------
     * Сложность - O(N2).
     * @param array
     */
    public static void insertionSort(double[] array) {

        // проходим по всем элементам массива (начинаем с 1-го)
        for (int i = 1; i < array.length; i++) {

            double current = array[i]; // текущий элемент
            // перебираем все элементы отсортированной части массива
            for (int j = i - 1; j >= 0; j--) {

                double currentSorted = array[j]; // текущий элемент из отсортированной части массива.
                // смещаем элементы большие текущего на ячейку вперед,
                // иначе вставляем элемент в пустую ячейку.
                if (currentSorted > current) {
                    array[j + 1] = currentSorted;
                } else {
                    array[j + 1] = current; // j + 1, т.к. начинаем с i - 1.
                    break;
                }

                // когда находим мин. элемент просто смещаем все элементы без вставок,
                // поэтому дополнительно реализуем этот случай.
                if (j == 0) {
                    array[j] = current;
                }
            }
        }
    }

    /**
     * Сортировка вставкой.
     * Находим мин. элемент и вставляем его после последнего отсортированного элемента.
     * -------------------
     * Сложность - O(N2)
     * @param array
     */
    public static void selectionSort(double[] array) {

        // Проходим по всем элементам массива, кроме последнего.
        for (int i = 0; i < array.length - 1; i++) {

            int minIndex = i; // индекс текущего мин. элемента.
            double min = array[minIndex]; //текущий мин. элемент.
            // ищем текущий мин. элемент среди несортированной части массива.
            for (int j = i + 1; j < array.length; j++) {
                double current = array[j];
                if (current < min) {
                    min = current;
                    minIndex = j;
                }
            }

            // пересталяем текущий мин. элемент
            // и элемент после последнего отсортированного.
            array[minIndex] = array[i];
            array[i] = min;
        }

    }

    /**
     * Сортировка пузырьком.
     * Суть - на каждой итерации сравниваем пары элементов,
     * и если предыдущий больше текущего, то меняем их местами.
     * Пары элемнтов меняются местами, пока не находится максимальный,
     * тогда он просто смещается в конец. Также массив частично сортируется посредствам
     * смены элементов местами.
     * Т.к алгоритм каждый раз смещает наибольший элемент в начало,
     * то можно ограничить max достигаемый внутренним циклом элемент
     * значением последнего отсортированного элемента(смещенного в конечное положение).
     * -------------------------
     *  Сложность - O(N2)
     * @param array
     */
    public static void bubbleSort(double[] array) {
        boolean isSorted = false; // флаг, показывающий отсортирован ли массив

        int lastSortеd = array.length - 1; // индекс последнего отсортированного элемента(смещенного в конечное положение).

        // Пока массив не отсортирован, продолжаем сортировку.
        while (!isSorted) {

            isSorted = true; // предполагаем, что массив отсортирован

            // меняем местами "неправльные" пары элементов
            // доходим до последнего отсортированного элемента(смещенного в конечное положение).
            for (int i = 1; i <= lastSortеd; i++) {
                double previous = array[i - 1];
                double current = array[i];

                if (previous > current) {
                    array[i - 1] = current;
                    array[i] = previous;
                    isSorted = false; // если предыдущий элемент больше текущего, то массив еще не отсортирован
                }
            }
            lastSortеd--;
        }
    }

    ////////////////////////////////////////////////////
    ///////////Пирамидальная сортировка/////////////////
    ////////////////////////////////////////////////////

    public static void heapSort(double[] array) {
        makeHeap(array);
        for (int i = 0; i < array.length; i++) {
            array[array.length - 1 - i] = removeTop(array, array.length - i);
        }
    }

    /**
     * Преращает маасив в кучу.
     * Переставляет элементы массива таким образом, чтобы массив соответствовал правилам построения бинарной кучи.
     * Для каждого элемента: меняем местами текущий элемент и его потенциального родителя, пока нейдется больший родитель.
     * @param array
     */
    private static void makeHeap(double[] array) {
        // проходим массив, начиная с конца
        for (int i = array.length - 1; i >= 0; i--) {

            int currentIndex = i;
            int parentIndex = (i - 1) / 2;
            // меняем местами текущий элемент и потенциального родителя,
            // пока невстретим большего родителя.
            while (parentIndex >= 0) {

                if (array[currentIndex] <= array[parentIndex]) {
                    break;
                }

                double temp = array[currentIndex];
                array[currentIndex] = array[parentIndex];
                array[parentIndex] = temp;

                currentIndex = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            }
        }
    }

    public static double removeTop(double[] array, int size) {

        // заменяем первый элемент последним, а последний зануляем
        double removed = array[0];
        array[0] = array[size - 1];
        array[size - 1] = 0;

        int currentIndex = 0; // текущий индекс нового корня
        while (true) {
            int currentLeftChild = 2 * currentIndex + 1;
            int currentRightChild = 2 * currentIndex + 2;

            // вышли за пределы массива
            if (currentLeftChild > size - 1) currentLeftChild = currentIndex;
            if (currentRightChild > size - 1) currentRightChild = currentIndex;

            // потомки меньше родитея (куча удовлетворяет условию)=> выходим из цикла
            if (array[currentIndex] >= array[currentLeftChild]
                    && array[currentIndex] >= array[currentRightChild]) {
                break;
            }

            double temp = array[currentIndex];
            // меняем текщий элемент с большим потомком местами, и сохраняем его индекс
            if (array[currentLeftChild] > array[currentRightChild]) {
                array[currentIndex] = array[currentLeftChild];
                array[currentLeftChild] = temp;
                currentIndex = currentLeftChild;
            } else {
                array[currentIndex] = array[currentRightChild];
                array[currentRightChild] = temp;
                currentIndex = currentRightChild;
            }
        }

        // возвращаем значение удаленного корня.
        return removed;
    }
}

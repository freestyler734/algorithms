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

    /**
     * Пирамидальная сортировка.
     * Превращаем массив в кучу
     * Удаляем больший элемент кучи, нормализуем кучу
     * и переносим его в конец массива
     * @param array
     */
    public static void heapSort(double[] array) {
        makeHeap(array);
        for (int i = 0; i < array.length; i++) {
            array[array.length - 1 - i] = removeTop(array, array.length - i);
        }
    }

    /**
     * Преращает маасив в кучу.
     * Переставляет элементы массива таким образом, чтобы массив соответствовал правилам построения бинарной кучи.
     * Для каждого элемента: меняем местами текущий элемент и его потенциального родителя, пока нейдется больший родитель
     * или не дойдем до корня.
     * ----------------------------
     * Сложность - O(N * log(N)).
     * @param array
     */
    private static void makeHeap(double[] array) {
        // проходим массив, начиная с конца
        for (int i = array.length - 1; i >= 0; i--) {

            int currentIndex = i;
            int parentIndex = (i - 1) / 2;
            // меняем местами текущий элемент и потенциального родителя,
            // пока не встретим большего родителя, или не дойдем до корня.
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

        // Нормализация кучи после удаления корня
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

    /**
     * Быстрая сортировка.
     * Суть - выбираем разделяющий элемент(есть разные стратегии),
     * перемещаем все элементы меньше него в левую часть массива,
     * большие в правую. Т.о. разделяющий элемент будет находится в своем конечном положении(отсортирован).
     * Выполняем рекурсивно эти действия для части массива слева от разделяющего элемента и части мссива справа от него,
     * пока левая или правая части массива не будет состоять из одного или менее элементов.
     * --------------------------------
     * Сложность - O(N2).
     * @param array
     * @param start
     * @param end
     */
    private static void quickSortInternal(double[] array, int start, int end) {

        // условие вызода из рекурсии:
        // если индекс начального элемента обрабатываемой части массива >=
        // идексу последнего элемента обрабатываемой части массива.
        if (start >= end) {
            return;
        }

        int currentStart = start; // index прохода части массива с начало до конца
        int currentEnd = end; // index прохода части массива с конца до начала
        int dividerIndex = start; // в качестве разделяющего элемента возьмем первый.
        double divider = array[dividerIndex]; // сохраняем разделяющий элемент.
        array[dividerIndex] = 0; // удаляем разделяющий элемент.

        // Проходим часть массива начиная с конца пока ненайдем элемент меньший разделяющего,
        // и кладем его в образовавшуюся ячейку.
        // Проходим часть массива начиная с начала пока ненайдем элемент больший разделяющего,
        // и кладем его в образовавшуюся ячейку на предыдущем шаге.
        // Цикл работает пока индексы начала и конца не сойдутся или разойдутся,
        // тогда кладем разделитель в оставшуюся пустую ячейку.
        while (true) {

            // Проходим часть массива начиная с конца пока ненайдем элемент меньший разделяющего,
            // и кладем его в образовавшуюся ячейку.
            while (divider <= array[currentEnd] && currentEnd > currentStart) {
                currentEnd--;
            }

            // индексы начала и конца сошлись в одной ячейке или разошлись,
            // кладем разделитель в оставшуюся пустую ячейку - currentStart.
            if (currentStart >= currentEnd) {
                array[currentStart] = divider;
                dividerIndex = currentStart;
                break;
            }

            // перемещаем найденный элемент в пустую ячейку.
            array[currentStart] = array[currentEnd];

            // Проходим часть массива начиная с конца пока ненайдем элемент меньший разделяющего,
            // и кладем его в образовавшуюся ячейку.
            while (divider > array[currentStart] && currentEnd > currentStart) {
                currentStart++;
            }

            // индексы начала и конца сошлись в одной ячейке или разошлись,
            // кладем разделитель в оставшуюся пустую ячейку - currentEnd.
            if (currentStart >= currentEnd) {
                array[currentEnd] = divider;
                dividerIndex = currentEnd;
                break;
            }

            // перемещаем найденный элемент в пустую ячейку.
            array[currentEnd] = array[currentStart];
        }

        quickSortInternal(array, start, dividerIndex - 1); // обрабатываес часть массива слева от разделителя
        quickSortInternal(array, dividerIndex + 1, end); // обрабатываес часть массива справа от разделителя
    }
    
    public static void quickSort(double[] array) {
        quickSortInternal(array, 0, array.length - 1);
    }


    /**
     * Сортировка слиянием.
     * Суть - рекурсивно разбиваем миссив на 2 части,
     * пока все части массива не будут состоять из одного элемента.
     * Далее, на каждом шаге рекурсии сливаем части массива во временный массив в пордке возрастания,
     * и записываем частично отсортированную часть в исходный массив.
     * Метод работает, т.к. на каждом шаге рекурсии происходит частичная сортировка элементов.
     * ------------------------------
     * Сложность - O(N2)
     * @param array
     * @param start
     * @param end
     */
    private static void mergeSortInner(double[] array, int start, int end) {

        // точка выхода из рекурсии (сортируемая часть массива содержит 1 элемент).
        if (start == end) return;

        // находим середину сортируемой части массива.
        int middle = (end + start) / 2;

        // вызываем метод для левой и правй части массива (чтобы дойти до максимального разбиения).
        mergeSortInner(array, start, middle);
        mergeSortInner(array, middle + 1, end);

        double[] temp = new double[ end - start + 1 ]; // создаем временный массив куда будем сливать отсортированную часть массива.

        int leftIndex = start;
        int rightIndex = middle + 1;
        int tempIndex = 0;

        // текущаю часть массива разбиваем на две и заполняем временный массив в порядке возрастания.
        while (leftIndex <= middle && rightIndex <= end) {
            double min;
            if (array[leftIndex] < array[rightIndex]) {
                min = array[leftIndex++];
            } else {
                min = array[rightIndex++];
            }

            temp[tempIndex++] = min;
        }

        // если закончились элементы в одной из частей текущего куска массива
        // (когда в одной из частей находим максимальный элемент),
        // то сливаем элементы одной из отсавшейся части массива
        while (leftIndex <= middle) {
            temp[tempIndex++] = array[leftIndex++];
        }

        while (rightIndex <= end) {
            temp[tempIndex++] = array[rightIndex++];
        }

        // заполняем сортируемую часть массива, результатами сортировки.
        for (int i = 0; i < temp.length; i++) {
            array[start + i] = temp[i];
        }
    }

    public static void mergeSort(double[] array){
        mergeSortInner(array, 0, array.length - 1);
    }

    /**
     * Сортировка подсчетом
     * (данная реализация с массивом для хранения количеств подходиттолько для массивов
     * c положительными элементами в небольшом интервале, можно исользовать hashmap).
     * Создаем массив индексы которого - элементы сортируемого массива,
     * а значения - кол-во их повторений.
     * -----------------------------------
     * Сложность - O(N + M).
     * N - кол-во элементов в сортируемом массиве.
     * M - кол - во уникальных элементов в сортируемом массиве.
     * @param array
     * @param maxValue
     */
    public static void countSort(int[] array, int maxValue) {

        int[] counts = new int[maxValue + 1];

        // заполняем массив количествами всех элементов
        for (int i = 0; i < array.length; i++) {
            counts[array[i]] = counts[array[i]] + 1;
        }


        // заполняем исходный массив, данными из массива количеств.
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            int elementCount = counts[i];

            while (elementCount != 0) {
                array[index++] = i;
                elementCount--;
            }
        }
    }

    /**
     * Хорошо подходим для равномернораспределенных массивов.
     * НЕ ДОДЕЛАЛ!
     * ------------------
     * Сложность O(N + M)
     * @param array
     * @param start
     * @param end
     */
    public static void bucketSort(double[] array, int start, int end) {

        if (start >= end) return;

        int bucketCount = 5;
        int bucketSize = array.length / bucketCount;

        while (bucketCount != 0) {
            bucketSort(array, start, bucketSize);
            bucketCount += bucketSize;
            bucketCount--;
        }




    }
}

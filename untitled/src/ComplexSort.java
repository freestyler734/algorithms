public class ComplexSort {


    // Сортировка Шелла
    public static void shellSort(double[] array) {

        int h = 1;
        int size = array.length;

        while (h < size) {
            h = 3 * h + 1;
        }

        while (h > 0) {

            for (int outIndex = h; outIndex < size; outIndex ++) {
                double temp = array[outIndex];
                int innerIndex = outIndex;

                // Перемещаем текущий элемент(temp), пока не встретим меньший (на сл. шагах меньшие сместятся в начало).
                while (innerIndex > h - 1 && temp <= array [innerIndex - h]) {
                    array[innerIndex] = array[innerIndex - h];
                    innerIndex -= h;
                }

                // Вставляем temp на место последнего смещенного(большего) элемента.
                array[innerIndex] = temp;
            }
            h = (h - 1) / 3;
        }
    }

    // БЫСТРАЯ СОРТИРОВКА!!!


    public static void swap(double[] array, int index1, int index2) {
        double temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    // Разбиение
    public static int partitionIt(double[] array, int left, int right, double pivot) {

        int leftIndex = left - 1;
        int rightIndex = right; // правый элемент - опорный, поэтому без него.

        while (true) {
            // нет проверки на выход за правую границу,
            // т.к. указатель всегда будет останавливаться на крайнем элементе левого подмассива (условие по выходу)
            // или на крайнем правом элементе(эсле все элементы меньше опорного).
            while (array[++leftIndex] < pivot) {
            }

            while (rightIndex > 0 && array[--rightIndex] > pivot) {
            }

            if (leftIndex >= rightIndex) {
                break;
            } else {
                swap(array, leftIndex, rightIndex);
            }

        }
        swap(array, leftIndex, right);
        return leftIndex;
    }

    public static void recQuickSort(double[] array, int left, int right) {
        if (right - left <= 0) { // м.б. как в начале так и в конце.
            return;
        } else {
            int partionIt = partitionIt(array, left, right, array[right]);
            recQuickSort(array, left, partionIt - 1);
            recQuickSort(array, partionIt + 1, right);

        }
    }

    public static void quickSort(double[] array) {
        recQuickSort(array, 0, array.length - 1);
    }

}

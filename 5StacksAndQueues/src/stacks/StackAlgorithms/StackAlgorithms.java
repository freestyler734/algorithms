package stacks.StackAlgorithms;

import stacks.ArrayStack.ArrayStack;

import java.util.Stack;

public class StackAlgorithms {

    /**
     * Метод реверсирует массив с помощью стека.
     * ----------------
     * Сложность - O(N).
     * @param array
     */
    public static void reverseArray(double[] array) {

        ArrayStack stack = new ArrayStack(array.length);

        // добавляем элементы в стек
        for (int i = 0; i < array.length; i++) {
            stack.push(array[i]);
        }

        // переносим элементы из стека в массив
        for (int i = 0; i < array.length; i++) {
            array[i] = stack.pop();
        }
    }

    /**
     * Задача имея два перегона(временных хранилища) и один текущий
     * перегнать вагоны в текущий в порядке возрастания.
     * стр. 124-125.
     * @param array
     */
    public void wagonSort(double[] array) {

        ArrayStack current = new ArrayStack(array.length);
        // Дополнительные перегоны
        ArrayStack first = new ArrayStack(array.length);
        ArrayStack second = new ArrayStack(array.length);

        // добавляем в текущий перегон
        for (int i = 0; i < array.length; i++) {
            current.push(array[i]);
        }

        double prevoius = current.pop();
        first.push(prevoius);

        while (current.hasElements()) {
            double currentElement = current.pop();

            if (currentElement >= prevoius) {
                first.push(currentElement);
            } else {

            }
        }

    }

    /**
     * Сортировка стека методом вставки
     * (берем неотсортированный элемент и вставляем в нужное место отсортированной части).
     * Для такой сортировки необходим временный стек.
     * Алгоритм:
     * 1) Берем элемент со стека(по определению берется с конца).
     * 2) Далее кладем во временный стек все неотсортированные элементы (получается что стек во временном поворачивается).
     * 3) После кладем все большие текущего (взятого на первом) отсортированные элементы во временный стек.
     * 4) И вставляем текущий элемент в исходный стек.
     * 5) Переносим все элементы со временного стека в исходный.
     * После возвращая элементы в исходный стек, вставляем сортируемый элемент в нужное место.
     * ------------------------
     * Сложность O(N2).
     * @param stack
     */
    public static void insertionSort(ArrayStack stack, int size) {
        ArrayStack temp = new ArrayStack(size);
        int cursor = 0; // указатель на последний отсортированный элемент

        // основной цикл прохода по элементам стека.
        while (cursor < size) {
            // берем элемент (текущий)
            double current = stack.pop();

            // вставляем во временный стек неотсортированную часть исходного стек
            int i = size - 1; // i - index, а индексируем с нуля.
            while (i > cursor) {
                temp.push(stack.pop());
                i--;
            }

            // вставляем элементы во временный стек из отсортированной части исходного стека,
            // до тех пор пока не найдем элемент меньше текущего.
            while (stack.hasElements()) {
                double currentSorted = stack.pop();

                if (currentSorted > current) {
                    temp.push(currentSorted); // отсортированный элемент больше текщуего, вставляем его во временный стек.
                } else {
                    // нашли элемент меньше (или равный) текущего.
                    // Т.к. для проверки значения элемента необходимо сперва его достать из стека,
                    // то вставляем его обратно (т.к. он меньше текущего должен быть перед текущим)
                    stack.push(currentSorted);
                    break;
                }
            }

            // вставляем текущий элемент в стэк
            // и сдивигаем курсор.
            stack.push(current);
            cursor++;

            // переносим все элементы из временного в исходный стек.
            while (temp.hasElements()) {
                stack.push(temp.pop());
            }
        }
    }

    /**
     * Сортировка стека методом выбора.
     * (Выбираем наибольший элемент и вставляем его в начало стека).
     * Для реализации алгоритма используется временный стек.
     * Алгоритм:
     * 1) Переносим все неотсортированные элементы из исходного стека во временный,
     *    попутно отыскивая наибольший.
     * 2) Наименьший элемент добавляем в исходный стек.
     * 3) Переносим все элементы из временного в исходный стек.
     * -----------------------------------------------------------
     * Сложность - O(N2).
     * @param stack
     * @param size
     */
    public static void selectionSort(ArrayStack stack, int size) {
        ArrayStack temp = new ArrayStack(size); // временный стек

        int cursor = 0;
        while (cursor < size) {
            double smallest = stack.pop();

            // ищем наим. элемент среди неотсортированных.
            int i = size - 1;
            while (i > cursor) {
                double current = stack.pop();

                if (current < smallest) {
                    // нашли меньший элемент, чем предыдущий нименьший,
                    // кладем предыдущий наименьший во временный стек,
                    // а в переменную сохраняем найденный.
                    temp.push(smallest);
                    smallest = current;
                } else {
                    // большие(или равные) элементы просто перекидываем во временный стек.
                    temp.push(current);
                }

                i--;
            }

            // добавляем найденный элемент в стек и сдвигаем курсор.
            stack.push(smallest);
            cursor++;

            // перекидываем элементы из временного стека обратно в исходный.
            while (temp.hasElements()) {
                stack.push(temp.pop());
            }

        }


    }


}

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
     * Для такой сортировки необходим временный стек, куда  кладем неотсортированную чать стека.
     * После возвращая элементы в исходный стек, вставляем сортируемый элемент в нужное место.
     * @param stack
     */
    public static ArrayStack insertionSort(ArrayStack stack, int size) {
        ArrayStack temp = new ArrayStack(size);
        int cursor = 0;

        while (cursor != size) {
            double current = stack.pop();
            cursor++;

            int i = size;

            while (i >= cursor) {
                temp.push(stack.pop());
                i--;
            }

            if (stack.hasElements()) {
                double currentTemp = stack.pop();
                while (current < currentTemp && stack.hasElements()) {
                    temp.push(current);
                    currentTemp = stack.pop();
                }
            }

            stack.push(current);
            while (temp.hasElements()) {
                stack.push(temp.pop());
            }



        }

        return stack;
    }
}

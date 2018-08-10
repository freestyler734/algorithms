/**
 * Поиск простых чисел до верхнего предела
 */
public class SimpleElements {
    public static void main(String[] args) {

        int[] array = findSimpleElements(90);
        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            if (element != -1) System.out.println(element);
        }
    }

    /**
     * Поиск простых чисел по методу Решето Эмбера
     * @param n
     * @return
     */
    public static int[] findSimpleElements(int n) {
        int[] array = new int[n - 1];

        // Заполняем таблицу в указанных пределах.
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 2;
        }

        // Исключаем числа кратные 2-м, кроме 2.
        for (int i = 2; i < array.length; i += 2) {
            array[i] = -1;
        }

        // Вычеркиваем все повторяющиеся кратные значения.
        int i = 1;
        while (i <= Math.sqrt(n)) {
            int element = array[i];

            if (element != -1) {
                int j = i + 2;
                while(j < array.length) {
                    if (array[j] % element == 0) {
                        array[j] = -1;
                    }

                    j += 2;
                }
            }


            i++;
        }





        return array;
    }

}

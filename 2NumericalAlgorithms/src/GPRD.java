import java.sql.SQLOutput;

/** Генератор песевдослучайных чисел (Xi+1 = (A * Xn + B) mod M)
 *  A, B, и M — постоянные.
*/
public class GPRD {
    static int X = 0;
    static int A = 7;
    static int B = 5;
    static int M = 11;

    public static void main(String[] args) {
        // 1. Напишите алгоритм, в котором игральная кость будет генерировать подбрасывания монеты.
        // - 0 - ОРЕЛ, 1 - РЕШКА.
        // Решение:
        // System.out.println(normalize(generateNumber(), 0, 1) >= 0.5 ? 1 : 0);

        // 5. Напишите алгоритм для выбора случайных величин M из массива, содержащего
        // N элементов (где M <= N). Определите время его работы и примените
        // к примеру, где необходимо раздать книги пяти людям, выбранным из
        // 100 (или 10 000) записей
        // Решение:
        // int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        // System.out.println(array[generateNumber()]);


    }

    /**
     *  Генерирует Псевдослучайные величины
     * @return
     */
    public static int generateNumber() {
        X = (A*X + B) % M;
        return X;
    }

    /**
     * Нормализует число в указанных пределах
     * @param x - число
     * @param a - min
     * @param b - max
     * @return
     */
    public static double normalize(double x, double a, double b) {
        // Для обеспечения равноправия(стат. верности) генерируемых ПСВ в диапазоне
        // необходимо полученное значение нормировать (0..1), а затем
        // умножить на желаемый диапазон (min + (X / M) * (max - min)), т.е. нормируем в диапазоне.
        // Пусть 1 - min
        //       2 - max
        // System.out.println( 1 + ( (double) generateNumber() / M ) * ( 2 - 1 ) );
        return (a + ( ( x / M ) * (b - a) ) );
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * Простые множители.
 */
public class SimpleMultipliers {

    public static void main(String[] args) {
        System.out.println(getSimpleMultipliers(57402));
    }

    /**
     * Получение простых сножителей числа
     * @param x
     * @return
     */
    public static List getSimpleMultipliers(int x) {
        List<Integer> result = new ArrayList<>();

        // Если число делится на 2, то сохраняем его,
        // и убираем все четные множители из числа(они точно не простые!)
        while(x % 2 == 0) {
            result.add(2);
            x /= 2;
        }

        // начинаем с нечетного числа,
        // а заканчиваем корнем числа-параметра, т.к.
        //  Если n == p * q, то p или q должно быть меньше либо равно sqrt (n). (Если p и q больше
        // sqrt (n), их произведение превысит n.)
        int i = 3;
        int end = (int) Math.sqrt(x);

        while(i <= end) {
            // Ищем множители числа x, и убираем его множители(кратные i).
            while (x % i == 0) {
                result.add(i);
                x /= i;
                end = (int) Math.sqrt(x);
            }

            i += 2;
        }

        // Если от числа что-то осталось(кроме 1), остаток тоже множитель.
        if (x != 1) result.add(x);

        return result;
    }
}

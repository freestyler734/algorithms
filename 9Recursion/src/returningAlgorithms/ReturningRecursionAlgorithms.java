package returningAlgorithms;

/**
 * Реализация алгоритмов с возвратом (просчитывают возможность справиться с поставленной задачей).
 * Используется для исключения заведомо ложных частичных решений.
 *
 */
public class ReturningRecursionAlgorithms {

    /**
     * Решение задачи о 8 ферзях метод алгоритма с возвратом.
     * Необходимо расставить на шахматной доске(64 клетки) 8 ферзей так,
     * чтобы они не могли атаковать друг друга(не находились на одной строчке, ряду или диагонали).
     * Для решения задачи используется алгоритм с возвратом.
     */
    public static void eightQueenProblem(boolean[][] field, int positionedQueenCount) {

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[0].length; col++) {

            }
        }
    }

    /**
     * Проверяет допустимо ли решение при текущей расстановки ферзей.
     * @param field
     * @return
     */
    private static boolean isLegal(boolean[][] field) {

        int trueCount = field[0][0] ? 1 : 0;

        for (int diagonalIndex = 0; diagonalIndex < field.length; diagonalIndex++) {
            int index = diagonalIndex;

            while (index < field.length) {
                index++;
            }
        }

        return false;
    }
}

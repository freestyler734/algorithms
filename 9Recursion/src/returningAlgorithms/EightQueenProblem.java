package returningAlgorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Решение задачи о 8 ферзях.
 * Для решения задачи используется алгоритм с возвратом (просчитывают возможность справиться с поставленной задачей).
 * Алгоритм с возвратом метод - для исключения заведомо ложных частичных решений.
 *
 */
public class EightQueenProblem {

    public static int stepCount = 0;



    static int[][] empty = {{-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1}};

    /**
     * Решение задачи о 8 ферзях метод алгоритма с возвратом.
     * Необходимо расставить на шахматной доске(64 клетки) 8 ферзей так,
     * чтобы они не могли атаковать друг друга(не находились на одной строчке, ряду или диагонали).
     * Для решения задачи используется алгоритм с возвратом.
     */
    public static boolean eightQueenProblem(int[][] indexes, int positionedQueenCount) {

        //if (!isLegal(indexes)) return false;

        if (positionedQueenCount == 8){
             return true; // одно решение
        }

        for (int row = positionedQueenCount; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int[] currentIndex = {row, col};
                if (!isTaken(indexes, currentIndex) && checkPosition(indexes, currentIndex)) {

                    indexes[positionedQueenCount] = currentIndex;
                    stepCount++;

                    if (eightQueenProblem(indexes, positionedQueenCount + 1)) {
                        return true;
                    }

                    indexes[positionedQueenCount] = new int[]{-1,-1};
                }
            }
        }

        return false;
    }

    /**
     * Проверяет допустимо ли решение при текущей расстановки ферзей.
     * @param indexes
     * @return
     */
    public static boolean isLegal(int[][] indexes) {

        if (indexes[0][0] == -1) return true;

       for (int i = 0; i < 8; i++) {
           int[] queenIndex1 = indexes[i];

           for (int j = i + 1; j < 8; j++) {
               int[] queenIndex2 = indexes[j];

               if (queenIndex2[0] == -1) break;

               if (queenIndex1[0] == queenIndex2[0] ||
                   queenIndex1[1] == queenIndex2[1] ||
                   (Math.abs(queenIndex1[0] - queenIndex2[0]) == Math.abs(queenIndex1[1] - queenIndex2[1]))) {
                   return false;
               }
           }
       }
        return true;
    }

    private static boolean isTaken(int[][] indexes, int[] index) {

        for (int i = 0; i < 8; i++) {
            int[] queenIndex = indexes[i];

            if (queenIndex[0] == index[0] && queenIndex[1] == index[1]) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkPosition(int[][] indexes, int[] queenIndex1) {

        if (indexes[0][0] == -1) return true;

       // if (queenIndex1[0] == 4 && queenIndex1[1] == 6) {
     //       System.out.println();
       // }

        int i = 0;
        while (i < 8) {
            int[] queenIndex2 = indexes[i];

            if (queenIndex2[0] == -1) break;

            if (queenIndex1[0] == queenIndex2[0] ||
                    queenIndex1[1] == queenIndex2[1] ||
                    (Math.abs(queenIndex1[0] - queenIndex2[0]) == Math.abs(queenIndex1[1] - queenIndex2[1]))) {
                return false;
            }

            i++;
        }

        return true;
    }

    public static void printQueens(int[][] indexes) {
        int index = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (indexes[index][0] == i && indexes[index][1] == j) {
                    System.out.print("|1");
                } else {
                    System.out.print("|_");
                }
            }
            index++;
            System.out.print("|");
            System.out.println();
        }
    }




}

package returningAlgorithms;

/**
 * Решение задачи ход коня - конь должен обойти все ячейки шахматной доски, при этом побывав в каждой ячейки один раз.
 */
public class HorseStep {

    public static boolean horseTour(int[][] field, int rowStart, int colStart,  int cellTakenCount) {

        field[rowStart][colStart] = ++cellTakenCount;

        if (cellTakenCount == 36) {
            return true;
        }

        int[] row = {-2, -2, 2,  2, 1,  1, -1, -1};
        int[] col = { 1, -1, 1, -1, 2, -2,  2, -2};

        for (int i = 0; i < 8; i++) {
            int rowIndex = rowStart + row[i];
            int colIndex = colStart + col[i];

            if (rowIndex >= 0 && rowIndex < 6 &&
                colIndex >= 0 && colIndex < 6 &&
                field[rowIndex][colIndex] == 0) {


                if (horseTour(field, rowIndex, colIndex, cellTakenCount)) {
                    return true;
                }

            }
        }

        field[rowStart][colStart] = 0;
        return false;
    }

    public static void printHorses(int[][] field, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("|" + field[i][j]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("==========");
    }
}

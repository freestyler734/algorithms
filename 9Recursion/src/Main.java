import baseAlrorithms.BaseAlgorithms;

public class Main {

    public static void main(String[] args) {

        int[][] array = {{0,1,0,0,0},
                         {0,0,0,0,0},
                         {0,0,0,1,0},
                         {1,0,0,0,0},
                         {1,0,0,0,1}};
        // диагональ: i1 - i2 = j1 - j2;
        // столбец:   j1 = j2;
        // ряд:       i1 = i1;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                System.out.print(String.format("%d|%d   ", i, j));
            }
            System.out.println();
        }
    }
}

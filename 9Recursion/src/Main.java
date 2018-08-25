import returningAlgorithms.EightQueenProblem;

public class Main {

    public static void main(String[] args) {

<<<<<<< HEAD
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
=======
        int[][] indexes = {{-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1},
                            {-1,-1}};

       System.out.println(EightQueenProblem.eightQueenProblem(indexes, 0));

       EightQueenProblem.printQueens(indexes);
>>>>>>> b230da160d09b7e7217897c20005c23d596e1c64
    }


}

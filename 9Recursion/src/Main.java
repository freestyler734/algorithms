import returningAlgorithms.EightQueenProblem;

public class Main {

    public static void main(String[] args) {

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
    }


}

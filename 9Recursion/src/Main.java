import combinatorics.Combinations;
import combinatorics.Permutes;
import returningAlgorithms.ConeDevTest;
import returningAlgorithms.EightQueenProblem;
import returningAlgorithms.HorseStep;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

        //int[][] field = new int[6][6];

       // System.out.println(EightQueenProblem.eightQueenProblem(indexes, 0));
       // System.out.println(EightQueenProblem.stepCount);

      // System.out.println(HorseStep.horseTour(field, 0,0,0));

//        EightQueenProblem.printQueens(indexes);

      // HorseStep.printHorses(field, 6);


        /*LinkedList<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");


        Combinations.displayList(Combinations.combinationsOf(list, 3));*/

        int[][] array = {{8, 0, 5, 1},
                        {4, 4, 3, 6},
                        {3, 7, 1, 2},
                        {5, 0, 5, 2},
                        {3, 0, 4, 3},
                        {5, 1, 0, 7},
                        {4, 0, 3, 3},
                        {5, 4, 6, 0},
                        {3, 4, 3, 0},
                        {1, 7, 3, 8},
                        {0, 7, 7, 3},
                        {3, 2, 0, 4}};

        List result = new ArrayList();

        System.out.println(ConeDevTest.test(array, result, 0));

        for (int i = 0; i < 12; i++) {
            int[] block = (int[]) result.get(i);
            System.out.println(String.format("{%d, %d, %d, %d}", block[0],block[1],block[2],block[3]));
        }

    }


}

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

        int[][] array = {{1, 0, 7, 5,},
                        {3, 4, 1, 3},
                        {5, 1, 1, 4},
                        {7, 6, 1, 8},
                        {5, 7, 4, 4},
                        {8, 3, 3, 4},
                        {7, 1, 5, 4},
                        {4, 1, 1, 0},
                        {3, 1, 1, 4},
                        {2, 4, 3, 8},
                        {5, 1, 1, 7},
                        {4, 2, 4, 2}};

        /*int[][] array = {{8, 0, 5, 1},
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
                        {3, 2, 0, 4}};*/
        /*int[][] array = {{1, 6, 4, 0},
                        {6, 1, 3, 5},
                        {4, 0, 5, 2},
                        {4, 1, 3, 5},
                        {5, 1, 4, 2},
                        {6, 5, 5, 4},
                        {4, 0, 4, 6},
                        {5, 2, 1, 4},
                        {4, 6, 0, 1},
                        {2, 0, 1, 4},
                        {3, 0, 4, 5},
                        {4, 0, 5, 1}};*/

        List result = new ArrayList();

        ConeDevTest.test(array, result);

        System.out.println(ConeDevTest.steps);



    }


}

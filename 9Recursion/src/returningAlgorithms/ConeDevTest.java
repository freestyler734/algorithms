package returningAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConeDevTest {

    public static int steps = 0;

    public static void test(int[][] array, List result) {

        boolean res = false;
        if (result.size() == 12) {
            display(result);
            System.out.println("--------------------------");
            return;
        }

        for (int i = 0; i < 12; i++) {
            if (!result.contains(array[i]) && check(result, array[i])) {

                result.add(array[i]);

                test(array, result);

                result.remove(array[i]);
            }

        }

    }

    private static boolean check(List list, int[] element) {

        if (list.size() == 0) return true;

        int[] first;
        int[] second;
        int[] third;
        int[] forth;
        int[] fifth;
        int[] sixth;
        int[] seventh;
        int[] eighth;
        int[] nineth;
        int[] tenth;
        int[] eleventh;


        switch (list.size()) {
            case 0:
                return true;
            case 1:
                first = (int[]) list.get(0);
                if (first[1] + element[0] > 10 || first[3] + element[2] > 10) return false;
                return true;
            case 2:
                first = (int[]) list.get(0);
                if (first[2] + element[1] > 10) return false;
                return true;
            case 3:
                first = (int[]) list.get(0);
                second = (int[]) list.get(1);
                third = (int[]) list.get(2);
                if (third[3] + element[2] > 10 ||
                        first[2] + element[0] + third[1] > 10 ||
                        first[3] + element[1] + second[2] > 10) return false;
                return true;
            case 4:
                first = (int[]) list.get(0);
                second = (int[]) list.get(1);
                forth = (int[]) list.get(3);
                if (forth[3] + element[2] > 10 ||
                        first[3] + second[2] + forth[1] + element[0] != 10) return false;
                return true;
            case 5:
                second = (int[]) list.get(1);
                fifth = (int[]) list.get(4);
                if (fifth[3] + element[2] > 10 ||
                        second[3] + fifth[1] + element[0] > 10) return false;
                return true;
            case 6:
                third = (int[]) list.get(2);
                forth = (int[]) list.get(3);
                if (third[2] + element[0] > 10 ||
                        third[3] + forth[2] + element[1] > 10) return false;
                return true;
            case 7:
                third = (int[]) list.get(2);
                forth = (int[]) list.get(3);
                fifth = (int[]) list.get(4);
                seventh = (int[]) list.get(6);
                if (seventh[3] + element[2] > 10 ||
                        third[3] + forth[2] + seventh[1] + element[0] != 10 ||
                        forth[3] + fifth[2] + element[1] > 10) return false;
                return true;
            case 8:
                forth = (int[]) list.get(3);
                fifth = (int[]) list.get(4);
                sixth = (int[]) list.get(5);
                eighth = (int[]) list.get(7);
                if (eighth[3] + element[2] > 10 ||
                        forth[3] + fifth[2] + eighth[1] + element[0] != 10 ||
                        fifth[3] + sixth[2] + element[1] > 10) return false;
                return true;
            case 9:
                fifth = (int[]) list.get(4);
                sixth = (int[]) list.get(5);
                nineth = (int[]) list.get(8);
                if (sixth[3] + element[1] > 10 ||
                        nineth[3] + element[2] > 10 ||
                        fifth[3] + sixth[2] + nineth[1] + element[0] != 10) return false;
                return true;
            case 10:
                seventh = (int[]) list.get(6);
                eighth = (int[]) list.get(7);
                nineth = (int[]) list.get(8);
                if (seventh[3] + eighth[2] + element[0] > 10 ||
                        eighth[3] + nineth[2] + element[1] > 10) return false;
                return true;
            case 11:
                eighth = (int[]) list.get(7);
                nineth = (int[]) list.get(8);
                tenth = (int[]) list.get(9);
                eleventh = (int[]) list.get(10);
                if (eleventh[3] + element[2] > 10 ||
                        nineth[3] + tenth[2] + element[1] > 10 ||
                        eighth[3] + nineth[2] + eleventh[1] + element[0] != 10) return false;
                return true;
        }
        return true;
    }

    private static void display(List list) {
        int[] first = (int[]) list.get(0);
        int[] second = (int[]) list.get(1);
        int[] third = (int[]) list.get(2);
        int[] forth = (int[]) list.get(3);
        int[] fifth = (int[]) list.get(4);
        int[] sixth = (int[]) list.get(5);
        int[] seventh = (int[]) list.get(6);
        int[] eighth = (int[]) list.get(7);
        int[] nineth = (int[]) list.get(8);
        int[] tenth = (int[]) list.get(9);
        int[] eleventh = (int[]) list.get(10);
        int[] twelveth = (int[]) list.get(11);

        System.out.print("     ");
        System.out.print(first[0] + "  " + first[1] +"|"+ second[0] + "  " + second[1]);
        System.out.println();
        System.out.println();
        System.out.print("     ");
        System.out.print(first[2] + "  " + first[3] +"|"+ second[2] + "  " + second[3]);

        System.out.println();

        System.out.print(third[0] + "  " + third[1] +"|"+ forth[0] + "  " + forth[1] +"|");
        System.out.print(fifth[0] + "  " + fifth[1] +"|"+ sixth[0] + "  " + sixth[1] +"|");
        System.out.println();
        System.out.println();
        System.out.print(third[2] + "  " + third[3] +"|"+ forth[2] + "  " + forth[3] +"|");
        System.out.print(fifth[2] + "  " + fifth[3] +"|"+ sixth[2] + "  " + sixth[3] +"|");

        System.out.println();

        System.out.print(seventh[0] + "  " + seventh[1] +"|"+ eighth[0] + "  " + eighth[1] +"|");
        System.out.print(nineth[0] + "  " + nineth[1] +"|"+ tenth[0] + "  " + tenth[1] +"|");
        System.out.println();
        System.out.println();
        System.out.print(seventh[2] + "  " + seventh[3] +"|"+ eighth[2] + "  " + eighth[3] +"|");
        System.out.print(nineth[2] + "  " + nineth[3] +"|"+ tenth[2] + "  " + tenth[3] +"|");

        System.out.println();

        System.out.print("     ");
        System.out.print(eleventh[0] + "  " + eleventh[1] +"|"+ twelveth[0] + "  " + twelveth[1]);
        System.out.println();
        System.out.println();
        System.out.print("     ");
        System.out.print(eleventh[2] + "  " + eleventh[3] +"|"+ twelveth[2] + "  " + twelveth[3]);
        System.out.println();
    }
}

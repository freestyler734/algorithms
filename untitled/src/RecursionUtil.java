import com.sun.org.apache.xerces.internal.impl.dv.dtd.NOTATIONDatatypeValidator;

public class RecursionUtil {

    //Вычисление значения числа треуголного ряда по номеру.
    static int circleTriangle(int n) {
        int result = n;

        while (n > 0) {
            result += --n;
        }

        return  result;
    }

    static int recursiveTriangle(int n) {
        System.out.println("Entering: n=" + n);
        if (n == 1) {
            System.out.println("Returning 1");
            return 1;
        }

        int temp = n + recursiveTriangle(n - 1);
        System.out.println("Returning " + temp);
        return (temp);
    }

    static int recursiveFactorial(int n) {

        if (n == 0) {
            return 1;
        }

        return (n * recursiveFactorial(n - 1));
    }

    static int binarySearch(double value, double[] sortedArray) {
        return recursiveBinarySearch(sortedArray, value, 0, sortedArray.length -1);
    }

    static int recursiveBinarySearch(double[] sortedArray, double value, int minBound, int maxBound) {
        int currenrIndex = (maxBound + minBound) / 2;

        if (sortedArray[currenrIndex] == value) {
            return currenrIndex;
        }

        if (minBound > maxBound) {
            throw new ArrayIndexOutOfBoundsException();
        }

        if (sortedArray[currenrIndex] > value) {
            return recursiveBinarySearch(sortedArray, value, currenrIndex + 1, maxBound);
        } else {
            return recursiveBinarySearch(sortedArray, value, minBound, maxBound - 1);
        }
    }




    // СОРТИРОВКА СЛИЯНИЕМ

    // Слияние 2-x отсортированных массивов в 3-й.
    public static void merge(double[] workSpace, int minBound, int mid, int maxBound) {
        int i1 = minBound;
        int i2 = mid;
        int ri = 0;
        double[] result = new double[maxBound + 1];
        while (i1 < mid && i2 <= maxBound) {
            if (workSpace[i1] < workSpace[i2]) {
                result[ri++] = workSpace[i1++];
            } else {
                result[ri++] = workSpace[i2++];
            }
        }

        while (i1 < mid) {
            result[ri++] = workSpace[i1++];
        }

        while (i2 <= maxBound) {
            result[ri++] = workSpace[i2++];
        }

        ri = 0;
        for (int j = minBound; j <= maxBound; j++) {
            workSpace[j] = result[ri++];
        }
    }

    private static void innerMergeSort(double[] workSpace, int minBound, int maxBound) {

        if (minBound == maxBound) {
            return;
        }

        int mid = (maxBound + minBound) / 2;

        innerMergeSort(workSpace, minBound, mid);
        innerMergeSort(workSpace, mid + 1, maxBound);

        // mid + 1, т.к. в merge-методе участки определяются (minbound<=x<mid) && (mid<=y<=maxbound)
        merge(workSpace, minBound, mid + 1, maxBound);
    }

    public static void mergeSort(double[] workSpace) {
        innerMergeSort(workSpace, 0, workSpace.length - 1);
    }
}

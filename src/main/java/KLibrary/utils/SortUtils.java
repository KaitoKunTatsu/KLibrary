package KLibrary.utils;

/**
 * (<a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>)
 * @version	1.3.0 | last edit: 25.11.2022
 * @author Joshua Hartjes | KaitoKunTatsu#3656
 */
public class SortUtils {

    public static String[] quickSort(String[] pArray) {return pArray;}

    public static Object[] quickSort(Object[] pArray) {return pArray;}

    public static long[] quickSort(long[] pArray) {return pArray;}

    public static double[] quickSort(double[] pArray) {return pArray;}

    public static float[] quickSort(float[] pArray) {return pArray;}

    public static int[] quickSort(int[] pArray) {
        return quickSort(pArray, 0, pArray.length-1);
    }

    /**
     * 	Quicksort algorithm: worst-case O(n^2), average- and best-Case O(n*log(n))
     * 	NOT FINISHED YET
     *
     * 	@param pArr	Array
     * */
    public static int[] quickSort(int[] pArr, int pStart, int pEnd) {
        if (pStart > pEnd) return null;

        int lPIndex = partition(pArr, pStart, pEnd);
        quickSort(pArr, pStart, lPIndex - 1);
        quickSort(pArr, lPIndex+1, pEnd);

        return pArr;
    }

    private static int partition(int[] pArr, int pStart, int pEnd) {
        int lPivot = pArr[pEnd];
        int i = pStart;

        for (int j = pStart; j < pEnd; ++j) {
            if (pArr[j] < lPivot) {
                swap(pArr, i, j);
                ++i;
            }
        }
        swap(pArr, i, pEnd);
        return i;
    }

    public static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {10,1,2,44,2,4,24,1,5,2};
        SortUtils.quickSort(arr);
        for (int i:arr) System.out.println(i);
    }
}

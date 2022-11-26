package KLibrary.utils;

/**
 * (<a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>)
 * @version	1.3.0 | last edit: 25.11.2022
 * @author Joshua Hartjes | KaitoKunTatsu#3656
 */
public class SortUtils {

    public static String[] quickSort(String[] pArray) {return pArray;}

    public static Object[] quickSort(Object[] pArray) {return pArray;}

    public static int[] quickSort(int[] pArray) {return pArray;}

    public static long[] quickSort(long[] pArray) {return pArray;}

    public static double[] quickSort(double[] pArray) {return pArray;}

    public static float[] quickSort(float[] pArray) {return pArray;}

    /**
     * 	Quicksort algorithm: worst-case O(n^2), average- and best-Case O(n*log(n))
     * 	NOT FINISHED YET
     *
     * 	@param arr	Array
     * */
    public static int[] quickSort(int[] arr, final int iStart, final int iEnd) {
        int index = partition(arr, iStart, iEnd);

        if (iStart < index-1) quickSort(arr, iStart, index-1);
        if (index < iEnd) quickSort(arr, index, iEnd);

        return arr;
    }

    private static int partition(int[] arr, int iStart, int iEnd) {
        int pivot = (iStart+iEnd)/2;
        int low = iStart;
        int high = iEnd;

        while (low <= high) {
            while (arr[high] > arr[pivot]) high--;
            while (arr[low] < arr[pivot]) low++;
            if (low <= high) swap(arr, low, high); low++; high--;
        }
        return low;
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

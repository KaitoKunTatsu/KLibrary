package KLibrary.utils;

/**
 *
 * <br>
 * Part of the <a href="https://github.com/KaitoKunTatsu/KLibrary">KLibrary</a>
 *
 * @version 1.3.0 | last edit: 18.12.2022
 * @author Joshua Hartjes | KaitoKunTatsu#3656
 */
public class SearchUtils {


    /**
     * 	Binary Search: Worst-Case: O(log(n)), Average-Case: O(log(n)), Best-Case: O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return 		returns	-1 if element not found, returns index of the item in the array similar to the element
     * */
    public int binarySearch(int[] arr, int element)
    {
        int first = 0;
        int last = arr.length-1;
        int mid = (first + last)/2;
        while(first <= last)
        {
            if (arr[mid] == element)
                return mid;

            if (arr[mid] < element)
                first = mid+1;

            else if (arr[mid] > element)
                last = mid-1;

            mid = (first + last)/2;
        }
        return -1;
    }

    /**
     * 	Linear Search: Worst-Case: O(n), Average-Case: O(n/2), Best-Case O(1)
     *
     * 	@param arr					Array which is used to search in
     * 	@param element				Item to be searched for
     * 	@param ignoreCapitalLetters	if true it compares the element to the array due ignoring capital letters in both
     * 	@return 					returns -1 if element not found, returns index of the item in the array similar to the element
     * */
    public int linearSearch(String[] arr, String element, boolean ignoreCapitalLetters)
    {
        for(int i=0; i < arr.length; i++)
        {
            if (ignoreCapitalLetters)
                if (arr[i].toLowerCase().equals(element.toLowerCase())) return i;

            else
                if (arr[i].equals(element)) return i;
        }
        return -1;
    }
}

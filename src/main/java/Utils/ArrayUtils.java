package Utils;

/**
 * This class includes methods avoiding annoying array problems
 *
 * @version	v2.4 02.12.2021
 * @author Joshua H. | KaitoKunTatsu#3656
 */
public class  ArrayUtils {

    // Searching ---------------------------------------------------------------------

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
        int mid = (first +first)/2;
        while(first <= last)
        {
            if (arr[mid] == element)
            {
                return mid;
            }
            if (arr[mid] < element)
            {
                first = mid+1;
            }
            else if (arr[mid] > element)
            {
                last = mid-1;
            }
            mid = (first + last)/2;

        }
        return -1;
    }

    /**
     * 	Binary Search: Worst-Case: O(log(n)), Average-Case: O(log(n)), Best-Case: O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return 		returns	-1 if element not found, returns index of the item in the array similar to the element
     * */
    public int binarySearch(long[] arr, long element)
    {
        int first = 0;
        int last = arr.length-1;
        int mid = (first +first)/2;
        while(first <= last)
        {
            if (arr[mid] == element)
            {
                return mid;
            }
            if (arr[mid] < element)
            {
                first = mid+1;
            }
            else if (arr[mid] > element)
            {
                last = mid-1;
            }
            mid = (first + last)/2;

        }
        return -1;
    }

    /**
     * 	Binary Search: Worst-Case: O(log(n)), Average-Case: O(log(n)), Best-Case: O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return 		returns	-1 if element not found, returns index of the item in the array similar to the element
     * */
    public int binarySearch(float[] arr, float element)
    {
        int first = 0;
        int last = arr.length-1;
        int mid = (first +first)/2;
        while(first <= last)
        {
            if (arr[mid] == element)
            {
                return mid;
            }
            if (arr[mid] < element)
            {
                first = mid+1;
            }
            else if (arr[mid] > element)
            {
                last = mid-1;
            }
            mid = (first + last)/2;

        }
        return -1;
    }

    /**
     * 	Binary Search: Worst-Case: O(log(n)), Average-Case: O(log(n)), Best-Case: O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return 		returns	-1 if element not found, returns index of the item in the array similar to the element
     * */
    public int binarySearch(double[] arr, double element)
    {
        int first = 0;
        int last = arr.length-1;
        int mid = (first +first)/2;
        while(first <= last)
        {
            if (arr[mid] == element)
            {
                return mid;
            }
            if (arr[mid] < element)
            {
                first = mid+1;
            }
            else if (arr[mid] > element)
            {
                last = mid-1;
            }
            mid = (first + last)/2;

        }
        return -1;
    }

    /**
     * 	Binary Search: Worst-Case: O(log(n)), Average-Case: O(log(n)), Best-Case: O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return			returns	-1 if element not found, returns index of the item in the array similar to the element
     * */
    public int binarySearch(String[] arr, String element)
    {
        int first = 0;
        int last = arr.length-1;
        int mid = (first + last)/2;

        while(first <= last)
        {
            if (arr[mid].equals(element))
            {
                return mid;
            }
            if (arr[mid].compareTo(element) < 0 )
            {
                first = mid+1;
            }
            else if (arr[mid].compareTo(element) > 0)
            {
                last = mid-1;
            }
            mid = (first + last)/2;

        }
        return -1;
    }

    /**
     * 	Binary Search: Worst-Case: O(log(n)), Average-Case: O(log(n)), Best-Case: O(1)
     *
     * 	@param arr					Array which is used to search in
     * 	@param element				Item to be searched for
     * 	@param ignoreCapitalLetters	if true it compares the element to the array due ignoring capital letters in both
     * 	@return 					returns	-1 if element not found, returns index of the item in the array similar to the element
     * */
    public int binarySearch(String[] arr, String element, boolean ignoreCapitalLetters)
    {

        int first = 0;
        int last = arr.length-1;
        int mid = (first + last)/2;
        String lE = element;
        String arrMid = arr[mid];

        while(first <= last)
        {
            if (ignoreCapitalLetters) {
                arrMid = arr[mid].toLowerCase();
                lE = element.toLowerCase();
            } else
            {
                arrMid = arr[mid];
            }

            if (arrMid.equals(lE))
            {
                return mid;
            }
            if (arrMid.compareTo(lE) < 0 )
            {
                first = mid+1;
            }
            if (arrMid.compareTo(lE) > 0)
            {
                last = mid-1;
            }
            mid = (first + last)/2;

        }
        return -1;
    }

    /**
     * 	Linear Search: Worst-Case: O(n), Average-Case: O(n/2), Best-Case O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return 		returns -1 if element not found, returns index of the item in the array similar to the element
     * */
    public int linearSearch(int[] arr, int element)
    {
        for(int i=0; i < arr.length; i++)
        {
            if (arr[i] == element) return i;
        }
        return -1;
    }

    /**
     * 	Linear Search: Worst-Case: O(n), Average-Case: O(n/2), Best-Case O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return 		returns -1 if element not found, returns index of the item in the array similar to the element
     * */
    public int linearSearch(float[] arr, float element)
    {
        for(int i=0; i < arr.length; i++)
        {
            if (arr[i] == element) return i;
        }
        return -1;
    }

    /**
     * 	Linear Search: Worst-Case: O(n), Average-Case: O(n/2), Best-Case O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return 		returns -1 if element not found, returns index of the item in the array similar to the element
     * */
    public int linearSearch(double[] arr, double element)
    {
        for(int i=0; i < arr.length; i++)
        {
            if (arr[i] == element) return i;
        }
        return -1;
    }

    /**
     * 	Linear Search: Worst-Case: O(n), Average-Case: O(n/2), Best-Case O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return 		returns -1 if element not found, returns index of the item in the array similar to the element
     * */
    public int linearSearch(long[] arr, long element)
    {
        for(int i=0; i < arr.length; i++)
        {
            if (arr[i] == element) return i;
        }
        return -1;
    }

    /**
     * 	Linear Search: Worst-Case: O(n), Average-Case: O(n/2), Best-Case O(1)
     *
     * 	@param arr		Array which is used to search in
     * 	@param element	Item to be searched for
     * 	@return			returns -1 if element not found, returns index of the item in the array similar to the element
     * */
    public int linearSearch(String[] arr, String element)
    {
        for(int i=0; i < arr.length; i++)
        {
            if (arr[i] == element) return i;
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
            {
                if (arr[i].toLowerCase().equals(element.toLowerCase())) return i;
            }
            else {
                if (arr[i] == element) return i;
            }
        }
        return -1;
    }

    // Searching ---------------------------------------------------------------------


    // Sorting ---------------------------------------------------------------------

    public int[] swap(int[] arr, int i1, int i2)
    {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        return arr;
    }

    public String[] swap(String[] arr, int i1, int i2)
    {
        String  temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        return arr;
    }

    public float[] swap(float[] arr, int i1, int i2)
    {
        float temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        return arr;
    }

    public double[] swap(double[] arr, int i1, int i2)
    {
        double temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        return arr;
    }

    public long[] swap(long[] arr, int i1, int i2)
    {
        long temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        return arr;
    }

    public char[] swap(char[] arr, int i1, int i2)
    {
        char temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
        return arr;
    }

    public int[] swap_LessMemory(int[] arr, int i1, int i2)
    {
        try {
            Math.addExact(arr[i1],arr[i2]);
            arr[i2] = arr[i1] - arr[i2];
            arr[i1] -= arr[i2];
            return arr;
        }
        catch (ArithmeticException e) {
            return swap(arr, i1, i2);
        }
    }

    public long[] swap_LessMemory(long[] arr, int i1, int i2)
    {
        try {
            Math.addExact(arr[i1],arr[i2]);
            arr[i2] = arr[i1] - arr[i2];
            arr[i1] -= arr[i2];
            return arr;
        }
        catch (ArithmeticException e) {
            return swap(arr, i1, i2);
        }
    }

    private int partition(int[] arr, int iStart, int iEnd)
    {
        int pivot = (iStart+iEnd)/2;
        int low = iStart;
        int high = iEnd;

        while (low <= high)
        {
            while (arr[high] > arr[pivot]) high--;
            while (arr[low] < arr[pivot]) low++;
            if (low <= high) swap(arr, low, high); low++; high--;
        }
        return low;
    }

    /**
     * 	Quicksort algorithm: Worst-Case O(n^2), Average- and Best-Case O(n*log(n))
     * 	NOT FINISHED YET
     *
     * 	@param arr	Array
     * 	@return		Returns arr but sorted
     * */
    public void quickSort(int[] arr, final int iStart, final int iEnd)
    {
        int index = partition(arr, iStart, iEnd);

        if (iStart < index-1) quickSort(arr, iStart, index-1);
        if (index < iEnd) quickSort(arr, index, iEnd);
    }

    // private fun
    private int compare(int[] arr, int old, int pNew, int element)
    {
        if ((element - arr[old]) * (element - arr[old]) < (element - arr[pNew]) * (element - arr[pNew])) return old;
        else return pNew;
    }

    private int compare(float[] arr, int old, int pNew, float element)
    {
        if ((element - arr[old]) * (element - arr[old]) < (element - arr[pNew]) * (element - arr[pNew])) return old;
        else return pNew;
    }

    private int compare(double[] arr, int old, int pNew, double element)
    {
        if ((element - arr[old]) * (element - arr[old]) < (element - arr[pNew]) * (element - arr[pNew])) return old;
        else return pNew;
    }

    private int compare(long[] arr, int old, int pNew, long element)
    {
        if ((element - arr[old]) * (element - arr[old]) < (element - arr[pNew]) * (element - arr[pNew])) return old;
        else return pNew;
    }

    /**
     * 	Method to sort something into an array
     * 	Running time: Worst-Case: O(2n), Best-Case(n)
     *
     * 	@param arr		Array in which the element should be sorted in
     * 	@param element	Item to sort in
     * 	@return 		New array including the old one and the element
     * */
    public int[] sortIntoArr(int[] arr, int element)
    {
        if (element >= arr[arr.length-1])
        {
            return addElementToArray(arr, element);
        }
        if (element <= arr[0])
        {
            int[] newArr = new int[arr.length + 1];
            int counter = 1;
            newArr[0] = element;
            for (int x : arr)
            {
                newArr[counter] = x;
                counter++;
            }
            newArr[counter] = element;
            return newArr;
        }
        else
        {
            int[] newArr = new int[arr.length+1];
            int nearest = 0;

            for (int i = 1; i < arr.length; i++)
            {
                nearest = compare(arr, nearest, i, element);
            }
            int counter = 0;
            for (int i = 0; i < arr.length; i++)
            {
                if (counter == nearest)
                {
                    newArr[i] = element;
                    i--;
                }
                else
                {
                    newArr[counter] = arr[i];
                }
                counter++;
            }
            return newArr;
        }
    }

    /**
     * 	Method to sort something into an array
     * 	Running time: Worst-Case: O(2n), Best-Case(n)
     *
     * 	@param arr		Array in which the element should be sorted in
     * 	@param element	Item to sort in
     * 	@return 		New array including the old one and the element
     * */
    public double[] sortIntoArr(double[] arr, double element)
    {
        if (element >= arr[arr.length-1])
        {
            return addElementToArray(arr, element);
        }
        if (element <= arr[0])
        {
            double[] newArr = new double[arr.length + 1];
            int counter = 1;
            newArr[0] = element;
            for (double x : arr)
            {
                newArr[counter] = x;
                counter++;
            }
            newArr[counter] = element;
            return newArr;
        }
        else
        {
            double[] newArr = new double[arr.length+1];
            int nearest = 0;

            for (int i = 1; i < arr.length; i++)
            {
                nearest = compare(arr, nearest, i, element);
            }
            int counter = 0;
            for (int i = 0; i < arr.length; i++)
            {
                if (counter == nearest)
                {
                    newArr[i] = element;
                    i--;
                }
                else
                {
                    newArr[counter] = arr[i];
                }
                counter++;
            }
            return newArr;
        }
    }

    /**
     * 	Method to sort something into an array
     * 	Running time: Worst-Case: O(2n), Best-Case(n)
     *
     * 	@param arr		Array in which the element should be sorted in
     * 	@param element	Item to sort in
     * 	@return 		New array including the old one and the element
     * */
    public float[] sortIntoArr(float[] arr, float element)
    {
        if (element >= arr[arr.length-1])
        {
            return addElementToArray(arr, element);
        }
        if (element <= arr[0])
        {
            float[] newArr = new float[arr.length + 1];
            int counter = 1;
            newArr[0] = element;
            for (float x : arr)
            {
                newArr[counter] = x;
                counter++;
            }
            newArr[counter] = element;
            return newArr;
        }
        else
        {
            float[] newArr = new float[arr.length+1];
            int nearest = 0;

            for (int i = 1; i < arr.length; i++)
            {
                nearest = compare(arr, nearest, i, element);
            }
            int counter = 0;
            for (int i = 0; i < arr.length; i++)
            {
                if (counter == nearest)
                {
                    newArr[i] = element;
                    i--;
                }
                else
                {
                    newArr[counter] = arr[i];
                }
                counter++;
            }
            return newArr;
        }
    }

    /**
     * 	Method to sort something into an array
     * 	Running time: Worst-Case: O(2n), Best-Case(n)
     *
     * 	@param arr		Array in which the element should be sorted in
     * 	@param element	Item to sort in
     * 	@return 		New array including the old one and the element
     * */
    public long[] sortIntoArr(long[] arr, long element)
    {
        if (element >= arr[arr.length-1])
        {
            return addElementToArray(arr, element);
        }
        if (element <= arr[0])
        {
            long[] newArr = new long[arr.length + 1];
            int counter = 1;
            newArr[0] = element;
            for (long x : arr)
            {
                newArr[counter] = x;
                counter++;
            }
            newArr[counter] = element;
            return newArr;
        }
        else
        {
            long[] newArr = new long[arr.length+1];
            int nearest = 0;

            for (int i = 1; i < arr.length; i++)
            {
                nearest = compare(arr, nearest, i, element);
            }
            int counter = 0;
            for (int i = 0; i < arr.length; i++)
            {
                if (counter == nearest)
                {
                    newArr[i] = element;
                    i--;
                }
                else
                {
                    newArr[counter] = arr[i];
                }
                counter++;
            }
            return newArr;
        }
    }

    /**
     * 	Selection Sort: All cases: O(n^2)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     * */
    public int[] selectionSort(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int min_index = i;
            for (int j = i; j < arr.length; j++)
            {
                if (arr[j] < arr[min_index]) min_index = j;
            }
            swap(arr, i, min_index);
        }
        return arr;
    }

    /**
     * 	Selection Sort: All cases: O(n^2)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     * */
    public long[] selectionSort(long[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int min_index = i;
            for (int j = i; j < arr.length; j++)
            {
                if (arr[j] < arr[min_index]) min_index = j;
            }
            swap(arr, i, min_index);
        }
        return arr;
    }

    /**
     * 	Selection Sort: All cases: O(n^2)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     * */
    public double[] selectionSort(double[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int min_index = i;
            for (int j = i; j < arr.length; j++)
            {
                if (arr[j] < arr[min_index]) min_index = j;
            }
            swap(arr, i, min_index);
        }
        return arr;
    }

    /**
     * 	Selection Sort: All cases: O(n^2)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     * */
    public float[] selectionSort(float[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int min_index = i;
            for (int j = i; j < arr.length; j++)
            {
                if (arr[j] < arr[min_index]) min_index = j;
            }
            swap(arr, i, min_index);
        }
        return arr;
    }

    /**
     * 	Selection Sort: All cases: O(n^2)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     * */
    public char[] selectionSort(char[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int min_index = i;
            for (int j = i; j < arr.length; j++)
            {
                if (arr[j] < arr[min_index]) min_index = j;
            }
            swap(arr, i, min_index);
        }
        return arr;
    }

    /**
     * 	Insertion Sort: Worst-Case: O(n^2), Best-Case: O(n)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     * */
    public static int[] insertionSort(int[] arr){
        int temp;
        int j;
        for (int i = 0; i<arr.length; i++){
            temp = arr[i];
            j = i;
            while (j>0 && arr[j-1] >temp){
                arr[j]=arr[j-1];
                j=j-1;
            }
            arr[j]=temp;
        }
        return arr;
    }


    /**
     * 	Insertion Sort: Worst-Case: O(n^2), Best-Case: O(n)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     * */
    public static long[] insertionSort(long[] arr){
        long temp;
        int j;
        for (int i = 0; i<arr.length; i++){
            temp = arr[i];
            j = i;
            while (j>0 && arr[j-1] >temp){
                arr[j]=arr[j-1];
                j=j-1;
            }
            arr[j]=temp;
        }
        return arr;
    }
    /**
     * 	Insertion Sort: Worst-Case: O(n^2), Best-Case: O(n)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     * */
    public static float[] insertionSort(float[] arr){
        float temp;
        int j;
        for (int i = 0; i<arr.length; i++){
            temp = arr[i];
            j = i;
            while (j>0 && arr[j-1] >temp){
                arr[j]=arr[j-1];
                j=j-1;
            }
            arr[j]=temp;
        }
        return arr;
    }

    /**
     * 	Insertion Sort: Worst-Case: O(n^2), Best-Case: O(n)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     * */
    public static double[] insertionSort(double[] arr){
        double temp;
        int j;
        for (int i = 0; i<arr.length; i++){
            temp = arr[i];
            j = i;
            while (j>0 && arr[j-1] >temp){
                arr[j]=arr[j-1];
                j=j-1;
            }
            arr[j]=temp;
        }
        return arr;
    }

    /**
     * 	Insertion Sort: Worst-Case: O(n^2), Best-Case: O(n)
     *
     * 	@param arr		Array in which should be sorted
     * 	@return 		Returns arr but sorted
     **/
    public static char[] insertionSort(char[] arr){
        char temp;
        int j;
        for (int i = 0; i<arr.length; i++){
            temp = arr[i];
            j = i;
            while (j>0 && arr[j-1] >temp){
                arr[j]=arr[j-1];
                j=j-1;
            }
            arr[j]=temp;
        }
        return arr;
    }

    // Sorting ---------------------------------------------------------------------

    /**
     * 	@param arr1	First array to merge
     * 	@param arr2	Second array to merge
     * 	@return 	New array including arr1 and arr2
     * */
    public Object[] mergeArrays(Object[] arr1, Object[] arr2)
    {
        Object[] retArr = new Object[arr1.length + arr2.length];
        int counter = 0;
        for (Object element : arr1)
        {
            retArr[counter] = element;
            counter++;
        }
        for (Object element : arr2)
        {
            retArr[counter] = element;
            counter++;
        }
        return retArr;
    }

    /**
     * 	@param arr1	First array to merge
     * 	@param arr2	Second array to merge
     * 	@return 	New array including arr1 and arr2
     * */
    public boolean[] mergeArrays(boolean[] arr1, boolean[] arr2)
    {
        boolean[] retArr = new boolean[arr1.length + arr2.length];
        int counter = 0;
        for (boolean element : arr1)
        {
            retArr[counter] = element;
            counter++;
        }
        for (boolean element : arr2)
        {
            retArr[counter] = element;
            counter++;
        }
        return retArr;
    }

    /**
     * 	@param arr1	First array to merge
     * 	@param arr2	Second array to merge
     * 	@return 	New array including arr1 and arr2
     * */
    public char[] mergeArrays(char[] arr1, char[] arr2)
    {
        char[] retArr = new char[arr1.length + arr2.length];
        int counter = 0;
        for (char element : arr1)
        {
            retArr[counter] = element;
            counter++;
        }
        for (char element : arr2)
        {
            retArr[counter] = element;
            counter++;
        }
        return retArr;
    }

    /**
     * 	@param arr1	First array to merge
     * 	@param arr2	Second array to merge
     * 	@return 	New array including arr1 and arr2
     * */
    public String[] mergeArrays(String[] arr1, String[] arr2)
    {
        String[] retArr = new String[arr1.length + arr2.length];
        int counter = 0;
        for (String element : arr1)
        {
            retArr[counter] = element;
            counter++;
        }
        for (String element : arr2)
        {
            retArr[counter] = element;
            counter++;
        }
        return retArr;
    }

    /**
     * 	@param arr1	First array to merge
     * 	@param arr2	Second array to merge
     * 	@return 	New array including arr1 and arr2
     * */
    public int[] mergeArrays(int[] arr1, int[] arr2)
    {
        int[] retArr = new int[arr1.length + arr2.length];
        int counter = 0;
        for (int element : arr1)
        {
            retArr[counter] = element;
            counter++;
        }
        for (int element : arr2)
        {
            retArr[counter] = element;
            counter++;
        }
        return retArr;
    }

    /**
     * 	@param arr1	First array to merge
     * 	@param arr2	Second array to merge
     * 	@return 	New array including arr1 and arr2
     * */
    public long[] mergeArrays(long[] arr1, long[] arr2)
    {
        long[] retArr = new long[arr1.length + arr2.length];
        int counter = 0;
        for (long element : arr1)
        {
            retArr[counter] = element;
            counter++;
        }
        for (long element : arr2)
        {
            retArr[counter] = element;
            counter++;
        }
        return retArr;
    }

    /**
     * 	@param arr1	First array to merge
     * 	@param arr2	Second array to merge
     * 	@return 	New array including arr1 and arr2
     * */
    public float[] mergeArrays(float[] arr1, float[] arr2)
    {
        float[] retArr = new float[arr1.length + arr2.length];
        int counter = 0;
        for (float element : arr1)
        {
            retArr[counter] = element;
            counter++;
        }
        for (float element : arr2)
        {
            retArr[counter] = element;
            counter++;
        }
        return retArr;
    }

    /**
     * 	@param arr1	First array to merge
     * 	@param arr2	Second array to merge
     * 	@return 	New array including arr1 and arr2
     * */
    public double[] mergeArrays(double[] arr1, double[] arr2)
    {
        double[] retArr = new double[arr1.length + arr2.length];
        int counter = 0;
        for (double element : arr1)
        {
            retArr[counter] = element;
            counter++;
        }
        for (double element : arr2)
        {
            retArr[counter] = element;
            counter++;
        }
        return retArr;
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printArr(String[] arr)
    {
        for (String element : arr)
        {
            System.out.print(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printArr(long[] arr)
    {
        for (long element : arr)
        {
            System.out.print(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printArr(int[] arr)
    {
        for (int element : arr)
        {
            System.out.print(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printArr(boolean[] arr)
    {
        for (boolean element : arr)
        {
            System.out.print(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printArr(char[] arr)
    {
        for (char element : arr)
        {
            System.out.print(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printArr(double[] arr)
    {
        for (double element : arr)
        {
            System.out.print(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printArr(float[] arr)
    {
        for (float element : arr)
        {
            System.out.print(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printlnArr(String[] arr)
    {
        for (String element : arr)
        {
            System.out.println(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printlnArr(long[] arr)
    {
        for (long element : arr)
        {
            System.out.println(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printlnArr(int[] arr)
    {
        for (int element : arr)
        {
            System.out.println(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printlnArr(boolean[] arr)
    {
        for (boolean element : arr)
        {
            System.out.println(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printlnArr(char[] arr)
    {
        for (char element : arr)
        {
            System.out.println(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printlnArr(double[] arr)
    {
        for (double element : arr)
        {
            System.out.println(element);
        }
    }

    /**
     * 	Method to print an array due iterating through it
     *
     * 	@param arr	array to print
     * */
    public void printlnArr(float[] arr)
    {
        for (float element : arr)
        {
            System.out.println(element);
        }
    }

    /**
     * 	@param arr		Array where an element should be added
     * 	@param element	Element which should be added
     * 	@return 		New Array containing the old one plus the new element pasted at the end
     * */
    public String[] addElementToArray(String[] arr, String element)
    {
        String[] newArr = new String[arr.length + 1];
        int counter = 0;
        for (String x : arr)
        {
            newArr[counter] = x;
            counter++;
        }
        newArr[counter] = element;
        return newArr;
    }

    /**
     * 	@param arr		Array where an element should be added
     * 	@param element	Element which should be added
     * 	@return 		New Array containing the old one plus the new element pasted at the end
     * */
    public long[] addElementToArray(long[] arr, long element)
    {
        long[] newArr = new long[arr.length + 1];
        int counter = 0;
        for (long x : arr)
        {
            newArr[counter] = x;
            counter++;
        }
        newArr[counter] = element;
        return newArr;
    }

    /**
     * 	@param arr		Array where an element should be added
     * 	@param element	Element which should be added
     * 	@return 		New Array containing the old one plus the new element pasted at the end
     * */
    public int[] addElementToArray(int[] arr, int element)
    {
        int[] newArr = new int[arr.length + 1];
        int counter = 0;
        for (int x : arr)
        {
            newArr[counter] = x;
            counter++;
        }
        newArr[counter] = element;
        return newArr;
    }

    /**
     * 	@param arr		Array where an element should be added
     * 	@param element	Element which should be added
     * 	@return 		New Array containing the old one plus the new element pasted at the end
     * */
    public boolean[] addElementToArray(boolean[] arr, boolean element)
    {
        boolean[] newArr = new boolean[arr.length + 1];
        int counter = 0;
        for (boolean x : arr)
        {
            newArr[counter] = x;
            counter++;
        }
        newArr[counter] = element;
        return newArr;
    }

    /**
     * 	@param arr		Array where an element should be added
     * 	@param element	Element which should be added
     * 	@return 		New Array containing the old one plus the new element pasted at the end
     * */
    public char[] addElementToArray(char[] arr, char element)
    {
        char[] newArr = new char[arr.length + 1];
        int counter = 0;
        for (char x : arr)
        {
            newArr[counter] = x;
            counter++;
        }
        newArr[counter] = element;
        return newArr;
    }

    /**
     * 	@param arr		Array where an element should be added
     * 	@param element	Element which should be added
     * 	@return 		New Array containing the old one plus the new element pasted at the end
     * */
    public float[] addElementToArray(float[] arr, float element)
    {
        float[] newArr = new float[arr.length + 1];
        int counter = 0;
        for (float x : arr)
        {
            newArr[counter] = x;
            counter++;
        }
        newArr[counter] = element;
        return newArr;
    }

    /**
     * 	@param arr		Array where an element should be added
     * 	@param element	Element which should be added
     * 	@return 		New Array containing the old one plus the new element pasted at the end
     * */
    public double[] addElementToArray(double[] arr, double element)
    {
        double[] newArr = new double[arr.length + 1];
        int counter = 0;
        for (double x : arr)
        {
            newArr[counter] = x;
            counter++;
        }
        newArr[counter] = element;
        return newArr;
    }


    /**
     * 	@param arr		Array where space should be added
     * 	@param value	Integer which says how many spaces should be added
     * 	@return 		Old array plus spaces added
     * */
    public String[] addSpaceToArray(String[] arr, int value)
    {
        String[] newArr = new String[arr.length + value];
        int counter = 0;
        for (String element : arr)
        {
            newArr[counter] = element;
            counter++;
        }
        return newArr;
    }

    /**
     * 	@param arr		Array where space should be added
     * 	@param value	Integer which says how many spaces should be added
     * 	@return			Old array plus spaces added
     * */
    public int[] addSpaceToArray(int[] arr, int value)
    {
        int[] newArr = new int[arr.length + value];
        int counter = 0;
        for (int element : arr)
        {
            newArr[counter] = element;
            counter++;
        }
        return newArr;
    }

    /**
     * 	@param arr		Array where space should be added
     * 	@param value	Integer which says how many spaces should be added
     * 	@return			Old array plus spaces added
     * */
    public long[] addSpaceToArray(long[] arr, int value)
    {
        long[] newArr = new long[arr.length + value];
        int counter = 0;
        for (long element : arr)
        {
            newArr[counter] = element;
            counter++;
        }
        return newArr;
    }

    /**
     * 	@param arr		Array where space should be added
     * 	@param value	Integer which says how many spaces should be added
     * 	@return 		Old array plus spaces added
     * */
    public double[] addSpaceToArray(double[] arr, int value)
    {
        double[] newArr = new double[arr.length + value];
        int counter = 0;
        for (double element : arr)
        {
            newArr[counter] = element;
            counter++;
        }
        return newArr;
    }

    /**
     * 	@param arr		Array where space should be added
     * 	@param value	Integer which says how many spaces should be added
     * 	@return 		Old array plus spaces added
     * */
    public float[] addSpaceToArray(float[] arr, int value)
    {
        float[] newArr = new float[arr.length + value];
        int counter = 0;
        for (float element : arr)
        {
            newArr[counter] = element;
            counter++;
        }
        return newArr;
    }

    /**
     * 	Method to convert an array to a string. Different elements are divided though ;;
     *
     * 	@param arr	Array which should be converted
     * 	@return		Returns a string including all elements from arr
     * */
    public String toString(String[] arr)
    {
        String newStr = arr[0];
        try
        {
            for (int i = 1; i < arr.length; i++)
            {
                newStr = newStr + ";;" + arr[i];
            }
        }
        catch (Exception e)
        {}
        return newStr;
    }

    /**
     * 	Method to convert an array to a string. Different elements are divided though ;;
     *
     * 	@param arr	Array which should be converted
     * 	@return		returns a string including all elements from arr
     * */
    public String toString(int[] arr)
    {
        String newStr = Integer.toString(arr[0]);
        try
        {
            for (int i = 1; i < arr.length; i++)
            {
                newStr = newStr + ";;" + arr[i];
            }
        }
        catch (Exception e)
        {}
        return newStr;
    }

    /**
     * 	Method to convert an array to a string. Different elements are divided though ;;
     *
     * 	@param arr	Array which should be converted
     * 	@return		returns a string including all elements from arr
     * */
    public String toString(long[] arr)
    {
        String newStr = Long.toString(arr[0]);
        try
        {
            for (int i = 1; i < arr.length; i++)
            {
                newStr = newStr + ";;" + Long.toString(arr[i]);
            }
        }
        catch (Exception e)
        {}
        return newStr;
    }

    /**
     *
     * 	@param arr		Array where something should be removed
     * 	@param index	Index of the element which should be removed
     * 	@return			New Array containing the old except the element of Index
     * */
    public int[] remove(int[] arr, int index)
    {
        int[] newArr = new int[arr.length-1];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i == index) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr			Array where something should be removed
     * 	@param indexStart	Index of the first element to remove
     * 	@param indexEnd		Index of the last element to remove
     * 	@return				New Array containing the old except the elements between indexStart (not included) and indexEnd (not included)
     * */
    public int[] remove(int[] arr, int indexStart, int indexEnd)
    {
        if ((indexEnd-indexStart-1) < 0) return arr;
        int[] newArr = new int[arr.length-(indexEnd-indexStart-1)];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i > indexStart && i < indexEnd) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr		Array where something should be removed
     * 	@param index	Index of the element which should be removed
     * 	@return			New Array containing the old except the element of Index
     * */
    public long[] remove(long[] arr, int index)
    {
        long[] newArr = new long[arr.length-1];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i == index) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr			Array where something should be removed
     * 	@param indexStart	Index of the first element to remove
     * 	@param indexEnd		Index of the last element to remove
     * 	@return				New Array containing the old except the elements between indexStart (not included) and indexEnd (not included)
     * */
    public long[] remove(long[] arr, int indexStart, int indexEnd)
    {
        if ((indexEnd-indexStart-1) < 0) return arr;
        long[] newArr = new long[arr.length-(indexEnd-indexStart-1)];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i > indexStart && i < indexEnd) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr		Array where something should be removed
     * 	@param index	Index of the element which should be removed
     * 	@return			New Array containing the old except the element of Index
     * */
    public String[] remove(String[] arr, int index)
    {
        String[] newArr = new String[arr.length-1];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i == index) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr			Array where something should be removed
     * 	@param indexStart	Index of the first element to remove
     * 	@param indexEnd		Index of the last element to remove
     * 	@return				New Array containing the old except the elements between indexStart (not included) and indexEnd (not included)
     * */
    public String[] remove(String[] arr, int indexStart, int indexEnd)
    {
        if ((indexEnd-indexStart-1) < 0) return arr;
        String[] newArr = new String[arr.length-(indexEnd-indexStart-1)];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i > indexStart && i < indexEnd) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr		Array where something should be removed
     * 	@param index	Index of the element which should be removed
     * 	@return			New Array containing the old except the element of Index
     * */
    public boolean[] remove(boolean[] arr, int index)
    {
        boolean[] newArr = new boolean[arr.length-1];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i == index) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr			Array where something should be removed
     * 	@param indexStart	Index of the first element to remove
     * 	@param indexEnd		Index of the last element to remove
     * 	@return				New Array containing the old except the elements between indexStart (not included) and indexEnd (not included)
     * */
    public boolean[] remove(boolean[] arr, int indexStart, int indexEnd)
    {
        if ((indexEnd-indexStart-1) < 0) return arr;
        boolean[] newArr = new boolean[arr.length-(indexEnd-indexStart-1)];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i > indexStart && i < indexEnd) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr		Array where something should be removed
     * 	@param index	Index of the element which should be removed
     * 	@return			New Array containing the old except the element of Index
     * */
    public char[] remove(char[] arr, int index)
    {
        char[] newArr = new char[arr.length-1];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i == index) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr			Array where something should be removed
     * 	@param indexStart	Index of the first element to remove
     * 	@param indexEnd		Index of the last element to remove
     * 	@return				New Array containing the old except the elements between indexStart (not included) and indexEnd (not included)
     * */
    public char[] remove(char[] arr, int indexStart, int indexEnd)
    {
        if ((indexEnd-indexStart-1) < 0) return arr;
        char[] newArr = new char[arr.length-(indexEnd-indexStart-1)];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i > indexStart && i < indexEnd) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }


    /**
     *
     * 	@param arr		Array where something should be removed
     * 	@param index	Index of the element which should be removed
     * 	@return			New Array containing the old except the element of Index
     * */
    public float[] remove(float[] arr, int index)
    {
        float[] newArr = new float[arr.length-1];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i == index) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr			Array where something should be removed
     * 	@param indexStart	Index of the first element to remove
     * 	@param indexEnd		Index of the last element to remove
     * 	@return				New Array containing the old except the elements between indexStart (not included) and indexEnd (not included)
     * */
    public float[] remove(float[] arr, int indexStart, int indexEnd)
    {
        if ((indexEnd-indexStart-1) < 0) return arr;
        float[] newArr = new float[arr.length-(indexEnd-indexStart-1)];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i > indexStart && i < indexEnd) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }


    /**
     *
     * 	@param arr		Array where something should be removed
     * 	@param index	Index of the element which should be removed
     * 	@return			New Array containing the old except the element of Index
     * */
    public double[] remove(double[] arr, int index)
    {
        double[] newArr = new double[arr.length-1];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i == index) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }

    /**
     *
     * 	@param arr			Array where something should be removed
     * 	@param indexStart	Index of the first element to remove
     * 	@param indexEnd		Index of the last element to remove
     * 	@return				New Array containing the old except the elements between indexStart (not included) and indexEnd (not included)
     * */
    public double[] remove(double[] arr, int indexStart, int indexEnd)
    {
        if ((indexEnd-indexStart-1) < 0) return arr;
        double[] newArr = new double[arr.length-(indexEnd-indexStart-1)];
        int counter = 0;
        for (int i = 0; i < arr.length; i++)
        {
            try
            {
                if (i > indexStart && i < indexEnd) continue;
                newArr[counter] = arr[i];
                counter++;
            }
            catch (Exception e) {}
        }
        return newArr;
    }
}

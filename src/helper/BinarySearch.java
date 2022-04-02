package helper;

public class BinarySearch {
    public int binarySearch(String[] arr, int checkedMarginIndex, int elementCount, String searchingNum)
    {
        if (elementCount >= checkedMarginIndex) {
            int mid = checkedMarginIndex + (elementCount - checkedMarginIndex) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid].equalsIgnoreCase(searchingNum)){
                return mid;
            }
            // If element is smaller than mid, then
            // it can only be present in left subarray
//            if (arr[mid] > searchingNum)
            if (arr[mid].compareTo(searchingNum) > 0) {
                return binarySearch(arr, checkedMarginIndex, mid - 1, searchingNum);
            }
            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, elementCount, searchingNum);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }
}

import java.util.*;

public class AlgorithmsDataStructures2
{

    public static int[] GenerateBBSTArray(int[] a)
    {
        if (a == null || a.length == 0) return a;

        int arraySize = a.length;
        int[] emptyArrayForBST = new int[arraySize];
        int[] sortedArray = sortInitialArray(a);
        return fillBSTSubtree(emptyArrayForBST, 0, sortedArray, 0, sortedArray.length-1);
    }

    
    private static int[] fillBSTSubtree(int[] emptyArrayForBST, int currentIndexToFill, int[] sortedArray, int sortedSubarrayStart, int sortedSubarrayEnd) {
        int middleIndex = calculateMiddleIndex(sortedSubarrayStart, sortedSubarrayEnd);
        emptyArrayForBST[currentIndexToFill] = sortedArray[middleIndex];

        if (sortedSubarrayStart == sortedSubarrayEnd) return emptyArrayForBST;

        fillBSTSubtree(emptyArrayForBST, currentIndexToFill*2+1, sortedArray, sortedSubarrayStart, middleIndex-1);
        fillBSTSubtree(emptyArrayForBST, currentIndexToFill*2+2, sortedArray, middleIndex+1, sortedSubarrayEnd);

        return emptyArrayForBST;
    }


    public static int[] sortInitialArray(int[] array) {
        Arrays.sort(array);
        return array;
    }


    public static int calculateMiddleIndex(int start, int end) {
        int delta = (end - start) / 2;
        return start + delta;
    }

}

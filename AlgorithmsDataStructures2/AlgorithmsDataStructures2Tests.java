import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AlgorithmsDataStructures2Tests {

    @Test
    void generateEmptyArray() {
        int[] testArray = new int[]{};
        int[] expected = new int[]{};
        int[] result = AlgorithmsDataStructures2.GenerateBBSTArray(testArray);
        assertArrayEquals(expected, result);
    }

    @Test
    void generateArrayOneNode() {
        int[] testArray = new int[]{1};
        int[] expected = new int[]{1};
        int[] result = AlgorithmsDataStructures2.GenerateBBSTArray(testArray);
        assertArrayEquals(expected, result);
    }

    @Test
    void generateArrayTwoLevels() {
        int[] testArray = new int[]{1,2,3};
        int[] expected = new int[]{2,1,3};
        int[] result = AlgorithmsDataStructures2.GenerateBBSTArray(testArray);
        assertArrayEquals(expected, result);
    }

    @Test
    public void generateBBSTArray_Not_Sorted(){
        int[] testArray = new int[]{7,5,6};
        int[] expected = new int[]{6,5,7};
        int[] result = AlgorithmsDataStructures2.GenerateBBSTArray(testArray);
        assertArrayEquals(expected, result);
    }

    @Test
    public void generateBBSTArray() {
        int[] testArray = new int[]{1,2,3,4,5,6,7};
        int[] expected = new int[]{4,2,6,1,3,5,7};
        int[] result = AlgorithmsDataStructures2.GenerateBBSTArray(testArray);
        assertArrayEquals(expected, result);
    }


}

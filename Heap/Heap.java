import java.util.*;

class Heap
{

    private final static int NO_VALUE = -1;
    private final static int MAX_CHILDREN = 2;
    private int freeSlot;
    public int [] HeapArray;

    public Heap() { HeapArray = null; }



    public void MakeHeap(int[] a, int depth)
    {
        if (a == null) return;

        int arraySize = (int) (Math.pow(MAX_CHILDREN, depth+1) - 1);
        HeapArray = new int[arraySize];

        for (int i = 0; i < HeapArray.length; i++) HeapArray[i] = NO_VALUE;
        freeSlot = 0;

        for (int i = 0; i < a.length; i++) Add(a[i]);
    }



    public int GetMax()
    {
        int result = pullMaxValue();
        reorganizeHeap();

        return result;
    }

    private int pullMaxValue() {
        int maxValue = HeapArray[0];

        if (maxValue == -1) return -1;

        int indexToFreeUp = getMinimumIndex();
        if (indexToFreeUp == 0) {
            removeKey(indexToFreeUp);
            freeSlot -= 1;
            return maxValue;
        }

        int replacingKey = HeapArray[indexToFreeUp];
        HeapArray[0] = replacingKey;
        removeKey(indexToFreeUp);

        return maxValue;
    }

      private void removeKey(int index) {
        HeapArray[index] = NO_VALUE;
        if (freeSlot > 0) freeSlot -= 1;
    }

    private void reorganizeHeap() {
        int runningIndex = 0;
        int runningValue = HeapArray[runningIndex];

        int biggestChildIndex = getBiggestChildIndex(runningIndex);
        int biggestChildValue = HeapArray[biggestChildIndex];

        for (;runningValue < biggestChildValue;) {
            swapKeys(runningIndex, biggestChildIndex);
            runningIndex = biggestChildIndex;
            biggestChildIndex = getBiggestChildIndex(runningIndex);
            biggestChildValue = HeapArray[biggestChildIndex];
        }
    }


  
    public boolean Add(int key)
    {
        if (freeSlot > (HeapArray.length-1)) return false;

        HeapArray[freeSlot] = key;
        if (freeSlot == 0) {
            freeSlot+=1;
            return true;
        }

        int parentIndex = (freeSlot-1)/MAX_CHILDREN;
        int parentValue = HeapArray[parentIndex];
        int childIndex = freeSlot;
        int childValue = key;

        for (;parentValue < childValue;) {
            int temp = parentValue;
            HeapArray[parentIndex] = childValue;
            HeapArray[childIndex] = temp;
            parentIndex = (parentIndex-1)/MAX_CHILDREN;
            parentValue = HeapArray[parentIndex];
        }

        freeSlot += 1;
        return true;
    }

    private int getMinimumIndex() {
        int minimumIndex = freeSlot-1;
        if (minimumIndex >= 0) return minimumIndex;

        return 0;
    }

    private void swapKeys(int firstKeyIndex, int otherKeyIndex) {
        int temp = HeapArray[firstKeyIndex];
        HeapArray[firstKeyIndex] = HeapArray[otherKeyIndex];
        HeapArray[otherKeyIndex] = temp;
    }

    private int getBiggestChildIndex(int parentIndex) {

        int leftChildIndex = getChildIndex(parentIndex, false);
        int rightChildIndex = getChildIndex(parentIndex, true);

        boolean thereIsOnlyOneChild = leftChildIndex == -1 ^ rightChildIndex == -1;
        boolean noChildren = leftChildIndex == rightChildIndex;

        if (noChildren) return parentIndex;
        if (thereIsOnlyOneChild && leftChildIndex != -1) return leftChildIndex;
        if (thereIsOnlyOneChild && rightChildIndex != -1) return rightChildIndex;


        return HeapArray[leftChildIndex] > HeapArray[rightChildIndex] ? leftChildIndex : rightChildIndex;
    }

    public int getChildIndex(int parentIndex, boolean getRight) {

        int result = NO_VALUE;

        if (getRight) {
            result = MAX_CHILDREN*parentIndex+2;
        }
        if (!getRight) {
            result = MAX_CHILDREN*parentIndex+1;
        }

        if (result < HeapArray.length) return result;

        return NO_VALUE;
    }
}














import java.util.*;

class aBST
{
    public Integer Tree [];

    public aBST(int depth)
    {
        int tree_size = (2 << depth) - 1;
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }




   public Integer FindKeyIndex(int key)
    {
        if (Tree[0] == null) return 0;

        int currentIndex = 0;

        for (;Tree[currentIndex] != key && getRightBranch(currentIndex) < Tree.length;)  {
            if (key < Tree[currentIndex]) {
                int leftIndex = getLeftBranch(currentIndex);

                if (Tree[leftIndex] == null) return -leftIndex;

                currentIndex = leftIndex;
            }

            if (key > Tree[currentIndex]) {
                int rightIndex = getRightBranch(currentIndex);

                if (Tree[rightIndex] == null) return -rightIndex;

                currentIndex = rightIndex;
            }
        }

        return Tree[currentIndex] == key ? currentIndex : null;
    }




    private int getLeftBranch(int givenIndex) {return givenIndex*2+1;}

    private int getRightBranch(int givenIndex) {return givenIndex*2+2;}





    public int AddKey(int key)
    {
        if (Tree[0] == null) {
            Tree[0] = key;
            return 0;
        }

        int currentIndex = 0;

        for (;getRightBranch(currentIndex) < Tree.length;)  {
            if (key < Tree[currentIndex]) {
                int leftIndex = getLeftBranch(currentIndex);

                if (Tree[leftIndex] == null) {
                    Tree[leftIndex] = key;
                    return leftIndex;
                }
                currentIndex = leftIndex;
            }

            if (Tree[currentIndex] == key) return currentIndex;

            if (key > Tree[currentIndex]) {
                int rightIndex = getRightBranch(currentIndex);

                if (Tree[rightIndex] == null) {
                    Tree[rightIndex] = key;
                    return rightIndex;
                }

                currentIndex = rightIndex;
            }
        }
        return -1;
    }
}











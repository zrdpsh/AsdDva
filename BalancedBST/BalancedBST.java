import java.util.*;


class BSTNode
{
    public int NodeKey; 
    public BSTNode Parent; 
    public BSTNode LeftChild; 
    public BSTNode RightChild; 	
    public int Level; 

    
    public BSTNode(int key, BSTNode parent)
    {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}



class BalancedBST
{
    public BSTNode Root;

    public BalancedBST()
    {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        if (a == null || a.length == 0) {
            Root = null;
            return;
        }

        int arraySize = a.length;
        int[] sortedArray = a;
        Arrays.sort(sortedArray);

        recurOverArray(sortedArray, 0, arraySize - 1, null, 0);

    }

    
    

    public BSTNode recurOverArray(int[] sortedArray, int sortedSubarrayStart, int sortedSubarrayEnd, BSTNode parent, int nodeLevel) {
        int middleIndex = calculateMiddleIndex(sortedSubarrayStart, sortedSubarrayEnd);

        BSTNode currentNode = new BSTNode(sortedArray[middleIndex], parent);
        currentNode.Level = nodeLevel;
        if (nodeLevel == 0) Root = currentNode;

        if (middleIndex > sortedSubarrayStart) {
            if (nodeLevel == 0) Root.LeftChild = recurOverArray(sortedArray, sortedSubarrayStart, middleIndex-1, Root, nodeLevel+1);
            if (nodeLevel != 0) currentNode.LeftChild = recurOverArray(sortedArray, sortedSubarrayStart, middleIndex-1, currentNode, nodeLevel+1);
        }

        if (middleIndex < sortedSubarrayEnd) {
            if (nodeLevel == 0) Root.RightChild = recurOverArray(sortedArray, middleIndex+1, sortedSubarrayEnd, Root, nodeLevel+1);
            if (nodeLevel != 0) currentNode.RightChild = recurOverArray(sortedArray, middleIndex+1, sortedSubarrayEnd,  currentNode, nodeLevel+1);
        }


        return currentNode;
    }

    

    public boolean IsBalanced(BSTNode root_node) {
        if(root_node == null){
            return true;
        }
        int maxDepthLeftBranch = getMaxDepth(root_node.LeftChild, root_node.Level);
        int maxDepthRightBranch = getMaxDepth(root_node.RightChild, root_node.Level);
        boolean currentIsBalanced = Math.abs(maxDepthRightBranch - maxDepthLeftBranch) <= 1;
        
        boolean isBalancedLeftChild = IsBalanced(root_node.LeftChild);
        boolean isBalancedRightChild = IsBalanced(root_node.RightChild);
        return currentIsBalanced && isBalancedLeftChild && isBalancedRightChild;
    }

    

    public int getMaxDepth(BSTNode rootOfSubtree, int parentLevel){
        if(rootOfSubtree == null){
            return parentLevel;
        }
        int maxDepthLeftBranch = getMaxDepth(rootOfSubtree.LeftChild, rootOfSubtree.Level);
        int maxDepthRightBranch = getMaxDepth(rootOfSubtree.RightChild, rootOfSubtree.Level);
        return Math.max(maxDepthRightBranch, maxDepthLeftBranch);
    }
    
    

    public static int calculateMiddleIndex(int start, int end) {
        int delta = (end - start) / 2;
        return start + delta;
    }
} 















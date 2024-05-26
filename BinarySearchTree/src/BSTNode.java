import java.io.*;
import java.util.*;


class BSTNode<T>
{
    public int NodeKey;
    public T NodeValue;
    public BSTNode<T> Parent;
    public BSTNode<T> LeftChild;
    public BSTNode<T> RightChild;

    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }

    public int getKey() {return this.NodeKey;}
    public T getNodeValue() { return NodeValue;}
    public BSTNode<T> getParent() {return Parent;}
    public BSTNode<T> getRightChild() {return RightChild;}
    public BSTNode<T> getLeftChild() {return LeftChild;}

    public boolean hasLeftChild() {return LeftChild != null;}
    public boolean hasRightChild() {return RightChild != null;}
}







class BSTFind<T>
{
    public BSTNode<T> Node; // parentNode to add it

    public boolean NodeHasKey;

    public boolean ToLeft;

    public BSTFind() { Node = null; }
    public BSTFind(BSTNode<T> parentNode, boolean nodeHasKey, boolean addToLeft) {
        this.Node = parentNode;
        this.NodeHasKey = nodeHasKey;
        this.ToLeft = addToLeft;
    }
}






class BST<T>
{
    BSTNode<T> Root;
    private int size = 0;

    public BST(BSTNode<T> node)
    {
        Root = node;
        if (Root != null) {
            size = 1;
        }
    }

    public BSTNode<T> getRoot() {return Root;}




    public BSTFind<T> FindNodeByKey(int key)
    {
        if (Root == null) {
            return new BSTFind(null, false, false);
        }

        return FindFromGivenNode(Root, key);
    }

    private BSTFind<T> FindFromGivenNode(BSTNode<T> currentRoot, int key) {

        if (key == currentRoot.getKey()) {
            return new BSTFind(currentRoot, true, false);
        }


        if (key < currentRoot.getKey() && currentRoot.getLeftChild() != null) {
            return FindFromGivenNode(currentRoot.getLeftChild(), key);
        }
        if (key < currentRoot.getKey() && currentRoot.getLeftChild() == null) {
            return new BSTFind(currentRoot, false, true);
        }


        if (key > currentRoot.getKey() && currentRoot.getRightChild() != null) {
            return FindFromGivenNode(currentRoot.getRightChild(), key);
        }

        return new BSTFind(currentRoot, false, false);
    }





    public boolean AddKeyValue(int key, T val)
    {
        BSTFind<T> searchResult = FindNodeByKey(key);

        BSTNode<T> targetNodeInTree = searchResult.Node;
        if (targetNodeInTree == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }



        if (searchResult.NodeHasKey) return false;

        addNewChild(searchResult, key, val);
        size+=1;
        return true;
    }

    private void addNewChild(BSTFind<T>  searchResult, int key, T val) {
        if (searchResult.ToLeft) {
            searchResult.Node.LeftChild = new BSTNode(key, val, searchResult.Node);
            return;
        }
        searchResult.Node.RightChild = new BSTNode(key, val, searchResult.Node);
    }





    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        if (FromNode == null) return null;

        if (FindMax) return FindMax(FromNode);
        return FindMin(FromNode);
    }

    private BSTNode<T> FindMax(BSTNode<T> node) {
        if (node.getRightChild() != null) return FindMax(node.getRightChild());
        return node;
    }

    private BSTNode<T> FindMin(BSTNode<T> node) {
        if (node.getLeftChild() != null) return FindMin(node.getLeftChild());
        return node;
    }





    public boolean DeleteNodeByKey(int key)
    {
        BSTFind<T> findResult = FindNodeByKey(key);
        if (!findResult.NodeHasKey) {
            return false;
        }
        return deleteNode(findResult);
    }

    public boolean deleteNode(BSTFind<T> findResult) {
        BSTNode<T> nodeToDelete = findResult.Node;

        if (nodeToDelete.equals(Root) && isLeaf(Root)) {
            Root = null;
            return true;
        }

        if (isLeaf(nodeToDelete)) {
            deleteLeaf(nodeToDelete);
            return true;
        }

        boolean haveOnlyOneChild = nodeToDelete.hasLeftChild() ^ nodeToDelete.hasRightChild();


        if (haveOnlyOneChild) {
            BSTNode<T> replacingNode = null;
            replacingNode = (nodeToDelete.hasLeftChild()) ? nodeToDelete.getLeftChild() :  nodeToDelete.getRightChild();
            replaceNode(nodeToDelete, replacingNode);
            return true;
        }

        deleteNodeWithBothChildren(nodeToDelete);
        return true;
    }

    public void deleteNodeWithBothChildren(BSTNode<T> toDelete) {
        BSTNode<T> minChild = FindMin(toDelete.RightChild);


        BSTNode<T> rightChild = toDelete.RightChild;
        BSTNode<T> leftChild = toDelete.LeftChild;
        
        if (isLeaf(minChild)) {
            deleteLeaf(minChild);
        } else {
            replaceNode(minChild, minChild.RightChild);
        }

        if (toDelete.Parent == null) {
            minChild.Parent = null;
        } else {
            replaceNode(toDelete, minChild);
        }

        setChildren(minChild, leftChild, rightChild);
    }

    public boolean isLeaf(BSTNode<T> node) {
        return node.LeftChild == null && node.RightChild == null;
    }


    public void setChildren(BSTNode<T> newParent, BSTNode<T> leftChild, BSTNode<T> rightChild) {
        boolean isNotRecursiveParentRightChild = !newParent.equals(rightChild);
        if (isNotRecursiveParentRightChild) {
            newParent.RightChild = rightChild;
        }
        boolean isNotRecursiveParentLeftChild = !newParent.equals(leftChild);
        if (isNotRecursiveParentLeftChild) {
            newParent.LeftChild = leftChild;
        }
        rightChild.Parent = newParent;
        leftChild.Parent = newParent;
    }


    public void replaceNode(BSTNode<T> toReplace, BSTNode<T> replacer) {
        boolean isRoot = toReplace.equals(Root);
        boolean isLeftChild = toReplace.NodeKey < toReplace.Parent.NodeKey;
        if (isLeftChild) {
            toReplace.Parent.LeftChild = replacer;
        } else {
            toReplace.Parent.RightChild = replacer;
        }
        replacer.Parent = toReplace.Parent;
        if (isRoot) {
            Root = replacer;
        }
        toReplace.Parent = null;
    }

    public void deleteLeaf(BSTNode<T> toDelete) {
        boolean isLeftChild = toDelete.NodeKey < toDelete.Parent.NodeKey;
        if (isLeftChild) {
            toDelete.Parent.LeftChild = null;
        } else {
            toDelete.Parent.RightChild = null;
        }
        toDelete.Parent = null;
    }



    public int Count()
    {
        return countNodes(Root);
    }

    private int countNodes(BSTNode<T> node) {
        if (node == null) return 0;
        return 1 + countNodes(node.getLeftChild()) + countNodes(node.getRightChild());
    }





    
    public ArrayList<BSTNode> WideAllNodes() {
        Queue<BSTNode> q = new LinkedList<BSTNode>();
        ArrayList<BSTNode> result = new ArrayList<>();
        q.add(getRoot());

        while (!q.isEmpty()) {
            BSTNode currentNode = q.poll();
            result.add(currentNode);

            if (currentNode.getLeftChild() != null) q.add(currentNode.getLeftChild());
            if (currentNode.getRightChild() != null) q.add(currentNode.getRightChild());
        }

        return result;
    }





    

    public ArrayList<BSTNode> DeepAllNodes(int searchType) {
        ArrayList<BSTNode> result = new ArrayList<>();

        if (searchType == 0) preorder(result, getRoot());
        if (searchType == 1) inorder(result, getRoot());
        if (searchType == 2) postorder(result, getRoot());

        return result;
    }

    private void preorder(ArrayList<BSTNode> accumulator, BSTNode currentNode) {
        if (currentNode == null) return;

        accumulator.add(currentNode);

        preorder(accumulator, currentNode.getLeftChild());
        preorder(accumulator, currentNode.getRightChild());
    }

    private void inorder(ArrayList<BSTNode> accumulator, BSTNode currentNode) {
        if (currentNode == null) return;

        inorder(accumulator, currentNode.getLeftChild());
        accumulator.add(currentNode);
        inorder(accumulator, currentNode.getRightChild());
    }

    private void postorder(ArrayList<BSTNode> accumulator, BSTNode currentNode) {
        if (currentNode == null) return;

        postorder(accumulator, currentNode.getLeftChild());
        postorder(accumulator, currentNode.getRightChild());
        accumulator.add(currentNode);

    }

}












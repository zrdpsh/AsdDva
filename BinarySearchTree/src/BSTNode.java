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
    public BSTNode<T> getRightChild() {return RightChild;}
    public BSTNode<T> getLeftChild() {return LeftChild;}
}






class BSTFind<T>
{
    public BSTNode<T> Node;

    public boolean NodeHasKey;

    public boolean ToLeft;

    public BSTFind() { Node = null; }
    public BSTFind(BSTNode<T> givenNode, boolean nodeHasKey, boolean addToLeft) {
        this.Node = givenNode;
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


        if (key > currentRoot.getKey() && currentRoot.getLeftChild() != null) {
            return FindFromGivenNode(currentRoot.getRightChild(), key);
        }

        return new BSTFind(currentRoot, false, false);
    }





    public boolean AddKeyValue(int key, T val)
    {
        BSTFind<T> searchResult= FindNodeByKey(key);

        BSTNode<T> targetNodeInTree = searchResult.Node;
        if (targetNodeInTree == null) {
            Root = new BSTNode<>(key, val, null);
        }

        if (searchResult.NodeHasKey) return false;

        addNewNode(searchResult, key, val);
        size+=1;
        return true;
    }

    private void addNewNode(BSTFind<T>  searchResult, int key, T val) {
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

        BSTNode toDelete = findResult.Node;


        if (isLeaf(toDelete)) {
            boolean isRoot = toDelete.equals(Root);
            if (isRoot) {
                Root = null;
                return true;
            }

            deleteLeaf(toDelete);
            return true;
        }

        if (toDelete.LeftChild != null && toDelete.RightChild == null) {
            replaceNode(toDelete, toDelete.LeftChild);
            return true;
        }

        if (toDelete.LeftChild == null && toDelete.RightChild != null) {
            replaceNode(toDelete, toDelete.RightChild);
            return true;
        }


        deleteNodeWithBothChildren(toDelete);
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

}












import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.*;

public class BSTNodeTests {

    @Test
    void FindExistingKey() {
        BST<Integer> emptyTree = new BST<Integer>(null);
        emptyTree.AddKeyValue(21, 71);
        Assertions.assertTrue(emptyTree.FindNodeByKey(21).NodeHasKey);

    }

    
    @Test
    void findNothingAndAddToLeft() {
        BST<Integer> emptyTree = new BST<Integer>(null);

        emptyTree.AddKeyValue(21, 71);

        BSTFind<Integer> smallerNode = emptyTree.FindNodeByKey(10);

        Assertions.assertTrue(smallerNode.ToLeft);
    }

    @Test
    void findNothingAndAddToRight() {
        BST<Integer> emptyTree = new BST<Integer>(null);

        emptyTree.AddKeyValue(21, 71);

        BSTFind<Integer> greaterNode = emptyTree.FindNodeByKey(31);

        Assertions.assertFalse(greaterNode.ToLeft);
    }



    @Test
    void addKeyValueToEmptyTree() {

        BST<Integer> emptyTree = new BST<Integer>(null);
        Assertions.assertTrue(!emptyTree.FindNodeByKey(5).NodeHasKey);

        emptyTree.AddKeyValue(21, 71);

        Assertions.assertTrue(emptyTree.getRoot().getKey() == 21);
    }

    @Test
    void addKeyValueToRoot() {
        BST<Integer> emptyTree = new BST<Integer>(null);

        emptyTree.AddKeyValue(21, 71);

        Assertions.assertTrue(!emptyTree.FindNodeByKey(10).NodeHasKey);

        emptyTree.AddKeyValue(10, 71);

        Assertions.assertTrue(emptyTree.getRoot().getLeftChild().getKey() == 10);
    }

    @Test
    void addKeyValueToRight() {
        BST<Integer> emptyTree = new BST<Integer>(null);

        emptyTree.AddKeyValue(21, 71);

        Assertions.assertTrue(!emptyTree.FindNodeByKey(30).NodeHasKey);

        emptyTree.AddKeyValue(30, 71);

        Assertions.assertTrue(emptyTree.getRoot().getRightChild().getKey() == 30);
    }

    @Test
    void addToRightWithPresentLeft() {
        BST<Integer> emptyTree = new BST<Integer>(null);

        emptyTree.AddKeyValue(21, 71);
        emptyTree.AddKeyValue(10, 71);

        Assertions.assertTrue(!emptyTree.FindNodeByKey(30).NodeHasKey);

        emptyTree.AddKeyValue(30, 71);

        Assertions.assertTrue(emptyTree.getRoot().getRightChild().getKey() == 30);
    }

    @Test
    void addExistingValue() {
        BST<Integer> emptyTree = new BST<Integer>(null);

        emptyTree.AddKeyValue(21, 71);
        emptyTree.AddKeyValue(10, 71);
        emptyTree.AddKeyValue(30, 71);

        Assertions.assertTrue(emptyTree.FindNodeByKey(30).NodeHasKey);
        Assertions.assertTrue(!emptyTree.AddKeyValue(30, 508));
    }

    @Test
    void FindMinAndMaxFromRoot() {
        BST<Integer> minMaxTree = new BST<Integer>(null);

        minMaxTree.AddKeyValue(21, 71);
        minMaxTree.AddKeyValue(10, 71);
        minMaxTree.AddKeyValue(13, 71);
        minMaxTree.AddKeyValue(7, 71);
        minMaxTree.AddKeyValue(30, 71);
        minMaxTree.AddKeyValue(25, 71);
        minMaxTree.AddKeyValue(37, 71);

        Assertions.assertTrue(minMaxTree.FinMinMax(minMaxTree.Root, false).getKey() == 7);
        Assertions.assertTrue(minMaxTree.FinMinMax(minMaxTree.Root, true).getKey() == 37);
    }

    @Test
    void FindMaxFromSubtree() {
        BST<Integer> minMaxSubTree = new BST<>(null);

        minMaxSubTree.AddKeyValue(21, 71);
        minMaxSubTree.AddKeyValue(10, 71);
        minMaxSubTree.AddKeyValue(13, 71);
        minMaxSubTree.AddKeyValue(7, 71);
        minMaxSubTree.AddKeyValue(30, 71);
        minMaxSubTree.AddKeyValue(25, 71);
        minMaxSubTree.AddKeyValue(37, 71);

        BSTNode<Integer> subTreeRoot = minMaxSubTree.Root.getLeftChild();

        Assertions.assertTrue(minMaxSubTree.FinMinMax(subTreeRoot, false).getKey() == 7);
        Assertions.assertTrue(minMaxSubTree.FinMinMax(subTreeRoot, true).getKey() == 13);
    }

    @Test
    void DeleteNode() {
        BST<Integer> deleteTree = new BST<>(null);

        deleteTree.AddKeyValue(21, 71);
        deleteTree.AddKeyValue(10, 71);
        deleteTree.AddKeyValue(13, 71);
        deleteTree.AddKeyValue(7, 71);
        deleteTree.AddKeyValue(5, 71);
        deleteTree.AddKeyValue(9, 71);
        deleteTree.AddKeyValue(30, 71);
        deleteTree.AddKeyValue(25, 71);
        deleteTree.AddKeyValue(37, 71);

        Assertions.assertTrue(deleteTree.FindNodeByKey(7).NodeHasKey);

        deleteTree.DeleteNodeByKey(7);

        Assertions.assertFalse(deleteTree.FindNodeByKey(7).NodeHasKey);
        BSTNode<Integer> replacedNode = deleteTree.FindNodeByKey(10).Node.getLeftChild();
        Assertions.assertTrue(replacedNode.getKey() == 9);
        Assertions.assertTrue(replacedNode.getLeftChild().getKey() == 5);
        Assertions.assertTrue(replacedNode.getRightChild() == null);
    }

    @Test
    void WideAllNodesBasic() {
        BST<Integer> wideAllNodesTree = new BST<>(null);

        wideAllNodesTree.AddKeyValue(1, 71);
        wideAllNodesTree.AddKeyValue(2, 71);
        wideAllNodesTree.AddKeyValue(3, 71);
        wideAllNodesTree.AddKeyValue(4, 71);
        wideAllNodesTree.AddKeyValue(5, 71);
        wideAllNodesTree.AddKeyValue(6, 71);
        wideAllNodesTree.AddKeyValue(7, 71);

        int[] testSeries = {1, 2, 3, 4, 5, 6, 7};

        ArrayList<BSTNode> result = wideAllNodesTree.WideAllNodes();

        for (int i = 0; i < testSeries.length; i++) {
            Assertions.assertTrue(testSeries[i] == result.get(i).getKey());
        }
    }

    @Test
    void PreOrderBasic() {
        BST<Integer> wideAllNodesTree = new BST<>(null);

        wideAllNodesTree.AddKeyValue(1, 71);
        wideAllNodesTree.AddKeyValue(2, 71);
        wideAllNodesTree.AddKeyValue(3, 71);
        wideAllNodesTree.AddKeyValue(4, 71);
        wideAllNodesTree.AddKeyValue(5, 71);
        wideAllNodesTree.AddKeyValue(6, 71);
        wideAllNodesTree.AddKeyValue(7, 71);

        int[] testSeries = {1, 2, 4, 5, 3, 6, 7};

        ArrayList<BSTNode> result = wideAllNodesTree.DeepAllNodes(0);

        for (int i = 0; i < testSeries.length; i++) {
            Assertions.assertTrue(testSeries[i] == result.get(i).getKey());
        }
    }

    @Test
    void InOrderBasic() {
        BST<Integer> wideAllNodesTree = new BST<>(null);

        wideAllNodesTree.AddKeyValue(1, 71);
        wideAllNodesTree.AddKeyValue(2, 71);
        wideAllNodesTree.AddKeyValue(3, 71);
        wideAllNodesTree.AddKeyValue(4, 71);
        wideAllNodesTree.AddKeyValue(5, 71);
        wideAllNodesTree.AddKeyValue(6, 71);
        wideAllNodesTree.AddKeyValue(7, 71);

        int[] testSeries = {4, 2, 5, 1, 6, 3, 7};

        ArrayList<BSTNode> result = wideAllNodesTree.DeepAllNodes(1);

        for (int i = 0; i < testSeries.length; i++) {
            Assertions.assertTrue(testSeries[i] == result.get(i).getKey());
        }
    }

    @Test
    void PostOrderBasic() {
        BST<Integer> wideAllNodesTree = new BST<>(null);

        wideAllNodesTree.AddKeyValue(1, 71);
        wideAllNodesTree.AddKeyValue(2, 71);
        wideAllNodesTree.AddKeyValue(3, 71);
        wideAllNodesTree.AddKeyValue(4, 71);
        wideAllNodesTree.AddKeyValue(5, 71);
        wideAllNodesTree.AddKeyValue(6, 71);
        wideAllNodesTree.AddKeyValue(7, 71);

        int[] testSeries = {4, 5, 2, 6, 7, 3, 1};

        ArrayList<BSTNode> result = wideAllNodesTree.DeepAllNodes(2);

        for (int i = 0; i < testSeries.length; i++) {
            Assertions.assertTrue(testSeries[i] == result.get(i).getKey());
        }
    }

}

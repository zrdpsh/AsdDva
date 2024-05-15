import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    @Test
    void createSingleNode() {
        SimpleTreeNode newNode = new SimpleTreeNode(10001, null);
        SimpleTree newTree = new SimpleTree(newNode);

        newTree.assignLevelsToNodes();

        Assertions.assertTrue(newTree.Root.nodeLevel == 1);
        Assertions.assertTrue(newTree.Root.getValue().equals(10001));
        Assertions.assertTrue(newTree.Root.getChildren().size() == 0);
    }

    @Test
    void createNodeWithChildren() {

        SimpleTreeNode rootNode = new SimpleTreeNode(0, null);
        SimpleTree newTree = new SimpleTree(rootNode);
        SimpleTreeNode child = new SimpleTreeNode(1, null);
        SimpleTreeNode grandchild = new SimpleTreeNode(2, null);
        newTree.AddChild(rootNode, child);
        newTree.AddChild(child, grandchild);


        newTree.assignLevelsToNodes();
        List<SimpleTreeNode<Integer>> getAllNodes = new ArrayList<>();
        getAllNodes = newTree.GetAllNodes();
        int a = getAllNodes.size();

        Assertions.assertTrue(newTree.Root.nodeLevel == 1);
        Assertions.assertTrue(newTree.Root.getValue().equals(0));
        Assertions.assertTrue(newTree.Root.getChildren().size() == 1);
        Assertions.assertTrue(newTree.GetAllNodes().size() == 3);
        Assertions.assertTrue(newTree.LeafCount() == 1);
    }

    @Test
    void deleteNode() {

        SimpleTreeNode rootNode = new SimpleTreeNode(0, null);
        SimpleTree newTree = new SimpleTree(rootNode);
        SimpleTreeNode child = new SimpleTreeNode(1, null);
        SimpleTreeNode grandchild = new SimpleTreeNode(2, null);
        SimpleTreeNode grandgrandchild = new SimpleTreeNode(3, null);
        newTree.AddChild(rootNode, child);
        newTree.AddChild(child, grandchild);
        newTree.AddChild(grandchild, grandgrandchild);

        Assertions.assertTrue(newTree.GetAllNodes().size() == 4);
        Assertions.assertTrue(newTree.LeafCount() == 1);
        Assertions.assertTrue(newTree.Count() == 4);

        newTree.DeleteNode(grandchild);

        newTree.assignLevelsToNodes();
        List<SimpleTreeNode<Integer>> getAllNodes = new ArrayList<>();
        getAllNodes = newTree.GetAllNodes();
        int a = getAllNodes.size();

        Assertions.assertTrue(newTree.Root.nodeLevel == 1);
        Assertions.assertTrue(newTree.Root.getValue().equals(0));
        Assertions.assertTrue(newTree.Root.getChildren().size() == 1);
        Assertions.assertTrue(newTree.GetAllNodes().size() == 2);
        Assertions.assertTrue(newTree.LeafCount() == 1);
        Assertions.assertTrue(newTree.Count() == 2);
    }

    @Test
    void findRoot() {

        SimpleTreeNode rootNode = new SimpleTreeNode(0, null);
        SimpleTree newTree = new SimpleTree(rootNode);

        List<SimpleTreeNode<Integer>> foundNodes = new ArrayList<>();
        foundNodes = newTree.FindNodesByValue(0);

        newTree.assignLevelsToNodes();
        List<SimpleTreeNode<Integer>> getAllNodes = new ArrayList<>();
        getAllNodes = newTree.GetAllNodes();
        int a = getAllNodes.size();


        Assertions.assertTrue(foundNodes.getFirst().getValue() == 0);
        Assertions.assertTrue(newTree.GetAllNodes().size() == 1);
        Assertions.assertTrue(newTree.LeafCount() == 1);
        Assertions.assertTrue(newTree.Count() == 1);
    }

    @Test
    void findGivenNode() {

        SimpleTreeNode rootNode = new SimpleTreeNode(0, null);
        SimpleTree newTree = new SimpleTree(rootNode);
        SimpleTreeNode child = new SimpleTreeNode(1, null);
        SimpleTreeNode grandchild = new SimpleTreeNode(66, null);
        newTree.AddChild(rootNode, child);
        newTree.AddChild(child, grandchild);

        List<SimpleTreeNode<Integer>> foundNodes = new ArrayList<>();
        foundNodes = newTree.FindNodesByValue(66);
        List<SimpleTreeNode<Integer>> exampleNodes = new ArrayList<>();
        exampleNodes.add(grandchild);

        newTree.assignLevelsToNodes();
        List<SimpleTreeNode<Integer>> getAllNodes = new ArrayList<>();
        getAllNodes = newTree.GetAllNodes();
        int a = getAllNodes.size();


        Assertions.assertTrue(foundNodes.getFirst().getValue() == 66);
        Assertions.assertTrue(foundNodes.equals(exampleNodes));
        Assertions.assertTrue(newTree.GetAllNodes().size() == 3);
        Assertions.assertTrue(newTree.LeafCount() == 1);
        Assertions.assertTrue(newTree.Count() == 3);
    }

    @Test
    void findGivenNodes() {

        SimpleTreeNode rootNode = new SimpleTreeNode(0, null);
        SimpleTree newTree = new SimpleTree(rootNode);
        SimpleTreeNode child = new SimpleTreeNode(1, null);
        SimpleTreeNode grandchild = new SimpleTreeNode(66, null);
        SimpleTreeNode grandgrandchild = new SimpleTreeNode(66, null);
        newTree.AddChild(rootNode, child);
        newTree.AddChild(child, grandchild);
        newTree.AddChild(grandchild, grandgrandchild);

        List<SimpleTreeNode<Integer>> foundNodes = new ArrayList<>();
        foundNodes = newTree.FindNodesByValue(66);
        List<SimpleTreeNode<Integer>> exampleNodes = new ArrayList<>();
        exampleNodes.add(grandchild);
        exampleNodes.add(grandgrandchild);

        newTree.assignLevelsToNodes();
        List<SimpleTreeNode<Integer>> getAllNodes = new ArrayList<>();
        getAllNodes = newTree.GetAllNodes();
        int a = getAllNodes.size();


        Assertions.assertTrue(foundNodes.equals(exampleNodes));
        Assertions.assertTrue(newTree.GetAllNodes().size() == 4);
        Assertions.assertTrue(newTree.LeafCount() == 1);
        Assertions.assertTrue(newTree.Count() == 4);
    }

    @Test
    void moveNode() {

        SimpleTreeNode rootNode = new SimpleTreeNode(0, null);
        SimpleTree newTree = new SimpleTree(rootNode);
        SimpleTreeNode child1 = new SimpleTreeNode(10, null);
        SimpleTreeNode child2 = new SimpleTreeNode(11, null);
        SimpleTreeNode grandchild = new SimpleTreeNode(20, null);
        SimpleTreeNode grandgrandchild = new SimpleTreeNode(30, null);
        newTree.AddChild(rootNode, child1);
        newTree.AddChild(rootNode, child2);
        newTree.AddChild(child1, grandchild);
        newTree.AddChild(grandchild, grandgrandchild);

        Assertions.assertTrue(newTree.GetAllNodes().size() == 5);
        Assertions.assertTrue(newTree.LeafCount() == 2);
        Assertions.assertTrue(newTree.Count() == 5);

        newTree.MoveNode(grandchild, child2);

        newTree.assignLevelsToNodes();
        List<SimpleTreeNode<Integer>> getAllNodes = new ArrayList<>();
        getAllNodes = newTree.GetAllNodes();
        int a = getAllNodes.size();

        Assertions.assertTrue(newTree.Root.nodeLevel == 1);
        Assertions.assertTrue(newTree.Root.getValue().equals(0));
        Assertions.assertTrue(newTree.GetAllNodes().size() == 5);
        Assertions.assertTrue(newTree.LeafCount() == 2);
        Assertions.assertTrue(newTree.Count() == 5);
        Assertions.assertTrue(grandchild.Parent.equals(child2));
    }

    @Test
    void howManyNodesAndLeaves() {

        SimpleTreeNode rootNode = new SimpleTreeNode(0, null);
        SimpleTree newTree = new SimpleTree(rootNode);
        SimpleTreeNode child = new SimpleTreeNode(1, null);
        SimpleTreeNode grandchild = new SimpleTreeNode(2, null);
        SimpleTreeNode grandgrandchild = new SimpleTreeNode(3, null);
        newTree.AddChild(rootNode, child);
        newTree.AddChild(child, grandchild);
        newTree.AddChild(grandchild, grandgrandchild);

        Assertions.assertTrue(newTree.GetAllNodes().size() == 4);
        Assertions.assertTrue(newTree.LeafCount() == 1);
        Assertions.assertTrue(newTree.Count() == 4);
    }
}
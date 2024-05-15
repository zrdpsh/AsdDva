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
}
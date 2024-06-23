import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

public class aBSTtests {


    @Test
    void creatingTrees() {
        aBST emptyTree = new aBST(0);
        aBST oneLevel = new aBST(1);
        aBST threeLevel = new aBST(3);

        Assertions.assertTrue(emptyTree.Tree.length == 1);
        Assertions.assertTrue(oneLevel.Tree.length == 3);
        Assertions.assertTrue(threeLevel.Tree.length == 15);
    }

    @Test
    void findKeyInEmptyTree() {
        aBST treeWhereNoKey = new aBST(3);

        Assertions.assertTrue(treeWhereNoKey.Tree.length == 15);
        Assertions.assertTrue(treeWhereNoKey.FindKeyIndex(5) == 0);
    }

    @Test
    void findKeyWhereNoKeyPresented() {
        aBST treeWhereNoKey = new aBST(3);
        treeWhereNoKey.AddKey(5);

        Assertions.assertTrue(treeWhereNoKey.Tree.length == 15);
        Assertions.assertTrue(treeWhereNoKey.FindKeyIndex(6) == -2);
    }

}

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

}

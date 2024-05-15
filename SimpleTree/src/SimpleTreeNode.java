import java.lang.reflect.Array;
import java.util.*;

public class SimpleTreeNode<T>
{
        public T NodeValue;
        public SimpleTreeNode<T> Parent;
        public List<SimpleTreeNode<T>> Children;
        public int nodeLevel = 0;

        public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
        {
            NodeValue = val;
            Parent = parent;
            Children = null;
        }

        public void makeChildrenList() {
            Children = new ArrayList<>();
        }

        public List<SimpleTreeNode<T>> getChildren() {
            if (Children == null) return new ArrayList<>();
            return Children;
        }

        public T getValue() { return NodeValue; }
}
	
class SimpleTree<T>
  {
        public SimpleTreeNode<T> Root; // корень, может быть null
        private int howManyNodes = 0;

        public SimpleTree(SimpleTreeNode<T> root)
        {
            Root = root;
            howManyNodes++;
        }
	
        public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
        {
            if (ParentNode.Children == null) ParentNode.makeChildrenList();
            ArrayList<SimpleTreeNode<T>> descendants = new ArrayList<>();
            collectRecursively(NewChild, descendants);
            int numOfDescendants = descendants.size();
            ParentNode.Children.add(NewChild);
            NewChild.Parent = ParentNode;
            howManyNodes+=numOfDescendants;
        }

        public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
        {
            boolean notRootNode = !NodeToDelete.equals(Root);
            if (notRootNode) {
                ArrayList<SimpleTreeNode<T>> descendants = new ArrayList<>();
                collectRecursively(NodeToDelete, descendants);
                int reduceIndex = descendants.size();
                NodeToDelete.Parent.Children.remove(NodeToDelete);
                howManyNodes-=reduceIndex;
            }
        }

       public List<SimpleTreeNode<T>> GetAllNodes()
        {
            ArrayList<SimpleTreeNode<T>> result = new ArrayList<>();
            collectRecursively(Root, result);

            return result;
        }
	
       public List<SimpleTreeNode<T>> FindNodesByValue(T val)
       {
            ArrayList<SimpleTreeNode<T>> result = new ArrayList<>();
            filterRecursively(Root, result, val);

            return result;
       }

        public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
        {
            SimpleTreeNode<T> oldParent = OriginalNode.Parent;
            oldParent.Children.remove(OriginalNode);
            ArrayList<SimpleTreeNode<T>> descendants = new ArrayList<>();
            collectRecursively(OriginalNode, descendants);
            int reduceIndex = descendants.size();
            howManyNodes -= reduceIndex;
            AddChild(NewParent, OriginalNode);
        }

        public int Count()
        {
            return howManyNodes;
        }

        public int LeafCount()
        {
            ArrayList<SimpleTreeNode<T>> result = new ArrayList<>();
            countLeavesRecursively(Root, result);
            return result.size();
        }

        private void collectRecursively(SimpleTreeNode<T> root, ArrayList<SimpleTreeNode<T>> result) {
            if (root.Children == null || root.Children.size() == 0) {
                result.add(root);
                return;
            }

            result.add(root);

            for (SimpleTreeNode<T> currentNode : root.Children) {
                collectRecursively(currentNode, result);
            }
        }

        private void filterRecursively(SimpleTreeNode<T> root, ArrayList<SimpleTreeNode<T>> result, T valueToCompareTo) {
            if (root.Children == null || root.Children.size() == 0) return;

            for (SimpleTreeNode<T> currentNode : root.Children) {
              if (currentNode.getValue().equals(valueToCompareTo)) result.add(currentNode);
              filterRecursively(currentNode, result, valueToCompareTo);
            }
        }

        private void countLeavesRecursively(SimpleTreeNode<T> root, ArrayList<SimpleTreeNode<T>> result) {
            if (root.Children == null || root.Children.size() == 0) {
              result.add(root);
              return;
            }

            for (SimpleTreeNode<T> currentNode : root.Children) {
              countLeavesRecursively(currentNode, result);
            }
        }

        public void assignLevelsToNodes() {
            assignLevelsRecursively(Root, 1);
        }

        private void assignLevelsRecursively(SimpleTreeNode<T> root, int currentLevel) {
            if (root.Children == null || root.Children.size() == 0) {
                root.nodeLevel = currentLevel;
                return;
            }

            root.nodeLevel = currentLevel;

            for (SimpleTreeNode<T> currentNode : root.getChildren()) {
                currentNode.nodeLevel = currentLevel;
                assignLevelsRecursively(currentNode, currentLevel+1);
            }
        }
}











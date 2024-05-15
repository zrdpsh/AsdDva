import java.util.*;

public class SimpleTreeNode<T>
{
        public T NodeValue;
        public SimpleTreeNode<T> Parent;
        public List<SimpleTreeNode<T>> Children;

        public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
        {
          NodeValue = val;
          Parent = parent;
          Children = null;
        }

        public void makeChildrenList() {
            Children = new ArrayList<>();
        }
}
	
class SimpleTree<T>
  {
        public SimpleTreeNode<T> Root; // корень, может быть null
        private int howManyNodes = 0;

        public SimpleTree(SimpleTreeNode<T> root)
        {
          Root = root;
        }
	
        public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
        {
            if (ParentNode.Children == null) ParentNode.makeChildrenList();
            ParentNode.Children.add(NewChild);
            NewChild.Parent = ParentNode;
            howManyNodes++;
        }

        public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
        {
            boolean notRootNode = NodeToDelete.equals(Root);
            if (notRootNode) {
                NodeToDelete.Parent.Children.remove(NodeToDelete);
                howManyNodes-=1;
            }
        }

       public List<SimpleTreeNode<T>> GetAllNodes()
        {
          // ваш код выдачи всех узлов дерева в определённом порядке
          return null;
        }
	
       public List<SimpleTreeNode<T>> FindNodesByValue(T val)
       {
          // ваш код поиска узлов по значению
          return null;
       }

        public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
        {
            SimpleTreeNode<T> oldParent = OriginalNode.Parent;
            oldParent.Children.remove(OriginalNode);
            AddChild(NewParent, OriginalNode);
        }

        public int Count()
        {
            return howManyNodes;
        }

        public int LeafCount()
        {
          // количество листьев в дереве
          return 0;
        }
}











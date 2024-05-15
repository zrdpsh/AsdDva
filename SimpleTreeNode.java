import java.util.*;

public class SimpleTreeNode<T>
{
        public T NodeValue; // значение в узле
        public SimpleTreeNode<T> Parent; // родитель или null для корня
        public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

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
          // ваш код добавления нового дочернего узла существующему ParentNode
            if (ParentNode.Children == null) ParentNode.makeChildrenList();
            ParentNode.Children.add(NewChild);
            NewChild.Parent = ParentNode;
            howManyNodes++;
        }

        public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
        {
          // ваш код удаления существующего узла NodeToDelete
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

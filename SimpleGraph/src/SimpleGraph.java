import java.util.*;

class Vertex
{
    public int Value;
    public boolean Hit;

    public Vertex(int val)
    {
        Value = val;
        Hit = false;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isSameObject = obj == this;
        if (isSameObject) {
            return true;
        }

        boolean isAnotherClassObject = !(obj instanceof Vertex);
        if (isAnotherClassObject) {
            return false;
        }
        Vertex bstNodeForCheck = (Vertex) obj;
        boolean isEqualValue = this.Value == bstNodeForCheck.Value;

        return isEqualValue;
    }
}

class SimpleGraph
{
    Vertex [] vertex;
    int [][] m_adjacency;
    int max_vertex;


    public SimpleGraph(int size)
    {
        max_vertex = size;
        m_adjacency = new int [size][size];
        vertex = new Vertex[size];
    }


    public void AddVertex(int value)
    {
        Vertex newVertex = new Vertex(value);

        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] == null) {
                vertex[i] = newVertex;
                return;
            }
        }
    }


    public void RemoveVertex(int v)
    {
        if (isValidIndex(v)) {
            vertex[v] = null;
            removeEdges(v);
        }

    }


    private boolean isValidIndex(int index) {
        return index >= 0 && index < max_vertex;
    }


    public boolean IsEdge(int v1, int v2)
    {
        if (isValidIndex(v1) && isValidIndex(v2)) {
            return m_adjacency[v1][v2] == 1;
        }

        return false;
    }


    public void AddEdge(int v1, int v2)
    {
        if(isValidIndex(v1) && isValidIndex(v2)) {
            m_adjacency[v1][v2] = 1;
            m_adjacency[v2][v1] = 1;
        }

    }


    public void RemoveEdge(int v1, int v2)
    {
        if(isValidIndex(v1) && isValidIndex(v2)) {
            m_adjacency[v1][v2] = 0;
            m_adjacency[v2][v1] = 0;
        }
    }


    public void removeEdges(int vertixIndex) {
        for (int i = 0; i < max_vertex; i++) RemoveEdge(vertixIndex, i);
    }


    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        if (!isValidIndex(VFrom) || !isValidIndex(VTo)) {
            return new ArrayList<>();
        }

        clearPath();
        ArrayList<Integer> stack = new ArrayList<>();
        stack = findPath(VFrom, VTo, stack);
        return indicesToVertices(stack);
    }


    public void clearPath() {
        for (int vertexIndex = 0; vertexIndex < max_vertex; vertexIndex++) {
            if (vertex[vertexIndex] != null) vertex[vertexIndex].Hit = false;
        }
    }


    public ArrayList<Integer> findPath(int parentIndex, int searchedVertexIndex, ArrayList<Integer> stack) {

        hitVertex(parentIndex);
        stack.add(parentIndex);
        return probeNextBranch(parentIndex, searchedVertexIndex, stack);
    }


    public void hitVertex(int indexOfVertex) {
        Vertex vertexObject = vertex[indexOfVertex];
        vertexObject.Hit = true;
    }


    public ArrayList<Integer> probeNextBranch(int parentIndex,
                                              int searchedVertexIndex, ArrayList<Integer> stack) {

        if (resultIsFound(parentIndex, searchedVertexIndex)) {
            stack.add(searchedVertexIndex);
            return stack;
        }

        int nextFreeNeighbour = getNextFreeNeighbour(parentIndex);
        if (nextFreeNeighbour != -1) {
            return findPath(nextFreeNeighbour, searchedVertexIndex, stack);
        }
        return stepBack(parentIndex, searchedVertexIndex, stack);
    }


    public boolean resultIsFound(int vertexIndex, int searchedVertexIndex) {

        boolean result = false;

        for (int i = 0; i < max_vertex; i++) {
            if (vertexIndex != i && IsEdge(vertexIndex, i) && i == searchedVertexIndex) return true;
        }

        return result;

    }


    public int getNextFreeNeighbour(int vertexIndex) {

        int result = -1;

        for (int i = 0; i < max_vertex; i++) {
            if (vertexIndex != i && IsEdge(vertexIndex, i) && !vertex[i].Hit) return i;
        }

        return result;
    }


    public ArrayList<Integer> stepBack(int parentIndex, int searchedVertexIndex, ArrayList<Integer> stack) {
        stack = removeLastFromStack(stack);

        if (stack.size() == 0) {
            return stack;
        }

        int newStartIndex = stack.getLast();
//        vertex[newStartIndex].Hit = true;
        return probeNextBranch(newStartIndex, searchedVertexIndex, stack);
    }


    private ArrayList<Integer> removeLastFromStack(ArrayList<Integer> stack) {
        if (stack.size() > 0) stack.remove(stack.size() - 1);
        return stack;
    }



    public ArrayList<Vertex> indicesToVertices(ArrayList<Integer> stack) {
        ArrayList<Vertex> resultingVertices = new ArrayList<>();

        for (Integer stackElement : stack) resultingVertices.add(vertex[stackElement]);

        return resultingVertices;
    }


}















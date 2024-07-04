import java.util.*;

class Vertex
{
    public int Value;
    public boolean Hit;
    public int DepthLevel;

    public Vertex(int val)
    {
        Value = val;
        Hit = false;
        DepthLevel = 0;
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
    ArrayList<Integer> pathToTarget;


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

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo)
    {
        if (!isValidIndex(VFrom) || !isValidIndex(VTo)) {
            return new ArrayList<>();
        }

        clearPath();

        pathToTarget.add(VFrom);
        markAsVisited(VFrom);
        if (VFrom == VTo) return indicesToVertices(pathToTarget);

        Queue<Integer> path = new LinkedList<>();
        createPath(VFrom, VTo, path);
        return indicesToVertices(pathToTarget);
    }

    public void clearPath() {
        for (int vertexIndex = 0; vertexIndex < max_vertex; vertexIndex++) {
            if (vertex[vertexIndex] != null) {
                vertex[vertexIndex].Hit = false;
                vertex[vertexIndex].DepthLevel = 0;
            }
        }
        pathToTarget = new ArrayList<>();
    }

    public ArrayList<Integer> findPath(int parentIndex,
                                       int searchedVertexIndex,
                                       ArrayList<Integer> stack) {

        markAsVisited(parentIndex);
        stack.add(parentIndex);
        return probeNextBranch(parentIndex, searchedVertexIndex, stack);
    }

    public Queue<Integer> createPath (int currentIndex,
                                      int targetIndex,
                                      Queue<Integer> path) {

        int nextVertex = getNextFreeNeighbour(currentIndex);

        if (nextVertex != -1) return addNeighbourToQueue(currentIndex, targetIndex, nextVertex, path);

        boolean thereIsQueue = path.size() > 0;
        if (thereIsQueue) {
            int nextIndex = path.poll();
            addVertexToPath(nextIndex);
            return createPath(nextIndex, targetIndex, path);
        }

        clearPath();
        return path;

    }

    public Queue<Integer> addNeighbourToQueue(int currentIndex,
                                              int targetIndex,
                                              int nextVertexIndex,
                                              Queue<Integer> path) {
        markAsVisited(nextVertexIndex);

        Vertex currentVertex = vertex[currentIndex];
        Vertex nextVertex = vertex[nextVertexIndex];
        nextVertex.DepthLevel = currentVertex.DepthLevel + 1;

        path.add(nextVertexIndex);

        if (nextVertexIndex == targetIndex) {
            pathToTarget.add(targetIndex);
            return path;
        }

        return createPath(currentIndex, targetIndex, path);
    }

    public void addVertexToPath(int index) {
        int lastIndexInPath = pathToTarget.get(pathToTarget.size() - 1);
        Vertex lastVertexInPath = vertex[lastIndexInPath];
        Vertex candidateVertex = vertex[index];
        
        if (lastVertexInPath.DepthLevel < candidateVertex.DepthLevel) {
            pathToTarget.add(index);
            return;
        }

        pathToTarget.remove(pathToTarget.size() - 1);
        pathToTarget.add(index);
    }


    public void markAsVisited(int indexOfVertex) {
        vertex[indexOfVertex].Hit = true;
    }


    public ArrayList<Integer> probeNextBranch(int parentIndex,
                                              int searchedVertexIndex,
                                              ArrayList<Integer> stack) {

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


    public boolean resultIsFound(int vertexIndex,
                                 int searchedVertexIndex) {

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


    public ArrayList<Integer> stepBack(int parentIndex,
                                       int searchedVertexIndex,
                                       ArrayList<Integer> stack) {
        stack = removeLastFromStack(stack);

        if (stack.size() == 0) {
            return stack;
        }

        int newStartIndex = stack.getLast();
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















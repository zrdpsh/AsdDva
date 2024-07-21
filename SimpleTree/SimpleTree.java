import java.util.*;

class Vertex
{
    public int Value;
    public Vertex(int val)
    {
        Value = val;
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
}










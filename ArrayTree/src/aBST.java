import java.util.*;

class aBST
{
    public Integer Tree []; // массив ключей

    public aBST(int depth)
    {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = 0;
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key)
    {
        // ищем в массиве индекс ключа
        return null; // не найден
    }

    public int AddKey(int key)
    {
        // добавляем ключ в массив
        return -1;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }

}

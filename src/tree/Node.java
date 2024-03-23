package tree;

import java.io.*;
import java.util.*;

// Определение класса Node (Узел)
public class Node {
    private int id; // Идентификатор узла
    private Node parent; // Родительский узел
    private List<Node> children = new ArrayList<>(); // Список дочерних узлов

    public Node(int id) { // Конструктор узла
        this.id = id;
    }

    public int getId() { // Получить идентификатор узла
        return id;
    }

    public Node getParent() { // Получить родительский узел
        return parent;
    }

    public void setParent(Node parent) { // Установить родительский узел
        this.parent = parent;
    }

    public List<Node> getChildren() { // Получить список дочерних узлов
        return children;
    }

    public boolean isLeaf() { // Проверить, является ли узел листом (узлом без дочерних узлов)
        return children.isEmpty();
    }

    public boolean isRoot() { // Проверить, является ли узел корнем (узлом без родительского узла)
        return parent == null;
    }
}
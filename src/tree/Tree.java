package tree;


public class Tree {
    private Node root; // Корневой узел дерева

    public Tree(Node root) { // Конструктор дерева
        this.root = root;
    }

    public Node getRoot() { // Получить корневой узел
        return root;
    }

    public int getMaxBranchLength() { // Найти максимальную длину ветки в дереве
        return getMaxBranchLength(root); // Начинаем с корневого узла
    }

    private int getMaxBranchLength(Node node) { // Рекурсивная функция для нахождения максимальной длины ветки
        int max = 0; // Инициализация максимальной длины
        for (Node child : node.getChildren()) { // Обход всех дочерних узлов текущего узла
            max = Math.max(max, getMaxBranchLength(child)); // Обновление максимальной длины
        }
        return max + 1; // Увеличение длины на 1 (учитывая текущий узел)
    }

    public int getNumMaxBranches() { // Найти количество веток максимальной длины
        int maxLen = getMaxBranchLength(); // Получить максимальную длину ветки
        return getNumBranchesOfLength(root, maxLen); // Найти количество веток заданной длины, начиная с корневого узла
    }

    private int getNumBranchesOfLength(Node node, int len) { // Рекурсивная функция для нахождения количества веток заданной длины
        if (len == 1) { // Если длина равна 1, проверить, является ли узел листом
            return node.isLeaf() ? 1 : 0; // Если узел является листом, вернуть 1, иначе вернуть 0
        }
        int sum = 0; // Инициализация суммы
        for (Node child : node.getChildren()) { // Обход всех дочерних узлов текущего узла
            sum += getNumBranchesOfLength(child, len - 1); // Обновление суммы
        }
        return sum; // Вернуть сумму
    }

    public int getNumLeaves() { // Найти количество листьев в дереве
        return getNumLeaves(root); // Начать с корневого узла
    }

    private int getNumLeaves(Node node) { // Рекурсивная функция для нахождения количества листьев
        if (node.isLeaf()) { // Если узел является листом, вернуть 1
            return 1;
        }
        int sum = 0; // Инициализация суммы
        for (Node child : node.getChildren()) { // Обход всех дочерних узлов текущего узла
            sum += getNumLeaves(child); // Обновление суммы
        }
        return sum; // Вернуть сумму
    }
}

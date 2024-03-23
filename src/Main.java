import tree.Node;
import tree.Tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException { // Главная функция программы
        // Чтение данных из файла и построение деревьев
        List<Tree> trees = buildTreesFromCSV("input.csv");

        // Подсчет количества деревьев
        int numTrees = trees.size();

        // Подсчет общего количества листьев во всех деревьях
        int numLeaves = trees.stream().mapToInt(Tree::getNumLeaves).sum();

        // Нахождение максимальной длины ветки среди всех деревьев
        int maxBranchLen = trees.stream().mapToInt(Tree::getMaxBranchLength).max().orElse(0);

        // Нахождение деревьев с максимальной длиной ветки
        List<Tree> maxBranchTrees = trees.stream().filter(tree -> tree.getMaxBranchLength() == maxBranchLen).collect(Collectors.toList());

        // Если есть только одно дерево с максимальной длиной ветки, получаем его ID
        // В противном случае, устанавливаем ID в 0
        int idMaxBranchTree = maxBranchTrees.size() == 1 ? maxBranchTrees.get(0).getRoot().getId() : 0;

        // Запись результатов в файл
        writeResultsToFile("result.txt", numTrees, numLeaves, idMaxBranchTree, maxBranchLen);
    }

    public static List<Tree> buildTreesFromCSV(String filename) throws IOException { // Функция для чтения данных из файла и построения деревьев
        Map<Integer, Node> nodes = new HashMap<>(); // Используется для хранения всех узлов по их идентификаторам
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { // Создание объекта BufferedReader для чтения файла
            String line; // Строка для хранения текущей строки файла
            while ((line = reader.readLine()) != null) { // Чтение файла построчно
                String[] parts = line.split(","); // Разделение строки на части по запятой
                int id = Integer.parseInt(parts[0]); // Получение идентификатора узла из первой части строки
                int parentId = Integer.parseInt(parts[1]); // Получение идентификатора родительского узла из второй части строки
                Node node = nodes.computeIfAbsent(id, Node::new); // Получение узла по идентификатору или создание нового узла, если он еще не существует
                Node parent = nodes.computeIfAbsent(parentId, Node::new); // То же самое для родительского узла
                node.setParent(parent); // Установка родительского узла для текущего узла
                parent.getChildren().add(node); // Добавление текущего узла в список дочерних узлов родительского узла
            }
        }
        List<Tree> trees = new ArrayList<>(); // Список для хранения деревьев
        for (Node node : nodes.values()) { // Обход всех узлов
            if (node.isRoot()) { // Если узел является корнем
                trees.add(new Tree(node)); // Добавление нового дерева в список
            }
        }
        return trees; // Возвращение списка деревьев
    }

    public static void writeResultsToFile(String filename, int numTrees, int numLeaves, int idMaxBranchTree, int maxBranchLen) throws IOException { // Функция для записи результатов в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) { // Создание объекта BufferedWriter для записи в файл
            writer.write(numTrees + " " + numLeaves + " " + idMaxBranchTree + " " + maxBranchLen); // Запись результатов в файл
        }
    }
}

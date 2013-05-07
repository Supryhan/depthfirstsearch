package depthfirstsearch;

import java.io.*;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Starting class with parser
 */
public class App {
    Set<Integer> setOfUsedNodes;

    /**
     * Parser for reading file and placing information into the tree of nodes
     *
     * @param fileName filename with input information
     */
    //Метод, який запускається та вибудовує дерево успадковування на основі текстового файла.
    public void parser(String fileName) {
        BufferedReader bufferedReader = null;
        Node rootNode = null;
        //Зчитування з файлу
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        int numberOfCases = 0;
        //Визначеня кількості випадків
        try {
            numberOfCases = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't read the number of cases!");
        }
        //Cases parsing
        //Пробігаємося по кожному випадку - створюємо дерево успадковування і шукаємо ромбовидне успадковування
        for (int currentCase = 1; currentCase <= numberOfCases; currentCase++) {
            setOfUsedNodes = new TreeSet<Integer>();
            int numberOfClasses = 0;
            try {
                numberOfClasses = Integer.parseInt(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Can't read case #" + currentCase);
            }
            //Classes parsing
            //Перебираємо усі класи для даного випадку
            int child = 1, parent;
            rootNode = new Node("1");//create root
            setOfUsedNodes.add(1);//register root, why not?
            for (int childClassNumber = 1; childClassNumber <= numberOfClasses; childClassNumber++) {
                int numberOfParent = 0;
                StringTokenizer stringTokenizer = null;
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (stringTokenizer.hasMoreTokens())
                    numberOfParent = Integer.parseInt(stringTokenizer.nextToken());
                //Inheritance for separate node parsing
                for (int parentNumber = 1; parentNumber <= numberOfParent; parentNumber++) {
                    if (stringTokenizer.hasMoreTokens()) {
                        int parentClassNumber = Integer.parseInt(stringTokenizer.nextToken());
                        //Реєструємо для даного лдочірнього елементу його черговий батьківський елемент
                        this.registerNode(rootNode, childClassNumber, parentClassNumber);
                    }
                }
            }
            //Усе, дерево успадковування сформоване, можна його аналізувати.


            //Ось тут запускається сервісний клас, він має посилання на корінь дерева і аналізуючи усе дерево видає результат:
            //ромбовидне успадковуваня присутнє або його немає.
            DepthFirstSearch depthFirstSearch = new DepthFirstSearch(rootNode);
            System.out.print("Case #" + currentCase + ": ");
            if (DepthFirstSearch.diamondInheritance) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    /**
     * Try to register parent node in child's list
     *
     * @param rootNode root node of tree
     * @param child    number of child node
     * @param parent   number of parent node
     */
    //Метод добавляє допоточного дочірнього класу черговий батьківський клас
    //реба тільки його дати посилання на корінь дерева і номери дочірнього і батьківського класів.
    public void registerNode(Node rootNode, int child, int parent) {
        Node currentChildNode = searchNode(rootNode, child);
        Node currentParentNode = null;
        Node tempNode = searchNode(rootNode, parent);
        if (tempNode == null) {
            currentParentNode = new Node(String.valueOf(parent));
        } else {
            currentParentNode = tempNode;
        }
        currentChildNode.nodesList.add(currentParentNode);
    }

    /**
     * Searching the proper node while having only its number
     *
     * @param rootNode the node to start from
     * @param i        number of node to find
     * @return searched node or null
     */
    //метод, який відшуковоє клас із заданим номером (імям) і повертає посилання на нього назад
    //у точку виклику. Якщо такої вершини немає, то у точку виклику повертається значення НУЛЛ
    //Сама точка виклику отримавши НУЛЛ, має створити нову вершину (клас) із заданим імям, бо такої вершини немає,
    //ала вона має бути створена.
    public Node searchNode(Node rootNode, int i) {
        if (rootNode.intName == i)
            return rootNode;
        Node tmp = null;
        for (Node n : rootNode.nodesList) {
            tmp = searchNode(n, i);
            if (tmp != null) return tmp;
        }
        if (tmp != null) return tmp;
        return null;
    }

    /**
     * Main method
     *
     * @param args input array of Strings
     */
    public static void main(String[] args) {
        new App().parser("src/main/in.txt");
    }
}

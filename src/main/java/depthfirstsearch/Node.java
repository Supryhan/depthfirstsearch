package depthfirstsearch;

import java.util.LinkedList;
import java.util.List;

/**
 * Node for organizing tree
 */
//Класс із якого ми вибудовуємо дерево успадковування і не просто кріпимо до нього одного батька,
//а вказуємо цілий набір батьківських класів.
public class Node {
    //Колекція із набором батьківських класів
    public List<Node> nodesList;
    //Змінна, яка вказує що під час пошуку ця вершина успадковування уже розглядалася.
    public boolean checked = false;
    //Імя вершини
    public String name;
    //Імя вершини у вигляді числа (не текст, так простіше робити порівняння)
    public int intName;

    public Node(String name) {
        this.intName = Integer.parseInt(name);
        this.name = name;
        nodesList = new LinkedList<Node>();
    }
}

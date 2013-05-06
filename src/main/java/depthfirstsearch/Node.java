package depthfirstsearch;

import java.util.LinkedList;
import java.util.List;

/**
 * Node for organizing tree
 */
public class Node {
    public List<Node> nodesList;
    public boolean checked = false;
    public String name;
    public int intName;

    public Node(String name) {
        this.intName = Integer.parseInt(name);
        this.name = name;
        nodesList = new LinkedList<Node>();
    }
}

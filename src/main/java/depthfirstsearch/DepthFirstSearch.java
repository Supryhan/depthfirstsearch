package depthfirstsearch;

/**
 * Utility class
 */
public class DepthFirstSearch {
    public static boolean diamondInheritance = false;

    public DepthFirstSearch(Node rootNode) {
        depthSearchFirst(rootNode);
    }

    public void depthSearchFirst(Node currentNode) {
        if (currentNode.checked) diamondInheritance = true;
        currentNode.checked = true;
        if (currentNode.nodesList.size() == 0) return;
        for (Node n : currentNode.nodesList) {
            depthSearchFirst(n);
        }
    }
}
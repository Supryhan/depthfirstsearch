package depthfirstsearch;

/**
 * Utility class
 */
//Інструментальний клас, який отримує посилання на дерево успадковування і починає
//обхід цього дерева у глибину.
//Увага, вершиною дерева є самий останній нащадок і ми рухаємося по дереву відшуковуючи усіх батьків
//від яких він повів свій рід.

public class DepthFirstSearch {
    //Змінна, яка показує, що у даному дереві усе таки було знайдено ромбовидне успадковування
    //Один раз знайшовши таке успадковування, далі можна виходити із програми - це і є результат.
    public static boolean diamondInheritance = false;

    public DepthFirstSearch(Node rootNode) {
        depthSearchFirst(rootNode);
    }
    //Рекурсивний метод, який викликає сам себе до тих пір, доки він не достигне останніх елементів на гілках
    public void depthSearchFirst(Node currentNode) {
        if (currentNode.checked) diamondInheritance = true;
        currentNode.checked = true;
        if (currentNode.nodesList.size() == 0) return;
        for (Node n : currentNode.nodesList) {
            depthSearchFirst(n);
        }
    }
}
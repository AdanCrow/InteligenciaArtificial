/* Crow Sosa Jesus Adan - 23170132
* Inteligencia Artificial 6:00 - 7:00 p.m.
* 8 Puzzle   */
package app;

import arbol.NodeUtils;
import arbol.SearchTree;

public class App {
    public static void main(String[] args) {
        String initialState = "7621 8435";
        String endState = "12345678 ";

        System.out.println("INITIAL STATE: \n" + NodeUtils.formatState(initialState));
        System.out.println("END STATE: \n" + NodeUtils.formatState(endState));

        SearchTree searchTree = new SearchTree(initialState, endState);

        // 1. Búsqueda primero en anchura
        searchTree.breadthFirstSearch();

        // 2. Búsqueda en profundidad
        searchTree.deepFirstSearch();

        // 3. Búsqueda de costo uniforme
        searchTree.uniformCostSearch();

        // 4. Busqueda Iterativa
        searchTree.iterativeDeepeningSearch(50);

    }
}
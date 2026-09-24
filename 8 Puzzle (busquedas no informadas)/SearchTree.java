/* Crow Sosa Jesus Adan - 23170132
 * Inteligencia Artificial 6:00 - 7:00 p.m.
 * 8 Puzzle   */
package arbol;

import java.util.*;

public class SearchTree {
    private static final int MAX_PATH_TO_PRINT = 60;
    private Node root;
    private String initialState;
    private String endState;


    public SearchTree(String initialState, String endState){
        this.initialState = initialState;
        this.endState = endState;
        this.root = new Node(initialState, null);
    }

    // 1. Busqueda primero en anchura
    public void breadthFirstSearch() {
        int time = 0;
        int generated = 0;
        long startTime = System.currentTimeMillis();
        // Crear estructura de datos para almacenar los nodos visitados
        Set<String> visited = new HashSet<String>();
        // 1. Buscar el nodo raíz y agregarlo a la cola
        Node currentNode = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(currentNode);
        generated++;
        // 2. Mientras la cola no esté vacía, hacer lo siguiente:
        while (!queue.isEmpty()) {
            time++;
            // 3. Sacar el primer nodo de la cola y verificar si es el nodo objetivo
            currentNode = queue.poll();
            if (visited.contains(currentNode.getState())) {
                continue;
            }
            visited.add(currentNode.getState());
            if(currentNode.getState().equals(endState)) {
                System.out.println("Goal state found: " + currentNode.getState());
                // print the path from root to goal
                printPath(currentNode);
                printResults("Busqueda primero en anchura", currentNode.getDepth(), time, generated, startTime);
                return;
            }
            // 4. Si no es el nodo objetivo, generar sus hijos y agregarlos a la cola
            List<Node> children = NodeUtils.generateChildren(currentNode);
            for (Node child : children) {
                if (!visited.contains(child.getState())) {
                    queue.add(child);
                    generated++;
                }
            }
        }

        System.out.println("=======================================");
        System.out.println("Busqueda primero en anchura");
        System.out.println("Time: " + time);
        System.out.println("Estados visitados: " + visited.size());
        System.out.println("Stack: " + queue.size());
        System.out.println("=======================================");

    }

    // 2. Profundidad
    public void deepFirstSearch() {
        int time = 0;
        int generated = 0;
        long startTime = System.currentTimeMillis();
        // Crear estructura de datos para almacenar los nodos visitados
        Set<String> visited = new HashSet<String>();
        // 1. Buscar el nodo raíz y agregarlo a la cola
        Node currentNode = root;
        Stack<Node> stack = new Stack<>();
        stack.push(currentNode);
        generated++;
        // 2. Mientras la cola no esté vacía, hacer lo siguiente:
        while (!stack.isEmpty()) {
            time++;
            // 3. Sacar el primer nodo de la cola y verificar si es el nodo objetivo
            currentNode = stack.pop();
            if (visited.contains(currentNode.getState())) {
                continue;
            }
            visited.add(currentNode.getState());
            // System.out.println(NodeUtils.formatState(currentNode.getState()));
            if(currentNode.getState().equals(endState)) {
                System.out.println("Goal state found: " + currentNode.getState());
                // print the path from root to goal
                printPath(currentNode);
                printResults("Busqueda de Profundidad", currentNode.getDepth(), time, generated, startTime);
                return;
            }
            // 4. Si no es el nodo objetivo, generar sus hijos y agregarlos a la cola
            List<Node> children = NodeUtils.generateChildren(currentNode);
            for (Node child : children) {
                if (!visited.contains(child.getState())) {
                    stack.add(child);
                    generated++;
                }
            }
        }
        System.out.println("=======================================");
        System.out.println("Busqueda de Profundidad");
        System.out.println("Time: " + time);
        System.out.println("Estados visitados: " + visited.size());
        System.out.println("Stack: " + stack.size());
        System.out.println("=======================================");

    }

    // 3. Costo uniforme
    public void uniformCostSearch() {
        int time = 0;
        int generated = 0;
        long startTime = System.currentTimeMillis();
        // Crear estructura de datos para almacenar los nodos visitados
        Set<String> visited = new HashSet<String>();
        // 1. Buscar el nodo raíz y agregarlo a la cola
        Node currentNode = root;
        PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodePriorityComparator());
        queue.add(currentNode);
        generated++;
        // 2. Mientras la cola no esté vacía, hacer lo siguiente:
        while (!queue.isEmpty()) {
            time++;
            // 3. Sacar el primer nodo de la cola y verificar si es el nodo objetivo
            currentNode = queue.poll();

            if (visited.contains(currentNode.getState())) {
                continue;
            }

            visited.add(currentNode.getState());
            // System.out.println(NodeUtils.formatState(currentNode.getState()));
            if(currentNode.getState().equals(endState)) {
                System.out.println("Goal state found: " + currentNode.getState());
                // print the path from root to goal
                printPath(currentNode);
                printResults("Costo uniforme", currentNode.getDepth(), time, generated, startTime);
                return;
            }
            // 4. Si no es el nodo objetivo, generar sus hijos y agregarlos a la cola
            List<Node> children = NodeUtils.generateChildren(currentNode);
            for (Node child : children) {
                if (!visited.contains(child.getState())) {
                    child.setCost(child.getParent().getDepth() + 1);
                    queue.add(child);
                    generated++;
                }
            }
        }
        System.out.println("=======================================");
        System.out.println("Costo uniforme");
        System.out.println("Time: " + time);
        System.out.println("Estados visitados: " + visited.size());
        System.out.println("Stack: " + queue.size());
        System.out.println("=======================================");
    }

    // 4. Iterativa
    public void iterativeDeepeningSearch(int maxDepth) {
        int time = 0;
        int generated = 0;
        long startTime = System.currentTimeMillis();
        for (int limit = 0; limit <= maxDepth; limit++) {
            Map<String, Integer> visited = new HashMap<String, Integer>();
            // 1. Buscar el nodo raíz y agregarlo a la pila
            Node currentNode = root;
            Stack<Node> stack = new Stack<>();
            stack.push(currentNode);
            generated++;
            visited.put(currentNode.getState(), currentNode.getDepth());
            // 2. Mientras la pila no esté vacía, hacer lo siguiente:
            while (!stack.isEmpty()) {
                // 3. Sacar el nodo de la pila y verificar si es el nodo objetivo
                time++;
                currentNode = stack.pop();
                if(currentNode.getState().equals(endState)) {
                    System.out.println("Límite de profundidad donde se encontró: " + limit);
                    System.out.println("Goal state found: " + currentNode.getState());
                    // print the path from root to goal
                    printPath(currentNode);
                    printResults("Iterativa", currentNode.getDepth(), time, generated, startTime);
                    return;
                }
                // 4. Si no es el nodo objetivo y no se ha llegado al límite, generar sus hijos y agregarlos a la pila
                if (currentNode.getDepth() < limit) {
                    List<Node> children = NodeUtils.generateChildren(currentNode);
                    for (Node child : children) {
                        Integer previousDepth = visited.get(child.getState());
                        if (previousDepth == null || child.getDepth() < previousDepth) {
                            visited.put(child.getState(), child.getDepth());
                            stack.push(child);
                            generated++;
                        }
                    }
                }
            }
            System.out.println("Límite " + limit + ": sin solución");
        }
        System.out.println("No se encontró solución dentro del límite de profundidad: " + maxDepth);
    }

    private void printPath(Node node) {
        LinkedList<Node> path = new LinkedList<>();
        while (node != null) {
            path.addFirst(node);
            node = node.getParent();
        }

        if (path.size() > MAX_PATH_TO_PRINT) {
            System.out.println("El camino tiene " + (path.size() - 1) + " pasos (demasiado largo para imprimirlo).");
            return;
        }

        for (Node step : path) {
            System.out.println(NodeUtils.formatState(step.getState()));
        }
    }

    private void printResults(String nombreAlgoritmo, int profundidad, int procesados, int generados, long startTime) {
        long endTime = System.currentTimeMillis();
        double segundos = (endTime - startTime) / 1000.0;

        System.out.println("=======================================");
        System.out.println("RESULTADOS: " + nombreAlgoritmo);
        System.out.println("Meta encontrada en profundidad: " + profundidad);
        System.out.println("Nodos procesados (evaluados): " + procesados);
        System.out.println("Nodos generados (creados): " + generados);
        System.out.println("Tiempo real: " + segundos + " segundos");
        System.out.println("=======================================");
    }

}

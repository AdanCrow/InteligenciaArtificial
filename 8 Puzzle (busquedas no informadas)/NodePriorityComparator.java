package arbol;

import java.util.Comparator;

public class NodePriorityComparator implements Comparator<Node> {

//    @Override
//    public int compare(Node n1, Node n2) {
//        return Integer.compare(n1.getCost(), n2.getCost());
//    }

    @Override
    public int compare(Node n1, Node n2) {
        return n1.getCost() > n2.getCost() ? 1 : (n1.getCost() < n2.getCost() ? -1 : 0);
    }
}

package arbol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Node implements Comparable<Node>{
    private String state;
    private Node parent;
    private int depth;
    private int cost;

    public Node(String state, Node parent) {
        this.state = state;
        this.parent = parent;
        this.depth = (parent == null) ? 0 : parent.getDepth() + 1;
        this.cost = 0;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.depth, other.depth);
    }
}

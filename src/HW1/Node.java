package HW1;

public class Node {
    private State state;
    private Node parent;
    private Action action;

    public Node(State state, Node parent, Action action) {
    }

    public Node getParent() {
        return this.parent;
    }

    public Action getAction() {
        return this.action;
    }

    public State getState() {
        return this.state;
    }

    public Node[] expand() {
        Action[] arr = state.actions();
        Node[] expanded = new Node[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            //  expanded[i] = new Node();
        }
        return null;
    }

    public int heuristicValue() {
        // TODO - implement
        return 0;
    }
}

package HW1;

public class Node {
    private State state;
    private Node parent;
    private Action action;

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
        // TODO - implement
        return null;
    }

    public int heuristicValue() {
        // TODO - implement
        return 0;
    }
}

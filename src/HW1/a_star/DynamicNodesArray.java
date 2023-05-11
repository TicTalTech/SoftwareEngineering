package HW1.a_star;

import HW1.math.Int2;

public class DynamicNodesArray {
    private NodeCell[] nodes;

    public DynamicNodesArray() {
        nodes = new NodeCell[20];
    }

    public boolean isEmpty(int i) {
        return nodes[i] == null || !nodes[i].isActive();
    }

    public NodeCell[] getNodes() {
        return nodes;
    }

    public int findEmpty() {
        for (int i = 0; i < nodes.length; i++) {
            if (isEmpty(i)) {
                return i;
            }
        }
        return -1;
    }

    public void doubleArr() {
        NodeCell[] newArr = new NodeCell[nodes.length * 2];
        for (int i = 0; i < nodes.length; i++) {
            newArr[i] = nodes[i];
        }
        nodes = newArr;
        System.out.println("The new length is " + nodes.length);
    }

    public NodeCell newNode(Tile tile) {
        int index = findEmpty();
        if (findEmpty() == -1) {
            doubleArr();
            index = findEmpty();
        }
        if (nodes[index] == null) {
            nodes[index] = new NodeCell();
        }
        nodes[index].setTile(tile);
        nodes[index].setActive(true);
        return nodes[index];
    }
}



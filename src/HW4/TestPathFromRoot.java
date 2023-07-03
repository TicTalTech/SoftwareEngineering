package HW4;


public class TestPathFromRoot {

    public static <E> void printBinaryTree(BinNode<E> root) {
        printBinaryTreeRecursive(root, "", "");
    }
    private static <E> void printBinaryTreeRecursive(BinNode<E> node, String prefix, String childPrefix) {
        if (node != null) {
            System.out.println(prefix + node.getData());

            BinNode<E> leftChild = node.getLeft();
            BinNode<E> rightChild = node.getRight();

            if (leftChild != null && rightChild != null) {
                System.out.println(childPrefix + "├─ Left: ");
                printBinaryTreeRecursive(leftChild, childPrefix + "│  ", childPrefix + "│  ");
                System.out.println(childPrefix + "└─ Right: ");
                printBinaryTreeRecursive(rightChild, childPrefix + "   ", childPrefix + "   ");
            } else if (leftChild != null) {
                System.out.println(childPrefix + "└─ Left: ");
                printBinaryTreeRecursive(leftChild, childPrefix + "   ", childPrefix + "   ");
            } else if (rightChild != null) {
                System.out.println(childPrefix + "└─ Right: ");
                printBinaryTreeRecursive(rightChild, childPrefix + "   ", childPrefix + "   ");
            }
        }
    }
    public static void main(String[] args) {
        BinNode<Character> root = new BinNode<>('h');
        root.setLeft(new BinNode<>('e'));
        root.setRight(new BinNode<>('a'));
        root.getLeft().setLeft(new BinNode<>('B', new BinNode<>('k'), null));
        root.getLeft().setRight(new BinNode<>('y', new BinNode<>('M'), new BinNode<>('!')));
        printBinaryTree(root);

        String[] inputs = {"hey", "Hey", "h", "k", "aaa", "hE", "hA", "ha", "@123", "he", "hex"};
        String[] trueInputs = {"h", "he", "heB", "heBk", "heyM", "hey!", "ha", ""};
        String[] falseInputs = {"o", "e", "hb", "hB", "hBk", "H", "hea", "heBl", "heyk", "HEY!", "HA", "hey!j", "hey!!",
        "hello", "hebk", "yeh", "!yeh", " n", " e", " hey!", " h", " ha", "y!"};



        for (String input : falseInputs) {
            boolean result = PathFromRoot.doesPathExist(root, input);
            System.out.println("Path \"" + input + "\"? " + result);
        }

        System.out.println();

    }
}

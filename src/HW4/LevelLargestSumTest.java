package HW4;

import static HW4.LevelLargestSum.getLevelWithLargestSum;

public class LevelLargestSumTest {
    public static void test() {
        BinNode<Integer> root = new BinNode<>(1);
        BinNode<Integer> left = new BinNode<>(-20);
        BinNode<Integer> right = new BinNode<>(3);
        root.setLeft(left);
        root.setRight(right);
        left = new BinNode<>(4);
        right = new BinNode<>(5);
        root.getLeft().setLeft(left);
        root.getLeft().setRight(right);
        left = new BinNode<>(6);
        right = new BinNode<>(-50);
        root.getRight().setLeft(left);
        root.getRight().setRight(right);
        left = new BinNode<>(60);
        root.getLeft().getRight().setLeft(left);
        int sum = getLevelWithLargestSum(root);
        System.out.println("level:" + sum);
    }

    public static void print(BinNode<Integer> root) {
        if (root == null)
            return;
        print(root.getLeft());
        System.out.println(root.getData());
        print(root.getRight());
    }

    public static void main(String[] args) {
        test();
    }
}



package HW4;

import HW1.Node;

import java.util.ArrayDeque;

public class LevelLargestSum {
    public static int getLevelWithLargestSum(BinNode<Integer> root) {
        if (root == null)
            return -1;
        ArrayDeque<BinNode<Integer>> arr = new ArrayDeque<>();
        int maxSum = Integer.MIN_VALUE;
        int levelWithLargestSum = 0;
        int level = 0;
        int sum = 0;
        BinNode<Integer> startOfLevel = null;
        BinNode<Integer> left;
        BinNode<Integer> right;
        arr.addLast(root);
        while (!arr.isEmpty()) {
            if (startOfLevel != null && startOfLevel == arr.getFirst()) {
                if (sum > maxSum) {
                    maxSum = sum;
                    levelWithLargestSum = level;
                }
                level++;
                startOfLevel = null;
                sum = 0;
            }
            left = arr.getFirst().getLeft();
            right = arr.getFirst().getRight();
            if (left != null) {
                arr.addLast(left);
                if (startOfLevel == null) {
                    startOfLevel = left;
                }
            }
            if (right != null) {
                arr.addLast(right);
                if (startOfLevel == null) {
                    startOfLevel = right;
                }
            }
            sum = sum + arr.getFirst().getData().intValue();
            arr.removeFirst();
        }
        if (sum > maxSum) {
            maxSum = sum;
            levelWithLargestSum = level;
        }
        return levelWithLargestSum;
    }
}


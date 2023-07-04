package HW4;

import java.util.ArrayDeque;

/**
 * A class containing a method that receives a binary tree and returns the level with the largest sum in the tree.
 */
public class LevelLargestSum {
    /**
     * A method that finds the level of a binary tree that has the largest sum.
     *
     * @param root the root of the binary tree
     * @return returns the level in the tree with the largest sum. If two levels have the same sum it returns the
     * first of the two. If the tree is empty it returns -1.
     */
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


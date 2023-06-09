/**
 * a class containing a method that check if a certain word exits in a binary tree
 */
public class PathFromRoot {
    /**
     * checks if the given string is in the binary tree (starting from the beginning of the tree)
     * @param root the root of the binary tree
     * @param str - the string that the method will look for
     * @return true if found the string in the binary tree, false otherwise
     */
     public static boolean doesPathExist(BinNode<Character> root, String str) {
         if ("".equals(str)) {
             return true;
         }
         char firstLetter = str.charAt(0);
         char rootLetter = root.getData();
         if (firstLetter != rootLetter) {
             return false;
         }
         if (str.length() == 1) {
             return true;
         }
         if (root.getLeft() != null) {
             boolean foundString = doesPathExist(root.getLeft(), str.substring(1));
             if (foundString) {
                 return true;
             }
         }
         if (root.getRight() != null) {
             boolean foundString = doesPathExist(root.getRight(), str.substring(1));
             if (foundString) {
                 return true;
             }
         }
         return false;
      }

}

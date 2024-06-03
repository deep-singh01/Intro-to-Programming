import java.util.*;

public class Sec13 {
   public static void printNumbers() {
      printNums(2,2,"");
   }
   
   private static void printNums(int twos, int fives, String num) {
      if (twos == 0 && fives == 0) {
         System.out.println(num);
      } else if (twos >= 0 && fives >= 0) {
         printNums(twos-1, fives, num + "2");
         printNums(twos, fives-1, num + "5");
      }
   }
   
   private int numLeaves() {
      numLeaves(overallRoot);
   }
   
   private int numLeaves(IntTreeNode root) {
      if (root == null) {
         return 0;
      } else if (root.left == null && root.right == null) {
         return 1;
      } else {
         return numLeaves(root.left) + numLeaves(root.right);
      }
   }
   
   private void writeTree() {
      writeTree(overallRoot);
   }
   
   private void writeTree(IntTreeNode root) {
      if (root != null) {
         int type = 0;
         if (root.left != null) {
            type++;
         }
         if (root.right != null) {
            type += 2;
         }
         System.out.println(type + " " + root.data);
         writeTree(root.left);
         writeTree(root.right);
      }
   }
}
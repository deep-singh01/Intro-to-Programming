// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: Huffman
// Class HuffmanTree can be used to compress text files using a Huffman
// coding scheme as well as write the coded files and read the files
// back into text.

import java.util.*;
import java.io.*;

public class HuffmanTree {
   
   // starting node of tree of frequency/character pairs
   private HuffmanNode rootH; 
   
   // post: constructs huffman tree using given array (count)
   // parameters: int[] count - non-null array of frequencies with their 
   //             respective character (frequecny: count[i] -> character:i)
   public HuffmanTree(int[] count) {
      Queue<HuffmanNode> qTree = new PriorityQueue<>();
     
      for(int i = 0; i < count.length; i++) {
         if (count[i] > 0) {
            qTree.add(new HuffmanNode(i, count[i]));
         }
      }
      
      qTree.add(new HuffmanNode(count.length, 1));
      
      while (qTree.size() > 1) {
         HuffmanNode letter1 = qTree.remove();
         HuffmanNode letter2 = qTree.remove();
         HuffmanNode combo = new HuffmanNode(letter1.frequency + letter2.frequency);
         combo.left = letter1;
         combo.right = letter2;
         qTree.add(combo);
      }
      
      rootH = qTree.remove();
   }
   
   // post: constructs huffman tree using given file (input)
   // parameters: Scanner input - non-null readable file in 
   //             standard format (transversal order)
   public HuffmanTree(Scanner input) {   
      rootH = null;
      
      while (input.hasNextLine()) {
         int letter = Integer.parseInt(input.nextLine());
         String code = input.nextLine();
         rootH = createTree(rootH, letter, code);
      }
   }
   
   // post: Adds huffman node to tree based on given code
   // parameters: HuffmanNode root - root of given tree
   //             int letter - given character
   //             String code  - code for placing node with given
   //             character (letter) into tree
   private HuffmanNode createTree(HuffmanNode root, int letter, String code) {
      if (code.length() == 0) {
         return new HuffmanNode(letter, -1);
         
      } else {
         if (root == null) {
            root = new HuffmanNode(-1);
         }
         
         if (code.charAt(0) == '0') {            
            root.left = createTree(root.left, letter, code.substring(1));
            
         } else {            
            root.right = createTree(root.right, letter, code.substring(1));
         }
      }
      
      return root;
   }
   
   // post: writes huffman tree onto given file (output) 
   // in standard format
   // parameters: PrintStream output - writeable file
   public void write(PrintStream output) {
      write(rootH, output, "");
   }
   
   // post: writes huffman tree onto given file (output)
   // in standard format (with each node's respective code)
   // paramters: Huffman root - root of given tree
   //            PrintStream output - writeable file
   //            String code - traversal code to locate a given node
   private void write(HuffmanNode root, PrintStream output, String code) {
      if (root != null) {
         if (root.left == null && root.right == null) {
            output.println(root.letter);
            output.println(code);
            
         } else {
            write(root.left, output, code + "0");
            write(root.right, output, code + "1");
         }
      }
   }
   
   // post: Reads bits from input file and writes characters onto output 
   //       file until it reaches the eof value
   // parameters: BitInputStream input - non-null input file containing
   //             coded form of characters
   //             PrintStream output - non-null writeable output file 
   //             int eof - special charcter to signify the end of the file
   public void decode(BitInputStream input, PrintStream output, int eof) {
      HuffmanNode curr = rootH;
      
      while (curr.letter != eof) {
         if (curr.left == null && curr.right == null) {
            output.write(curr.letter);
            curr = rootH;
         }
         
         if (input.readBit() == 1) {
            curr = curr.right;
            
         } else {
            curr = curr.left;
         }
      }
   }
}
// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: Huffman
// Class HuffmanNode can be used to store a given character
// and its frequency in some file and can be used to compare
// two HuffmanNodes.

public class HuffmanNode implements Comparable<HuffmanNode> {

   public int letter; // stored character
   public int frequency; // frequency of stored character in file
   
   // two character nodes - used only if letter is a placeholder
   // character.
   public HuffmanNode left; // character with lesser frequency
   public HuffmanNode right; // character with greater frequency
   
   // post: constructs node with a placeholder character &
   // given frequecny
   // parameters: int frequency - given frequency
   public HuffmanNode(int frequency) {
      this(-1, frequency);
   }
   
   // post: constructs node with given character & its
   // given frequency
   // parameters: int letter - given character
   //             int frequency - frequency of given character
   public HuffmanNode(int letter, int frequency) {
      this.letter = letter;
      this.frequency = frequency;
   }
   
   // post: returns integer to compare priority of one node
   //       from another
   //       (The character node with lesser frequency takes
   //       precedence)
   // parameters: HuffmanNode other - character node being compared to           
   public final int compareTo(HuffmanNode other) {
      return (this.frequency - other.frequency);
   }
}
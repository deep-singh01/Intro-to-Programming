// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: LetterInventory
// Class LetteryInventory can be used to store the counts of letters
// in a string regardless of the case of the letters and whether it
// contains non-alphabetic characters.

public class LetterInventory {
   private int[] inventory; // list of counts of letters
   private int size;        // sum of all the counts of letters in the list
   
   public static final int LENGTH_OF_ALPHABET = 26;

   // post: constructs a list with all the counts of letters in data
   public LetterInventory(String data) {
      inventory = new int[LENGTH_OF_ALPHABET];
      data = data.toLowerCase();
      
      for (int i = 0; i < data.length(); i++) {
         char c = data.charAt(i);
         if (Character.isLetter(c)) {
            inventory[c - 'a']++;
            size++;
         }
      }
   }
   
   // pre: letter - alphabetic character, case insensitive
   //      (throws IllegalArgumentException if not)
   // post: returns count of letter
   public int get(char letter) {
      if (Character.isLetter(letter)) {
         letter = Character.toLowerCase(letter);
         return inventory[letter - 'a'];
      }
      
      throw new IllegalArgumentException();
   }
   
   // pre: letter - alphabetic character, case insensitive & value >= 0 
   //      (throws IllegalArgumentException if not)
   // post: sets count of letter to value
   public void set(char letter, int value) {
      if (!Character.isLetter(letter) || value < 0) {
         throw new IllegalArgumentException();
      }
      
      letter = Character.toLowerCase(letter);
      size -= inventory[letter - 'a'];
      inventory[letter - 'a'] = value;
      size += value;
   }
   
   // post: returns the sum of all the counts of letters in the list
   public int size() {
      return size;
   }
   
   // post: checks if list is empty
   public boolean isEmpty() {
      return (size == 0);
   }
   
   // post: creates a comma-separated, bracketed version of the list
   public String toString() {
      String s = "[";
      
      for (int i = 0; i < LENGTH_OF_ALPHABET; i++) {
         for (int j = 0; j < inventory[i]; j++) {
            s += (char) ('a' + i);
         }
      }
      
      s += "]";
      return s;
   }
   
   // pre: other - non-null LetterInventory
   // post: returns a new list that contains the sum of its counts
   //       and another list's for each letter
   //       (existing lists remain unchanged)
   public LetterInventory add(LetterInventory other) {
      LetterInventory newInventory = new LetterInventory("");
      
      for (int i = 0; i < LENGTH_OF_ALPHABET; i++) {
         newInventory.inventory[i] = inventory[i] + other.inventory[i];
         newInventory.size += newInventory.inventory[i];
      }
      
      return newInventory;
   }
   
   // pre: other - non-null LetterInventory
   // post: returns a new list that contains the difference of its counts
   //       and another list's for each letter
   //       (returns null if any difference is negative)
   //       (existing lists remain unchanged)
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory newInventory = new LetterInventory("");
      
      for (int i = 0; i < LENGTH_OF_ALPHABET; i++) {         
         if (inventory[i] - other.inventory[i] < 0) {
            return null;
         }
         
         newInventory.inventory[i] = inventory[i] - other.inventory[i];
         newInventory.size += newInventory.inventory[i];
      }
      
      return newInventory;
   }
}
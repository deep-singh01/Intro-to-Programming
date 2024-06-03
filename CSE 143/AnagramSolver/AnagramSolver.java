// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: Anagram Solver
// Class AnagramSolver can be used to find all combos of words from
// a given dictionary of a specified length that have same letters
// as a given word or phrase.

import java.util.*;

public class AnagramSolver {

   // map of words to inventories with their respective letters
   private Map<String, LetterInventory> words;
   // list of words that can be used in combos
   private List<String> dict; 
   
   // post: constructs a storage of all words in given list mapped
   //       to a inventory of their respective letters
   // parameters: List<String> list - non-null, non-empty,
   //             no duplicate-containing, non-changing list of words
   public AnagramSolver(List<String> list) {
      words = new HashMap<>();
      dict = list;
      
      for (String word: list) {         
         words.put(word, new LetterInventory(word));
      }
   }
   
   // pre: max >= 0 (throws IllegalArgumentException);
   // post: prints all possible combos of words of length max that have same
   //       letters (type/amount) of given string (s)
   //       (if max = 0, unlimited length on combos)
   // parameters: String s - word/phrase
   //             int max - max number of guesses
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException();
      }
      
      LetterInventory lettersOfS = new LetterInventory(s);
      List<String> newDict = new ArrayList<>();
      Stack<String> combos = new Stack<>();
      
      for (String word: words.keySet()) {
         if (lettersOfS.subtract(words.get(word)) != null) {
            newDict.add(word);
         }
      }
      
      print(max, combos, lettersOfS, newDict);
   }
   
   // post: prints all combos of word of length max that have the
   //       same letters (type/amount) as stored in lettersOfS using
   //       given dictionary (newDict)
   // parameters: int max - max number of guesses
   //             Stack<String> combos - current combo of words being used
   //             to make the given word/phrase
   //             LetterInventory lettersOfS - inventory consisting of the
   //             letters and their respective amounts in given word/phrase
   //             List<String> newDict - potential list of words that are a
   //             part of the given word
   private void print(int max, Stack<String> combos,
                     LetterInventory lettersOfS, List<String> newDict) {
      if (lettersOfS.size() == 0) {
         System.out.println(combos);
         
      } else {
         if (max == 0 || combos.size() < max) {
            for (String newWord: newDict) {
               LetterInventory newLettersOfS = lettersOfS.subtract(words.get(newWord));
               
               if (newLettersOfS != null) {
                  combos.add(newWord);
                  print(max, combos, newLettersOfS, newDict);
                  combos.pop();
               }
            }
         }
      }
   }
}
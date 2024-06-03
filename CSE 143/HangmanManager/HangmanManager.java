// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: Hangman Manager
// Class HangmanManager can be used to manage and track a game of evil
// hangman. Evil hangman is just like normal hangaman; however, unlike
// normal hangman, the computer doesn't chose a word until it is forced to.
// Hence, the computer has a set of words it can chose from at any given
// point.

import java.util.*;

public class HangmanManager {

   // current set of words in play
   private Set<String> inventory;
   // current set of letters that have been guessed
   private Set<Character> letters; 
   
   // current pattern of word being guessed
   private String pattern;
   // number of incorrect guesses left
   private int numIncGuesses;
   
   // pre: length > 1 & max >= 0
   //      (throw IllegalArgumentException if not)
   // post: initializes all words of given length from given dictionary
   //       into play without duplicates
   //       (if given dictionary is empty or doesn't contain words with
   //       given length, no words will be initialized)
   // parameters: Collection<String> dictionary - list of all possible words
   //             that can be used
   //             int length - length of word being guessed
   //             int max - maximum number of guesses
   public HangmanManager(Collection<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException();
      }
      
      pattern = "";
      numIncGuesses = max;
      inventory = new TreeSet<>();
      letters = new TreeSet<>();
      
      for (String word : dictionary) {
         if (word.length() == length) {
            inventory.add(word);
         }
      }
      
      for (int i = 0; i < length; i++) {
         pattern += "-";
      }   
   }
   
   // post: returns the current set of words in play
   public Set<String> words() {
      return inventory;
   }
   
   // post: returns the number of guesses left
   public int guessesLeft() {
      return numIncGuesses;
   }
   
   // post: returns the current set of all the letters that have been guessed
   public Set<Character> guesses() {
      return letters;
   }
   
   // pre: current set of words in play must not be empty
   //      (throws IllegalStateException if not)
   // post: returns a spaced out version of the current pattern for display
   //       where letters that haven't been guessed are shown as dashes
   public String pattern() {
      if (inventory.isEmpty()) {
         throw new IllegalStateException();
      }
      
      String spacedPattern = "" + pattern.charAt(0);
      
      for (int i = 1; i < pattern.length(); i++) {
         spacedPattern += " " + pattern.charAt(i);
      }
      
      return spacedPattern;
   }
   
   // pre: number of guesses left and current set of words in play must not
   //      be empty
   //      (throw IllegalStateException if not)
   //      & letter guessed must be letter that hasn't previously been guessed
   //      (throw IllegalArgumentException if not)
   // post: records new guess made, updates inventory to new appropiate set
   //       of words, updates current pattern of the word, updates number
   //       of guesses left, & returns number of occurences of guessed letter
   //       in the new patern
   public int record(char guess) {
      if (numIncGuesses < 1 || inventory.isEmpty()) {
         throw new IllegalStateException();
      }
      
      if (letters.contains(guess)) {
         throw new IllegalArgumentException();
      }
      
      letters.add(guess);
      update(guess);      
      return correctGuesses(guess);
   }
   
   // post: gets list of new patterns with their respective set of words
   //       & updates the current pattern and the current set of words 
   //       in play
   private void update(char guess) {
      int listSize = 0;
      Map<String, Set<String>> listOfPatterns = possiblePatterns(guess);
      
      for (String wordPattern : listOfPatterns.keySet()) {
         if (listOfPatterns.get(wordPattern).size() > listSize) {
            listSize = listOfPatterns.get(wordPattern).size();
            pattern = wordPattern;
            inventory = listOfPatterns.get(wordPattern);
         }
      }
   }
   
   // post: returns map of new patterns based on letter guessed with
   //       the set of words that follow each respective pattern
   private Map<String, Set<String>> possiblePatterns(char guess) {
      Map<String, Set<String>> listOfPatterns = new TreeMap<String,
         Set<String>>();
      
      for (String word : inventory) {
         String wordPattern = "";
         
         for (int i = 0; i < pattern.length(); i++) {
            if (guess != word.charAt(i)) {
               wordPattern += pattern.charAt(i);
            } else {
               wordPattern += guess;
            }
         }
         
         if (!listOfPatterns.containsKey(wordPattern)) {
            listOfPatterns.put(wordPattern, new TreeSet<String>());
         }
         
         listOfPatterns.get(wordPattern).add(word);
      }
      
      return listOfPatterns;
   }
   
   // post: returns number of times guessed letter occurs in current pattern
   private int correctGuesses(char guess) {
      int correct = 0;
      
      for (int i = 0; i < pattern.length(); i++) {
         if (guess == pattern.charAt(i)) {
            correct++;
         }
      }
      
      if (correct == 0) {
         numIncGuesses--;
      }
      
      return correct;
   }

}
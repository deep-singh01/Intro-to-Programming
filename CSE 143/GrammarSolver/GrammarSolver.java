// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: Grammar Solver
// Class GrammarSolver can be used to manipulate sequences of standard
// Backus-Naur Form to create various types of strings.

import java.util.*;

public class GrammarSolver {

   // map of nonterminal symbols to their respective rules
   private SortedMap<String, List<String[]>> nonTerminals;
   
   // pre: given list of strings (grammar) must not be empty &
   //      should not contain more than one entry of the same nonterminal
   //      (throws IllegalArgumentException if not)
   // post: generates a map to track nonterminal symbols and their
   //       respective rules
   // parameters: List<String> grammar - list of strings in Backus-Naur Form
   //             (remains unchanged)
   public GrammarSolver(List<String> grammar) {
      if (grammar.isEmpty()) {
         throw new IllegalArgumentException();
      }
      
      nonTerminals = new TreeMap<>();
   
      for (String line : grammar) {
         String[] parts = line.split("::=");
         
         if (nonTerminals.containsKey(parts[0])) {
            throw new IllegalArgumentException();
         }
         
         nonTerminals.put(parts[0], new ArrayList<String[]>());
         String[] terminals = parts[1].trim().split("[|]");
         
         for (int i = 0; i < terminals.length; i++) {
            nonTerminals.get(parts[0]).add(terminals[i].trim().split("[ \t]+"));
         }
      }
   }
    
   // post: returns whether nonTerminals (field) contains given 
   //       nonterminal symbol (case-sensitive)
   // parameters: String symbol - nonterminal symbol
   public boolean grammarContains(String symbol) {
      return nonTerminals.containsKey(symbol);
   }
   
   // pre: nonTerminals must contain given nonterminal symbol 
   //      (case-sensitive) & times must be zero or above
   //      (throws IllegalArgumentException if not) 
   // post: generates the requested number of instances (times) of 
   //       given symbol
   // parameters: String symbol - nonterminal symbol
   //             int times - number of sentences/words being generated
   public String[] generate(String symbol, int times) {
      if (!nonTerminals.containsKey(symbol) || times < 0) {
         throw new IllegalArgumentException();
      }
      
      String[] sentences = new String[times];
      Random rand = new Random();
      
      for (int i  = 0; i < times; i++) {
        sentences[i] = generate(symbol, rand).trim();
      }
      
      return sentences;      
   }

   // post: generates one instance of the given nonterminal symbol
   //       (case-sensitive)
   // parameters: String symbol - nonterminal symbol
   //             Random rand - random number generator
   private String generate(String symbol, Random rand) {
      if (!nonTerminals.containsKey(symbol)) {
         return " " + symbol;
         
      } else {
         int randNum = rand.nextInt(nonTerminals.get(symbol).size());
         String[] terminals = nonTerminals.get(symbol).get(randNum);
         String sentence = "";
         
         for (String term : terminals) {
            sentence += generate(term.trim(), rand);
         }
         
         return sentence;
      }
   }
   
   // post: returns a comma-separated bracketed string representation of all
   //       possible non-terminal symbols
   public String getSymbols() {
      return nonTerminals.keySet().toString();
   }
}

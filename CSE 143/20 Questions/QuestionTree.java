// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: 20 Questions
// Class QuestionTree can be used to manage and play a
// yes or no guessing game where the program will try to
// guess the thing the user is thinking of.

import java.util.*;
import java.io.*;

public class QuestionTree {
   
   private QuestionNode rootQ; // starting question/answer
   private Scanner console; // for user input
   
   // post: constructs question tree with one question node
   public QuestionTree() {
      console = new Scanner(System.in);
      rootQ = new QuestionNode("computer");
   }
   
   // post: reads new question tree to replace current question tree
   // parameters: Scanner input - legal, readable file in proper format
   public void read(Scanner input) {
      while (input.hasNextLine()) {
         rootQ = assistRead(input);
      }      
   }
   
   // post: reads in new info from given file to create
   //       new question tree       
   // parameters: Scanner input - non-empty, legal, readable file in 
   //             proper format         
   private QuestionNode assistRead(Scanner input) {
      String type = input.nextLine();
      String data = input.nextLine();
      QuestionNode root = new QuestionNode(data);
      
      if (type.equals("Q:")) {
         root.yes = assistRead(input);
         root.no = assistRead(input);
      }
      
      return root;
   }
   
   // post: writes question tree onto output file
   // parameters: PrintStream output - non-null, fillable, blank file        
   public void write(PrintStream output) {
      write(rootQ, output);
   }
   
   // post: writes question tree onto output file from given root
   // parameters: QuestionNode root - topmost node in question tree
   //             PrintStream output - non-null fillable file
   private void write(QuestionNode root, PrintStream output) {
      if (root.yes == null && root.no == null) {
         output.println("A:");
         output.println(root.data);
         
      } else {
         output.println("Q:");
         output.println(root.data);
         write(root.yes, output);
         write(root.no, output);
      }
   }
   
   // post: asks user questions starting from the first question/answer
   //       until a (wrong or right) answer is achieved
   //       (if answer given is wrong, expands question tree to include
   //       user's answer & a question to distinguish user's answer from
   //       wrong answer given)
   public void askQuestions() {
      rootQ = askQuestions(rootQ);
   }
   
   // post: asks user questions starting from given question/answer
   //       until a (wrong or right) answer is achieved
   //       (if answer given is wrong, expands question tree to include
   //       user's answer & a question to distinguish user's answer from
   //       wrong answer given)
   // parameters: QuestionNode root - top-most node in question tree
   private QuestionNode askQuestions(QuestionNode root) {
      if (root.yes != null && root.no != null) {
         if (yesTo(root.data)) {
            root.yes = askQuestions(root.yes);
            
         } else {
            root.no = askQuestions(root.no);
         }
         
      } else {
         if (yesTo("Would your object happen to be " + root.data + "?")) {
            System.out.println("Great, I got it right!");
            
         } else {
            root = addAnswer(root);
         }        
      }
      
      return root;
   }
   
   // post: returns new node with question to distinguish user's answer
   //       from wrong answer given by program
   // parameters: QuestionNode root - wrong answer in question tree
   private QuestionNode addAnswer(QuestionNode root) {
      System.out.print("What is the name of your object? ");
      QuestionNode currAns = new QuestionNode(console.nextLine());
      System.out.println("Please give me a yes/no question that");
      System.out.println("distinguishes between your object");
      System.out.print("and mine--> ");
      String q = console.nextLine();
      
      if (yesTo("And what is the answer for your object?")) {
         return new QuestionNode(q, currAns, root);
         
      } else {
         return new QuestionNode(q, root, currAns);
      }
   }   
   
   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   // parameters: String prompt - question being asked
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      
      return response.equals("y");
   }
}
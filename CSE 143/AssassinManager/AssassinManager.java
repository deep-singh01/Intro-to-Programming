// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: Assassin Manager
// Class AssassinManager can be used to start, manage, and
// track a game of assassin. Assassin is a game where the players
// are all assigned targets that they have to assassinate, which is
// the only person they know. After assassinating their target, they
// take on their target's target and so on.

import java.util.*;

public class AssassinManager {
   
   AssassinNode killRing;  // list of players alive
   AssassinNode graveyard; // list of players dead
   
   // pre: name must not be empty
   //      (throw IllegalArgumentException if not)
   // post: initializes players playing Assassin into the killRing
   // parameters: List<String> name - list of players playing Assassin
   public AssassinManager(List<String> name) {
      if (name.isEmpty()) {
         throw new IllegalArgumentException();
      }
      
      killRing = new AssassinNode(name.get(0));
      graveyard = null;
      AssassinNode curr = killRing;
      
      for (int i = 1; i < name.size(); i++) {
         curr.next = new AssassinNode(name.get(i));
         curr = curr.next;
      }
      
      curr.next = killRing;
   }
   
   // post: prints report on who is currently stalking who
   //       (if one remaining, reports player is stalking themselves)
   public void printKillRing() {
      AssassinNode curr = killRing;
      
      while (curr.next != killRing) {
         System.out.println("    " + curr.name + " is stalking " 
            + curr.next.name);
         curr = curr.next;
      }
      
      System.out.println("    " + curr.name + " is stalking " 
         + killRing.name);
   }
   
   // post: prints report on who was killed by who starting with
   //       the most recent victim to the oldest
   //       (no output if no one has been killed)
   public void printGraveyard() {
      AssassinNode curr = graveyard;
      
      while (curr != null) {
         System.out.println("    " + curr.name + " was killed by "
            + curr.killer);
         curr = curr.next;
      }
   }
   
   // post: returns whether player is alive (case insensitive)
   // parameters: String name - name of player in question
   public boolean killRingContains(String name) {
      if (killRing.name.equalsIgnoreCase(name)) {
         return true;
      }
      
      AssassinNode curr = killRing.next;
      while (curr != killRing) {
         if (curr.name.equalsIgnoreCase(name)) {
            return true;
         }
         
         curr = curr.next;
      }
      
      return false;
   }
   
   // post: returns whether player is dead (case insensitive)
   // parameters: String name - name of player in question
   public boolean graveyardContains(String name) {
      AssassinNode curr = graveyard;
      
      while (curr != null) {
         if (curr.name.equalsIgnoreCase(name)) {
            return true;
         }
         
         curr = curr.next;
      }
      
      return false;
   }
   
   // post: returns if game is over
   public boolean gameOver() {
      return (killRing.next == killRing);
   }
   
   // post: returns winner of the game
   //       (returns null if game isn't over)
   public String winner() {
      if (gameOver()) {
         return killRing.name;
      }
      
      return null;
   }
   
   // pre: given player must be alive
   //      (throws IllegalArgumentException if not)
   //      & game must not be over
   //      (throws IllegalStateException if not)
   // post: kills given player & sends them to the graveyard
   //       (killRing order remains unchanged)
   // parameters: String name - name of player being killed 
   //             (case insensitive)
   public void kill(String name) {
      if (!killRingContains(name)) {
         throw new IllegalArgumentException();
      }
      
      if (gameOver()) {
         throw new IllegalStateException();
      }
      
      AssassinNode killer = killRing;
      
      while (!killer.next.name.equalsIgnoreCase(name)) {
         killer = killer.next;
      }
      
      if (killer.next == killRing) {
         killRing = killRing.next;
      }
            
      killer.next.killer = killer.name;
      AssassinNode victim = killer.next;
      killer.next = killer.next.next;
      victim.next = graveyard;
      graveyard = victim;      
   }
}

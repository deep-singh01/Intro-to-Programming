// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: GuitarHero
// Class Guitar37 can be used to model and play
// a guitar with 37 unique strings.

public class Guitar37 implements Guitar {

   private GuitarString[] guitar; // list consisting of guitar strings
   private int tics; // current time
   
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; 
   
   // post: constructs a guitar with 37 unique strings
   public Guitar37() {
      guitar = new GuitarString[37];
      tics = 0;
      
      for (int i = 0; i < guitar.length; i++) {
         double frequency = 440.0 * Math.pow(2, (i - 24.0) / 12.0);
         guitar[i] = new GuitarString(frequency);
      }
   }
   
   // pre: int pitch - pitch of note wanting to be played 
   // post: plays a note based on given pitch and 
   //       whether pitch is valid
   public void playNote(int pitch) {
      int index = pitch + 24;
      
      if (index >= 0 && index < guitar.length) {
         guitar[index].pluck();
      }      
   }
   
   // pre: char key - musical key
   // post: checks if guitar has given key
   public boolean hasString(char key) {
      if (KEYBOARD.indexOf(key) == -1) {
         return false;
      }
      
      return true;
   }
   
   // pre: char key - musical key wanting to be played
   //      guitar must have given key
   //      (throws IllegalArgumentException if not)
   // post: plays given key on guitar
   public void pluck(char key) {
      if (!hasString(key)) {
         throw new IllegalArgumentException();
      }
      
      int index = KEYBOARD.indexOf(key);
      guitar[index].pluck();
   }
   
   // post: returns current sound sample
   public double sample() {
      double currSample = 0.0;
      
      for (int i = 0; i < guitar.length; i++) {
         currSample += guitar[i].sample();
      }
      
      return currSample;
   }
   
   // post: furthers time by one tic
   public void tic() {
      for (int i = 0; i < guitar.length; i++) {
         guitar[i].tic();
      }
      
      tics++;
   }
   
   // post: returns the current time
   public int time() {
        return tics;
   }
}
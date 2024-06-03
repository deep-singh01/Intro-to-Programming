// Deepesh Singh
// CSE 143
// Section AR - Adithi Raghavan
// Project: GuitarString
// Class GuitarString can be used to model and play
// a guitar string of a given frequency using the
// the ring buffer feedback mechanism.

import java.util.*;

public class GuitarString {
   
   // guitar string of specified frequency
   private Queue<Double> ringBuffer = new LinkedList<>();
   
   public static final double ENERGY_DECAY_FACTOR = 0.996;

   // pre: double frequency - frequency of guitar string
   //      0 < frequency & desired capacity of ring buffer >= 2
   //      (throws IllegalArgumentException if not)
   // post: creates a guitar string for the given frequency
   public GuitarString(double frequency) {
      int N = (int) Math.round(StdAudio.SAMPLE_RATE/frequency);
      
      if (frequency <= 0 || N < 2) {
         throw new IllegalArgumentException();
      }
      
      for (int i = 0; i < N; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   // pre: double[] init - array of values inputed into ring buffer
   //      length of init >= 2
   //      (throws IllegalArgumentException if not)
   // post: creates a guitar string using values given in the array
   public GuitarString(double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }
      
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // post: plays the guitar string
   public void pluck() {
      Random r = new Random();
      int size = ringBuffer.size();
      
      for (int i = 0; i < size; i++) {
         ringBuffer.add(ringBuffer.remove() + (r.nextDouble() - 0.5));
      }
   }
   
   // post: furthers time for guitar string by one tic
   //       (executes the Karplus-Strong algorithm once on ring buffer)
   public void tic() {
      double avg = (ringBuffer.remove() + ringBuffer.peek())/2.0;
      ringBuffer.add(ENERGY_DECAY_FACTOR * avg);
   }
   
   // post: returns guitar string sample
   //       (first value in ring buffer)
   public double sample() {
      return ringBuffer.peek();
   }
}
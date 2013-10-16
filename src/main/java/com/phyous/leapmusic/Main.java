package com.phyous.leapmusic;

import com.leapmotion.leap.*;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
//    // Create a sample listener and controller
//    SampleListener listener = new SampleListener();
//    Controller controller = new Controller();
//
//    // Have the sample listener receive events from the controller
//    controller.addListener(listener);
//
//    // Keep this process running until Enter is pressed
//    System.out.println("Press Enter to quit...");
//    try {
//      System.in.read();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    // Remove the sample listener when done
//    controller.removeListener(listener);
    playNotes();
  }

  private static void playNotes(){
    Piano p = PianoSingleton.getInstance();
    for(int i = 0; i < 20; i++) {
      p.playNote(50+i, 127, 1000);
      try {
       Thread.sleep(100);
      }  catch (InterruptedException e) {

      }
    }
    p.closePiano();
  }
}

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
    playNote();
  }

  private static void playNote(){
    int nChannelNumber = 0; // (0 -15)
    int	nNoteNumber = 50;	// MIDI key number (0-127)
    int	nVelocity = 127; // (0-127)
    int	nDuration = 1000; // in ms


    Synthesizer synth = null;
    try {
      synth = MidiSystem.getSynthesizer();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
      System.exit(1);
    }

    try {
      synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
      System.exit(1);
    }

    MidiChannel[] channels = synth.getChannels();
    MidiChannel channel = channels[nChannelNumber];
    channel.noteOn(nNoteNumber, nVelocity);


    try {
      Thread.sleep(nDuration);
    } catch (InterruptedException e) {
    }

    channel.noteOff(nNoteNumber);
    synth.close();
  }
}

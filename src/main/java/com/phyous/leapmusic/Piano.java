package com.phyous.leapmusic;


import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Piano {
  private int nChannelNumber;
  private int nNoteNumber;
  private int nVelocity;
  private int nDuration;
  private MidiChannel channel;
  private MidiChannel[] channels;
  Synthesizer synth = null;

  public Piano() {
    nChannelNumber = 0; // (0 -15)
    nNoteNumber = 50;  // MIDI key number (0-127)
    nVelocity = 127; // (0-127)
    nDuration = 1000; // duration of notes in ms

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

    channels = synth.getChannels();
    channel = channels[nChannelNumber];
  }

  public void playNote(int noteNumber, int velocity, int durationMs) {
    channel.noteOn(nNoteNumber, nVelocity);
//    try {
//      Thread.sleep(nDuration);
//    } catch (InterruptedException e) {
//    }
  }

}
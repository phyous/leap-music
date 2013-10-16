package com.phyous.leapmusic;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Piano {
  private int nChannelNumber;
  private MidiChannel channel;
  private MidiChannel[] channels;
  Synthesizer synth = null;
  ExecutorService executor = null;

  public Piano() {
    nChannelNumber = 0; // (0 -15)

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
    executor = Executors.newFixedThreadPool(channels.length);
  }

  /**
   * Play a single note on the piano
   * @param noteNumber between 0 - 127
   * @param velocity between 0 -127
   * @param durationMs length in Ms
   */
  public void playNote(int noteNumber, int velocity, int durationMs) {
    Runnable note = new PianoNoteThread(channel, noteNumber, velocity, durationMs);
    executor.execute(note);
  }

  /**
   * Close the piano, killing any sounds that might be playing.
   */
  public void closePiano() {
    executor.shutdown();
    synth.close();
  }

  private class PianoNoteThread implements Runnable {
    private int mNoteNumber;
    private int mVelocity;
    private int mDurationMs;
    private MidiChannel mChannel;

    public PianoNoteThread(MidiChannel c, int noteNumber, int velocity, int durationMs) {
      this.mNoteNumber = noteNumber;
      this.mVelocity = velocity;
      this.mDurationMs = durationMs;
      this.mChannel = c;
    }

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + " Start note " + mNoteNumber);
      processCommand();
      System.out.println(Thread.currentThread().getName() + " End note " + mNoteNumber);
    }

    private void processCommand() {
      mChannel.noteOn(mNoteNumber, mVelocity);
      try {
        Thread.sleep(mDurationMs);
      } catch (InterruptedException e) {
      }
    }

    @Override
    public String toString() {
      return String.format("%s %s %s", mNoteNumber, mVelocity, mDurationMs);
    }
  }
}
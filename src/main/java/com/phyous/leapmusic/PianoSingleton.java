package com.phyous.leapmusic;

public class PianoSingleton {
  private static volatile Piano instance = null;

  private PianoSingleton() {}

  public static Piano getInstance() {
    if (instance == null) {
      synchronized (Piano.class){
        if (instance == null) {
          instance = new Piano();
        }
      }
    }
    return instance;
  }


}

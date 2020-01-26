package com.example.nepal_app.UI.Fragments;

import android.media.MediaPlayer;

public class Afspilning {
  private static MediaPlayer mp;

  public static void start(MediaPlayer mp1) {
    start(mp1, 0);
  }

  public static void start(MediaPlayer mp1, int skipStart) {
    start(mp1, skipStart, null);
  }

  public static void start(MediaPlayer mp1, int skipStart, final MediaPlayer.OnCompletionListener ocl) {
    stop();
    try {
      mp = mp1;
      if (skipStart>0) {
        mp.seekTo(skipStart); // Der er tavshed de første 0,7 sekunder af filerne - så hop over dem
      }
      mp.start();
      mp.setOnCompletionListener(mpx -> {
        try {
          if (ocl!=null) ocl.onCompletion(null);
          mp.release();
          mp = null;
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();  // fang alle fejl og log dem, men lad være at crashe app'en
    }
  }

  public static void stop() {
    if (mp != null) try {
      mp.stop(); // Stop tidligere afspilning
    } catch (Exception e) {
      e.printStackTrace();  // fang alle fejl og log dem, men lad være at crashe app'en
    }
  }
}

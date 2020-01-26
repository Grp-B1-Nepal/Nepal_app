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
      mp.setOnCompletionListener(mpx -> {
        try {
          if (ocl != null) ocl.onCompletion(null);
          mp.reset();
          mp.release();
          mp = null;
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
      mp.start();
    } catch (Exception e) {
      e.printStackTrace();  // fang alle fejl og log dem, men lad være at crashe app'en
    }
  }

  public static void stop() {
    if (mp != null) try {
      mp.stop(); // Stop tidligere afspilning
      mp.reset();
      mp.release();
      mp = null;
    } catch (Exception e) {
      e.printStackTrace();  // fang alle fejl og log dem, men lad være at crashe app'en
    }
  }
}

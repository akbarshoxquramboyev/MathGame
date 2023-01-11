package com.example.mathforeveryone.sizecomp;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.mathforeveryone.R;

public class Mplayer {
    public static MediaPlayer mainSound;
    public static void MplayerCreate(Context context){
        mainSound = MediaPlayer.create(context, R.raw.main_music);
        mainSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
    }
}

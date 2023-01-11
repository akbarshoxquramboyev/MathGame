package com.example.mathforeveryone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.mathforeveryone.sizecomp.RandNubber;

public class RightAnswer extends AppCompatActivity {

    Handler handler;
    SharView sharView;
    MediaPlayer mplR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharView = new SharView(this);
        setContentView(sharView);
        RandNubber.test_count++;
        RandNubber.olqishlash = 0;
        mplR = MediaPlayer.create(this, R.raw.clapping);
        mplR.start();
        handler = new Handler();
        handler.postDelayed(runnable, 4000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        mplR.stop();
        finish();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mplR.stop();
            handler.removeCallbacks(this);
            Intent intent = new Intent(RightAnswer.this, GameAdd.class);
            switch (getIntent().getStringExtra("game")) {
                case "Subtract":
                    intent = new Intent(RightAnswer.this, GameSubtract.class);
                    break;
                case "Add":
                    intent = new Intent(RightAnswer.this, GameAdd.class);
                    break;
                case "Sequence":
                    intent = new Intent(RightAnswer.this, GameSequence.class);
                    break;
                case "missing":
                    intent = new Intent(RightAnswer.this, FindMissingNumber.class);
                    break;
                case "ketmaketlik":
                    intent = new Intent(RightAnswer.this, KenmaKetlik.class);
                    break;

            }
            if(RandNubber.test_count>3)
                intent = new Intent(RightAnswer.this, ChooseCategory.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    };
}
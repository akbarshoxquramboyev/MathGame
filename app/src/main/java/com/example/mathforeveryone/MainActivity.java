package com.example.mathforeveryone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mathforeveryone.sizecomp.MetricsDevices;
import com.example.mathforeveryone.sizecomp.Mplayer;
import com.example.mathforeveryone.sizecomp.RandNubber;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Handler handler;
    ConstraintLayout.LayoutParams layoutParams;
    int anim_time, anim_loop;
    float widthPixels, heightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        RandNubber.RandNubberNew();
        Mplayer.MplayerCreate(this);
        Mplayer.mainSound.start();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        widthPixels = metrics.widthPixels;
        heightPixels = metrics.heightPixels;
        if(heightPixels<widthPixels){
            float alm = widthPixels;
            widthPixels = heightPixels;
            heightPixels = alm;
        }
        float density = metrics.density;
        float dpWidht = widthPixels/density;
        float dpHeight = heightPixels/density;
        MetricsDevices.pxWidht = widthPixels;
        MetricsDevices.pxHeight = heightPixels;
        MetricsDevices.density = density;
        MetricsDevices.dpWidht = dpWidht;
        MetricsDevices.dpHeight = dpHeight;
        anim_time = 0;

        button1 = findViewById(R.id.button1);
        layoutParams = (ConstraintLayout.LayoutParams) button1.getLayoutParams();
        layoutParams.width = (int) (widthPixels * 0.30 * 1.35);
        layoutParams.height = (int) (widthPixels * 0.30);
        button1.setLayoutParams(layoutParams);
        handler = new Handler();
        anim_loop = 2;
        handler.postDelayed(runnable, 2000);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChooseCategory.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!Mplayer.mainSound.isPlaying()) {
//            Mplayer.MplayerCreate(this);
            Mplayer.mainSound.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Mplayer.mainSound.pause();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            switch (anim_time){
                case 0:
                    if(anim_loop >= 25) {
                        anim_time = 1;
                        anim_loop = 1;
                        handler.postDelayed(this::run, 200);
                    } else {
                        layoutParams.width = (int) (widthPixels * 0.30 * 1.35) + anim_loop*4;
                        layoutParams.height = (int) (widthPixels * 0.30) - anim_loop*2;
                        button1.setLayoutParams(layoutParams);
                        //2 - 8 // 4 - 16 // 6 - 24
                        //4    // 4      // 4
                        button1.setX(button1.getX() - 4);
                        anim_loop+=2;
                        handler.postDelayed(this::run, 1);
                    }
                    break;
                case 1:
                    if(anim_loop == 40){
                        anim_time = 2;
                        anim_loop = 1;
                        handler.postDelayed(this::run, 50);
                    } else {
                        layoutParams.width = (int) (widthPixels * 0.30 * 1.35+100) - anim_loop*4;
                        layoutParams.height = (int) (widthPixels * 0.30-50)+anim_loop*2;
                        button1.setLayoutParams(layoutParams);
                        button1.setX(button1.getX() + 2);
                        anim_loop++;
                        handler.postDelayed(this::run, 1);
                    }
                    break;
                case 2:
                    if(anim_loop == 15) {
                        anim_time = 0;
                        anim_loop = 1;
                        handler.postDelayed(this::run, 700);
                    }else {
                        layoutParams.width = (int) (widthPixels * 0.30 * 1.35-60)+anim_loop*4;
                        layoutParams.height = (int) (widthPixels * 0.30+30)-anim_loop*2;
                        button1.setLayoutParams(layoutParams);
                        button1.setX(button1.getX() - 2);
                        anim_loop++;
                        handler.postDelayed(this::run, 1);
                    }
                    break;
            }
        }
    };
}
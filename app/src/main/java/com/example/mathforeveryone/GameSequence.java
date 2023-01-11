package com.example.mathforeveryone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mathforeveryone.sizecomp.MetricsDevices;
import com.example.mathforeveryone.sizecomp.RandNubber;

import java.util.Random;

public class GameSequence extends AppCompatActivity {

    private LinearLayout linearLayout, inner_linerLayout1, inner_linerLayout2;
    private ImageView imageViews[];
    int k, a, answers[], right_ans, res_fruit;
    MetricsDevices devices;
    Handler handler;
    TextView imageAnswer;
    MediaPlayer mplR, mplW;
    boolean space_two_ans;
    private LinearLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_sequence);

        devices = new MetricsDevices();

        ImageView exitAddGame = findViewById(R.id.exitSequenceGame);
        exitAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        linearLayout = findViewById(R.id.line_lay_sequence);
        inner_linerLayout1 = new LinearLayout(this);
        inner_linerLayout2 = new LinearLayout(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        inner_linerLayout1.setLayoutParams(params1);
        inner_linerLayout2.setLayoutParams(params1);
        inner_linerLayout1.setOrientation(LinearLayout.HORIZONTAL);
        inner_linerLayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(inner_linerLayout1);

        TextView answer1, answer2, answer3, answer4;
        answer1 = findViewById(R.id.sequence_img1);
        answer2 = findViewById(R.id.sequence_img2);
        answer3 = findViewById(R.id.sequence_img3);
        answer4 = findViewById(R.id.sequence_img4);
        answer1.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer2.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer3.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer4.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer1.setOnClickListener(listener);
        answer2.setOnClickListener(listener);
        answer3.setOnClickListener(listener);
        answer4.setOnClickListener(listener);

        imageAnswer = findViewById(R.id.image_answer);
        imageAnswer.setTextSize((float) (MetricsDevices.dpWidht*0.15));

        mplR = MediaPlayer.create(this, R.raw.correct2);
        mplW = MediaPlayer.create(this, R.raw.wrong);

//        olmalarni chiqarish uchun ImageView va uning parametrlari
        imageViews = new ImageView[35];
        int imgPx = (int) (devices.getWidhtPixsel()*0.4*0.24);
        int margnPx = (int) (devices.getWidhtPixsel()*0.4*0.05);
        params = new LinearLayout.LayoutParams(imgPx, imgPx);
        params.setMargins(margnPx, margnPx,0,0);

        handler = new Handler();
        Random random = new Random();
        int rand_res = random.nextInt(10);
        randomRes(rand_res);
        a = random.nextInt(10)+1;
//        System.out.println(a+" ---");
        answers = new int[4];
        right_ans = random.nextInt(4);
        boolean[] used_ans = new boolean[11];
        for (int i = 0; i < 11; i++) {
            used_ans[i] = false;
        }
        used_ans[a] = true;
        for (int i = 0; i < 4; i++) {
            if(i==right_ans){
                answers[i] = a;
            } else {
                while (true) {
                    int d = random.nextInt(10);
                    if (!used_ans[d]) {
                        used_ans[d] = true;
                        answers[i] = d;
                        break;
                    }
                }
            }
        }
        answer1.setText(answers[0]+"");
        answer2.setText(answers[1]+"");
        answer3.setText(answers[2]+"");
        answer4.setText(answers[3]+"");

        runnable.run();
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GameSequence.this, ChooseCategory.class);
        startActivity(intent);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!space_two_ans) {
                TextView ans = (TextView) view;
                imageAnswer.setText(ans.getText().toString());
                imageAnswer.setBackground(getResources().getDrawable(R.drawable.ic_rectangle_4288));
                if (Integer.parseInt(ans.getText().toString()) != answers[right_ans]) {
                    mplW.start();
                    space_two_ans = true;
                    handler.postDelayed(wrongAns, 1000);
                } else {
                    mplR.start();
                    space_two_ans = true;
                    handler.postDelayed(rightAns, 1000);
                }
            }
        }
    };

    private Runnable wrongAns = new Runnable() {
        @Override
        public void run() {
            imageAnswer.setText("");
            imageAnswer.setBackground(getResources().getDrawable(R.drawable.text_view_drw));
            space_two_ans = false;
        }
    };

    private Runnable rightAns = new Runnable() {
        @Override
        public void run() {
            RandNubber.olqishlash++;
            Intent intent = new Intent(GameSequence.this, RightAnswer.class);
            handler.removeCallbacks(runnable);
            intent.putExtra("game", "Sequence");
            space_two_ans = false;
            if(RandNubber.olqishlash>2) {
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } else {
                intent = new Intent(GameSequence.this, GameSequence.class);
                startActivity(intent);
                finish();
            }
        }
    };


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            k++;
            if(k==1){
                //shunchaki kutish
            } else {
                appleShow();
            }
            if(a!=k-1)
                handler.postDelayed(this, 700);
        }
    };

    private void appleShow(){
        if(k<=6) {
            imageViews[k] = new ImageView(GameSequence.this);
            imageViews[k].setLayoutParams(params);
            imageViews[k].setBackgroundResource(res_fruit);
            inner_linerLayout1.addView(imageViews[k]);
        }
        else {
            if(k==7)
                linearLayout.addView(inner_linerLayout2);
            imageViews[k] = new ImageView(GameSequence.this);
            imageViews[k].setLayoutParams(params);
            imageViews[k].setBackgroundResource(res_fruit);
            inner_linerLayout2.addView(imageViews[k]);
        }
    }

    private void randomRes(int rand_res) {
        switch (rand_res){
            case 0:
                res_fruit = R.drawable.apple_trim;
                break;
            case 1:
                res_fruit = R.drawable.stroberry;
                break;
            case 2:
                res_fruit = R.drawable.peach;
                break;
            case 3:
                res_fruit = R.drawable.lemon;
                break;
            case 4:
                res_fruit = R.drawable.cherry;
                break;
            case 5:
                res_fruit = R.drawable.gepart_t;
                break;
            case 6:
                res_fruit = R.drawable.jirafa_t;
                break;
            case 7:
                res_fruit = R.drawable.kivi_t;
                break;
            case 8:
                res_fruit = R.drawable.tarvuz_t;
                break;
            case 9:
                res_fruit = R.drawable.zebra_t;
                break;
        }
    }
}
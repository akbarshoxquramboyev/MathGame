package com.example.mathforeveryone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mathforeveryone.sizecomp.MetricsDevices;
import com.example.mathforeveryone.sizecomp.RandNubber;

import java.util.Random;

public class FindMissingNumber extends AppCompatActivity {

    private TextView answer1, answer2, answer3, answer4;
    private TextView[] query;
    MediaPlayer mplR, mplW;
    boolean space_two_ans;
    Handler handler;
    int missingNumber, startSequence, k, startSequenceMove, a, answers[], right_ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_find_missing_number);

        ImageView exitAddGame = findViewById(R.id.exitMissingNumber);
        exitAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mplR = MediaPlayer.create(this, R.raw.correct2);
        mplW = MediaPlayer.create(this, R.raw.wrong);

        answer1 = findViewById(R.id.answer_missing_number1);
        answer2 = findViewById(R.id.answer_missing_number2);
        answer3 = findViewById(R.id.answer_missing_number3);
        answer4 = findViewById(R.id.answer_missing_number4);
        answer1.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer2.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer3.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer4.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer1.setOnClickListener(listener);
        answer2.setOnClickListener(listener);
        answer3.setOnClickListener(listener);
        answer4.setOnClickListener(listener);

        query = new TextView[5];
        query[1] = findViewById(R.id.query_missing_number1);
        query[2] = findViewById(R.id.query_missing_number2);
        query[3] = findViewById(R.id.query_missing_number3);
        query[4] = findViewById(R.id.query_missing_number4);
        query[1].setTextSize((float) (MetricsDevices.dpWidht*0.15));
        query[2].setTextSize((float) (MetricsDevices.dpWidht*0.15));
        query[3].setTextSize((float) (MetricsDevices.dpWidht*0.15));
        query[4].setTextSize((float) (MetricsDevices.dpWidht*0.15));

        handler = new Handler();
        Random random = new Random();
        missingNumber = random.nextInt(4)+1;
        startSequence = random.nextInt(6)+1;
        startSequenceMove = startSequence;
        k = 0;
        answers = new int[4];
        right_ans = random.nextInt(4);
        boolean[] used_ans = new boolean[11];
        for (int i = 0; i < 11; i++) {
            used_ans[i] = false;
        }
        a = startSequence+missingNumber-1;
        used_ans[a] = true;
        for (int i = 0; i < 4; i++) {
            if(i==right_ans){
                answers[i] = a;
            } else {
                while (true) {
                    int d = random.nextInt(9)+1;
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
        Intent intent = new Intent(FindMissingNumber.this, ChooseCategory.class);
        startActivity(intent);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            k++;
            if(k==missingNumber){
                query[k].setText("?");
            } else {
                query[k].setText(startSequenceMove+"");
            }
            startSequenceMove++;
            if(k<4)
                handler.postDelayed(this, 700);
        }
    };

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!space_two_ans) {
                TextView ans = (TextView) view;
                query[missingNumber].setText(ans.getText());
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
            query[missingNumber].setText("?");
            space_two_ans = false;
        }
    };

    private Runnable rightAns = new Runnable() {
        @Override
        public void run() {
            RandNubber.olqishlash++;
            Intent intent = new Intent(FindMissingNumber.this, RightAnswer.class);
            handler.removeCallbacks(runnable);
            intent.putExtra("game", "missing");
            space_two_ans = false;
            if(RandNubber.olqishlash>2) {
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } else {
                intent = new Intent(FindMissingNumber.this, FindMissingNumber.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
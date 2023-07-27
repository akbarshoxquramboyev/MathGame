package com.math.mathforeveryone;

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

import com.math.mathforeveryone.sizecomp.MetricsDevices;
import com.math.mathforeveryone.sizecomp.RandNubber;

import java.util.Random;

public class GameSubtract extends AppCompatActivity {

    LinearLayout line_lay_subtract, inner_linerLayout1, inner_linerLayout2;
    private int k, k2, ak, a, b;
    private Handler handler;
    private ImageView[] imageView;
    private LinearLayout.LayoutParams params;
    private TextView ayriluvchi1, ayriluvchi2;
    private TextView imageAnswer;
    private int[] answers;
    private int right_ans;
//    private float xDown, yDown, xFirstDown, yFirstDown, xAnswer, yAnswer;
    MediaPlayer mplR, mplW;
    MetricsDevices devices;
    MediaPlayer MpBeatten;
    boolean space_two_ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_subtract);

        MpBeatten = MediaPlayer.create(this, R.raw.apple_eatten2);
        devices = new MetricsDevices();

        ImageView exitAddGame = findViewById(R.id.exitSubtractGame);
        exitAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ayriluvchi1 = findViewById(R.id.ayriluvchi1);
        ayriluvchi2 = findViewById(R.id.ayriluvchi2);
        ayriluvchi1.setTextSize((float) (MetricsDevices.dpWidht*0.15)); //15%
        ayriluvchi2.setTextSize((float) (MetricsDevices.dpWidht*0.15)); //15%

        line_lay_subtract = findViewById(R.id.line_lay_subtract);
        inner_linerLayout1 = new LinearLayout(this);
        inner_linerLayout2 = new LinearLayout(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        inner_linerLayout1.setLayoutParams(params1);
        inner_linerLayout2.setLayoutParams(params1);
        inner_linerLayout1.setOrientation(LinearLayout.HORIZONTAL);
        inner_linerLayout2.setOrientation(LinearLayout.HORIZONTAL);
        line_lay_subtract.addView(inner_linerLayout1);

//        besh javob variantini ko`rsatadigan TextView lar
        TextView answer1, answer2, answer3, answer4;
        answer1 = findViewById(R.id.answer_subtract1);
        answer2 = findViewById(R.id.answer_subtract2);
        answer3 = findViewById(R.id.answer_subtract3);
        answer4 = findViewById(R.id.answer_subtract4);
        answer1.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer2.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer3.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer4.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer1.setOnClickListener(listener);
        answer2.setOnClickListener(listener);
        answer3.setOnClickListener(listener);
        answer4.setOnClickListener(listener);

//        Javobni belgiash uchun TextView
        imageAnswer = findViewById(R.id.image_answer_subtract);
        imageAnswer.setTextSize((float) (MetricsDevices.dpWidht*0.15));

        mplR = MediaPlayer.create(this, R.raw.correct2);
        mplW = MediaPlayer.create(this, R.raw.wrong);

//        olmalarni chiqarish uchun ImageView va uning parametrlari
        imageView = new ImageView[35];
        int imgPx = (int) (devices.getWidhtPixsel()*0.4*0.24);
        int margnPx = (int) (devices.getWidhtPixsel()*0.4*0.05);
        params = new LinearLayout.LayoutParams(imgPx, imgPx);
        params.setMargins(margnPx, margnPx,0,0);

        handler = new Handler();
        Random random = new Random();

        a = random.nextInt(10)+1;
        b = random.nextInt(a)+1;
        answers = new int[4];
        right_ans = random.nextInt(4);
        boolean[] used_ans = new boolean[11];
        for (int i = 0; i < 11; i++) {
            used_ans[i] = false;
        }
        used_ans[a-b] = true;
        for (int i = 0; i < 4; i++) {
            if(i==right_ans){
                answers[i] = a-b;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GameSubtract.this, ChooseCategory.class);
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

//    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            float xMoved, yMoved;
//            TextView textView = (TextView) view;
//            switch (motionEvent.getActionMasked()){
//                case MotionEvent.ACTION_DOWN:
//                    xDown = motionEvent.getX();
//                    yDown = motionEvent.getY();
//                    xFirstDown = view.getX();
//                    yFirstDown = view.getY();
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    xMoved = motionEvent.getX();
//                    yMoved = motionEvent.getY();
//
//                    float distanceX = xMoved-xDown;
//                    float distanceY = yMoved-yDown;
//                    view.setX(view.getX()+distanceX);
//                    view.setY(view.getY()+distanceY);
//                    yAnswer = view.getY()+distanceY;
//                    xAnswer = view.getX()+distanceX;
//                    break;
//                case MotionEvent.ACTION_UP:
//                    view.setX(xFirstDown);
//                    view.setY(yFirstDown);
//                    if(Math.abs(xAnswer-imageAnswer.getX())<imageAnswer.getWidth()/4 &&
//                            Math.abs(yAnswer-imageAnswer.getY())<imageAnswer.getWidth()/4){
//                        int ans = Integer.parseInt(textView.getText()+"");
//                        imageAnswer.setText(textView.getText());
//                        imageAnswer.setBackground(getResources().getDrawable(R.drawable.number_back_color));
//                        if(ans != answers[right_ans]){
//                            mplW.start();
//                            handler.postDelayed(wrongAns, 1000);
//                        } else {
//                            mplR.start();
//                            handler.postDelayed(rightAns, 1000);
//                        }
//                    }
//                    xAnswer = 0;
//                    yAnswer = 0;
//                    break;
//            }
//            return true;
//        }
//    };

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
            Intent intent = new Intent(GameSubtract.this, RightAnswer.class);
            handler.removeCallbacks(runnable);
            intent.putExtra("game", "Subtract");
            space_two_ans = false;
            if(RandNubber.olqishlash>2) {
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } else {
                intent = new Intent(GameSubtract.this, GameSubtract.class);
                startActivity(intent);
                finish();
            }
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            k2++;
            if(k2==1){
                //shunchaki kutish
            }
            if(k2==2)
                ayriluvchi1.setText(a+"");
            if(k2==a+3)
                ayriluvchi2.setText(b+"");
            else
            if(k2>2) {
                appleShow();
            }
            if(!(a-b==k && ak == 1))
                handler.postDelayed(this, 700);
        }
    };

    private void appleShow(){
        if(ak==0) {
            k++;
            if(k<=10) {
                imageView[k] = new ImageView(GameSubtract.this);
                imageView[k].setLayoutParams(params);
                imageView[k].setBackgroundResource(R.drawable.apple_trim);
                inner_linerLayout1.addView(imageView[k]);
            }
            else {
                if(k==11)
                    line_lay_subtract.addView(inner_linerLayout2);
                imageView[k] = new ImageView(GameSubtract.this);
                imageView[k].setLayoutParams(params);
                imageView[k].setBackgroundResource(R.drawable.apple_trim);
                inner_linerLayout2.addView(imageView[k]);
            }
        }
        else {
            imageView[k].setBackgroundResource(R.drawable.bitten_apple);
            k--;
            MpBeatten.start();
        }
        if(k==a)
            ak = 1;
    }
}
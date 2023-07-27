package com.math.mathforeveryone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.math.mathforeveryone.sizecomp.MetricsDevices;
import com.math.mathforeveryone.sizecomp.RandNubber;

import java.util.Random;

public class GameAdd extends AppCompatActivity {

    private int k, k2, ak, bk, a, b;
    private Handler handler;
    private ImageView imageView[];
    private LinearLayout.LayoutParams params;
    private LinearLayout linearLayout;
    private TextView qushiluvchi1, qushiluvchi2;
    private TextView imageAnswer;
    private int answers[];
    private int right_ans, res_fruit;
    private float xDown, yDown, xFirstDown, yFirstDown, xAnswer, yAnswer;
    ImageView cardView_add_2, card_view;
    MediaPlayer mplR, mplW;
    MetricsDevices devices;
    boolean space_two_ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_add);
        ImageView exitAddGame = findViewById(R.id.exitAddGame);
        exitAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        startGame();
    }

    private void startGame(){
        //        olmalarni joylashtirish uchun ikkita asosiy LinearLayoutning har birini
//        ichidagi layoutlarni ko`rsatish uchun
        linearLayout = findViewById(R.id.line_lay_ichki_1);
//      Ekran o`lchamlarini olish uchun
        devices = new MetricsDevices();
//        qo`shiluvchi sonlarni ko`rsatadigan TextViewlar
        qushiluvchi1 = findViewById(R.id.qushiluvchi1);
        qushiluvchi2 = findViewById(R.id.qushiluvchi2);
        qushiluvchi1.setTextSize((float) (MetricsDevices.dpWidht*0.15)); //15%
        qushiluvchi2.setTextSize((float) (MetricsDevices.dpWidht*0.15)); //15%

//        qo`shilivchi sonlar o`rtasidagi qo`shish belgisi (CardView) o`lchamlarini aniqlash
        float radiusWidht = (float) (devices.getWidhtPixsel()*0.24);
        cardView_add_2 = findViewById(R.id.image_add_2);
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) cardView_add_2.getLayoutParams();
        layoutParams.width = (int) (radiusWidht*0.7);
        layoutParams.height = (int) (radiusWidht*0.7);
        cardView_add_2.setLayoutParams(layoutParams);

//        olmalar o`rtasidagi qo`shish belgisi (CardView) o`lchamlarini aniqlash
        radiusWidht = (float) (devices.getWidhtPixsel()*0.40);
        card_view = findViewById(R.id.imageView2);
        layoutParams = (ConstraintLayout.LayoutParams) card_view.getLayoutParams();
        layoutParams.setMargins((int) (radiusWidht*0.06), 0, 0, 0);
        layoutParams.width = (int) (radiusWidht * 0.6);
        layoutParams.height = (int) (radiusWidht * 0.6);
        card_view.setLayoutParams(layoutParams);

//        ikkinchi bo`lak olmalar joylashgan LinearLayout ga marginLeft berish
        LinearLayout line_lay_qush_2 = findViewById(R.id.line_lay_qush_2);
        layoutParams = (ConstraintLayout.LayoutParams) line_lay_qush_2.getLayoutParams();
        layoutParams.setMargins((int) (radiusWidht*0.06), 0, 0, 0);
        line_lay_qush_2.setLayoutParams(layoutParams);

//        besh javob variantini ko`rsatadigan TextView lar
        TextView answer1, answer2, answer3, answer4;
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        answer1.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer2.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer3.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer4.setTextSize((float) (MetricsDevices.dpWidht*0.15));
        answer1.setOnClickListener(listener);
        answer2.setOnClickListener(listener);
        answer3.setOnClickListener(listener);
        answer4.setOnClickListener(listener);

//        Javobni belgiash uchun TextView
        imageAnswer = findViewById(R.id.image_answer);
        imageAnswer.setTextSize((float) (MetricsDevices.dpWidht*0.15));

        mplR = MediaPlayer.create(this, R.raw.correct2);
        mplW = MediaPlayer.create(this, R.raw.wrong);

//        olmalarni chiqarish uchun ImageView va uning parametrlari
        imageView = new ImageView[35];
        int imgPx = (int) (devices.getWidhtPixsel()*0.4*0.24);
        int margnPx = (int) (devices.getWidhtPixsel()*0.4*0.07);
        params = new LinearLayout.LayoutParams(imgPx, imgPx);
        params.setMargins(margnPx, margnPx,0,0);

        handler = new Handler();
        Random random = new Random();

        int rand_res = random.nextInt(10);
        randomRes(rand_res);
        a = random.nextInt(9)+1;
        b = random.nextInt(10-a)+1;
        answers = new int[4];
        right_ans = random.nextInt(4);
        boolean used_ans[] = new boolean[11];
        for (int i = 0; i < 11; i++) {
            used_ans[i] = false;
        }
        used_ans[a+b] = true;
        for (int i = 0; i < 4; i++) {
            if(i==right_ans){
                answers[i] = a+b;
            } else {
                while (true) {
                    int d = random.nextInt(10) + 1;
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
        Intent intent = new Intent(GameAdd.this, ChooseCategory.class);
        startActivity(intent);
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
//                    xMoved = motionEvent.getX();
//                    yMoved = motionEvent.getY();
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
            Intent intent = new Intent(GameAdd.this, RightAnswer.class);
            handler.removeCallbacks(runnable);
            intent.putExtra("game", "Add");
            space_two_ans = false;
            if(RandNubber.olqishlash>2) {
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } else {
                intent = new Intent(GameAdd.this, GameAdd.class);
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
                qushiluvchi1.setText(a+"");
            if(k2==a+3)
                qushiluvchi2.setText(b+"");
            else
                if(k2>2)
                    appleShow();
            if(a+b>k)
                handler.postDelayed(this, 500);
        }
    };

    private void appleShow(){
        k++;
        if(k<=a) {
            if (ak % 4 == 0) {
                switch (ak / 4){
                    case 0:{
                        linearLayout = findViewById(R.id.line_lay_ichki_1);
                        break;
                    }
                    case 1:{
                        linearLayout = findViewById(R.id.line_lay_ichki_2);
                        break;
                    }
                    case 2:{
                        linearLayout = findViewById(R.id.line_lay_ichki_3);
                        break;
                    }
                }
            }
            ak++;
        }
        else {
            if (bk % 4 == 0) {
                switch (bk / 4){
                    case 0:{
                        linearLayout = findViewById(R.id.line_lay_ichki_4);
                        break;
                    }
                    case 1:{
                        linearLayout = findViewById(R.id.line_lay_ichki_5);
                        break;
                    }
                    case 2:{
                        linearLayout = findViewById(R.id.line_lay_ichki_6);
                        break;
                    }
                }
            }
            bk++;
        }
        imageView[k] = new ImageView(GameAdd.this);
        imageView[k].setLayoutParams(params);
        imageView[k].setBackgroundResource(res_fruit);
        linearLayout.addView(imageView[k]);
    }
}
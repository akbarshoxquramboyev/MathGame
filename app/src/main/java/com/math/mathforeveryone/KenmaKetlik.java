package com.math.mathforeveryone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.math.mathforeveryone.sizecomp.RandNubber;

import java.util.Random;

public class KenmaKetlik extends AppCompatActivity {

    private MediaPlayer mplR, mplW;
    private boolean space_two_ans;
    private Handler handler;
    private ImageView[] query;
    private ImageView[] ans_img;
    private int mas[][], res_toys[], k, rand_q, right_ans;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_kenma_ketlik);

        ImageView exitAddGame = findViewById(R.id.exitKetmaKetlik);
        exitAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mplR = MediaPlayer.create(this, R.raw.correct2);
        mplW = MediaPlayer.create(this, R.raw.wrong);

        query = new ImageView[7];
        query[1] = findViewById(R.id.kema_ketlik_query_1);
        query[2] = findViewById(R.id.kema_ketlik_query_2);
        query[3] = findViewById(R.id.kema_ketlik_query_3);
        query[4] = findViewById(R.id.kema_ketlik_query_4);
        query[5] = findViewById(R.id.kema_ketlik_query_5);
        query[6] = findViewById(R.id.kema_ketlik_query_6);

        ans_img = new ImageView[5];
        ans_img[1] = findViewById(R.id.kema_ketlik_ans_1);
        ans_img[2] = findViewById(R.id.kema_ketlik_ans_2);
        ans_img[3] = findViewById(R.id.kema_ketlik_ans_3);
        ans_img[4] = findViewById(R.id.kema_ketlik_ans_4);
        for (int i = 1; i < 5; i++) {
            ans_img[i].setOnClickListener(listener);
        }

        mas = new int[][]{
                {1, 2, 3, 1, 2, 3},
                {1, 1, 2, 2, 3, 3},
                {1, 2, 1, 2, 1, 2},
                {1, 1, 2, 2, 1, 1},
                {1, 1, 1, 2, 2, 2}
        };
        random = new Random();
        handler = new Handler();
        rand_q = random.nextInt(5);
        randQuery();
    }

    private void randQuery() {

        int max_number_fruet = max_mas();

        res_toys = new int[max_number_fruet];
        int res_toys_id[] = new int[max_number_fruet];
        boolean used[] = new boolean[10];
        for (int i = 0; i < 10; i++) {
            used[i] = false;
        }

        for (int i = 0; i < max_number_fruet; i++) {
            int r = random.nextInt(9);
            while (used[r]) {
                r = random.nextInt(9);
            }
            used[r] = true;
            res_toys_id[i] = r;
            res_toys[i] = randomRes(r);
        }
        k = -1;
        runnable.run();
        boolean[] used_ans = new boolean[11];
        for (int i = 0; i < 11; i++) {
            used_ans[i] = false;
        }
        right_ans = random.nextInt(4)+1;
        int a = res_toys_id[mas[rand_q][5]-1];
        used_ans[a] = true;
        for (int i = 1; i < 5; i++) {
            if(i==right_ans){
                ans_img[i].setBackgroundResource(res_toys[mas[rand_q][5]-1]);
            } else {
                while (true) {
                    int d = random.nextInt(9);
                    if (!used_ans[d]) {
                        used_ans[d] = true;
                        ans_img[i].setBackgroundResource(randomRes(d));
                        break;
                    }
                }
            }
        }
    }

    private int randomRes(int rand_res) {
        int res_fruit = 0;
        switch (rand_res) {
            case 0:
                res_fruit = R.drawable.stroberry;
                break;
            case 1:
                res_fruit = R.drawable.peach;
                break;
            case 2:
                res_fruit = R.drawable.lemon;
                break;
            case 3:
                res_fruit = R.drawable.cherry;
                break;
            case 4:
                res_fruit = R.drawable.gepart_t;
                break;
            case 5:
                res_fruit = R.drawable.jirafa_t;
                break;
            case 6:
                res_fruit = R.drawable.kivi_t;
                break;
            case 7:
                res_fruit = R.drawable.tarvuz_t;
                break;
            case 8:
                res_fruit = R.drawable.zebra_t;
                break;
        }
        return res_fruit;
    }

    private int max_mas() {
        int maxx = mas[rand_q][0];
        for (int i : mas[rand_q]) {
            maxx = Math.max(maxx, i);
        }
        return maxx;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            k++;
            if(k<5)
                query[k+1].setBackgroundResource(res_toys[mas[rand_q][k]-1]);
            else
                query[k+1].setBackgroundResource(R.drawable.text_view_drw);
            if(k<5)
                handler.postDelayed(this, 700);
        }
    };

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(KenmaKetlik.this, ChooseCategory.class);
        startActivity(intent);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!space_two_ans) {
                ImageView ans = (ImageView) view;
                query[6].setBackground(ans.getBackground());
                if (ans.getId() != ans_img[right_ans].getId()) {
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
            query[6].setBackgroundResource(R.drawable.text_view_drw);
            space_two_ans = false;
        }
    };

    private Runnable rightAns = new Runnable() {
        @Override
        public void run() {
            RandNubber.olqishlash++;
            Intent intent = new Intent(KenmaKetlik.this, RightAnswer.class);
            handler.removeCallbacks(runnable);
            intent.putExtra("game", "ketmaketlik");
            space_two_ans = false;
            if(RandNubber.olqishlash>2) {
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } else {
                intent = new Intent(KenmaKetlik.this, KenmaKetlik.class);
                startActivity(intent);
                finish();
            }
        }
    };

}
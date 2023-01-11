package com.example.mathforeveryone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.mathforeveryone.sizecomp.Mplayer;
import com.example.mathforeveryone.sizecomp.RandNubber;

public class ChooseCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose_category);
        ImageView add_game = findViewById(R.id.category_add_game);
        add_game.setOnClickListener(listener);
        ImageView subtract_game = findViewById(R.id.category_subtract_game);
        subtract_game.setOnClickListener(listener);
        ImageView secuence_game = findViewById(R.id.category_sequence_game);
        secuence_game.setOnClickListener(listener);
        ImageView missing_number_game = findViewById(R.id.category_missing_number_game);
        missing_number_game.setOnClickListener(listener);
        ImageView ketma_ketlik = findViewById(R.id.category_ketma_ketlik_game);
        ketma_ketlik.setOnClickListener(listener);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Mplayer.mainSound.pause();
//    }

    @Override
    protected void onPause() {
        super.onPause();
        Mplayer.mainSound.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!Mplayer.mainSound.isPlaying()) {
//            Mplayer.MplayerCreate(this);
            Mplayer.mainSound.start();
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Mplayer.mainSound.pause();
            RandNubber.test_count = 0;
            RandNubber.olqishlash = 0;
            ImageView imageView = (ImageView) view;
            if(imageView.getId() == R.id.category_add_game){
                Intent intent = new Intent(ChooseCategory.this, FindMissingNumber.class);
                startActivity(intent);
                finish();
            }
            if(imageView.getId() == R.id.category_subtract_game) {
                Intent intent = new Intent(ChooseCategory.this, KenmaKetlik.class);
                startActivity(intent);
                finish();
            }
            if(imageView.getId() == R.id.category_sequence_game) {
                Intent intent = new Intent(ChooseCategory.this, GameSequence.class);
                startActivity(intent);
                finish();
            }
            if(imageView.getId() == R.id.category_missing_number_game) {
                Intent intent = new Intent(ChooseCategory.this, GameSubtract.class);
                startActivity(intent);
                finish();
            }
            if(imageView.getId() == R.id.category_ketma_ketlik_game) {
                Intent intent = new Intent(ChooseCategory.this, GameAdd.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
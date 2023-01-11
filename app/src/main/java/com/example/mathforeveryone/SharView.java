package com.example.mathforeveryone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;

import com.example.mathforeveryone.sizecomp.MetricsDevices;

public class SharView extends View {
    private int shar_x1, shar_x2, shar_x3, shar_x4, shar_x5;
    private int shar_y1, shar_y2, shar_y3, shar_y4, shar_y5;
    private Bitmap bitmap1, bitmap2, bitmap3, bitmap4, bitmap5;
    private int bitmap_widht, bitmap_height;
    private MetricsDevices devices;

    public SharView(Context context) {
        super(context);
        setBackgroundResource(R.drawable.right_answer_backend);
        devices = new MetricsDevices();
        int widht = (int) devices.getWidhtPixsel();
        int height = (int) devices.getHeightPixsel();
        bitmap_widht = (height/6);
        bitmap_height = (int) ((350./160)*bitmap_widht);
        shar_y1 = widht;
        shar_x1 = bitmap_widht/6;
        shar_y2 = widht+bitmap_height;
        shar_x2 = bitmap_widht/6+bitmap_widht;
        shar_y3 = widht+bitmap_height/2;
        shar_x3 = bitmap_widht/6+bitmap_widht*2;
        shar_y4 = widht+bitmap_height*2/3;
        shar_x4 = bitmap_widht/6+bitmap_widht*3;
        shar_y5 = widht+bitmap_height;
        shar_x5 = bitmap_widht/6+bitmap_widht*4;
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_png_1);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_png_3);
        bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_png_4);
        bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_png_5);
        bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.balloon_png_6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        shar_y1-=15;
        shar_y2-=15;
        shar_y3-=15;
        shar_y4-=15;
        shar_y5-=15;

        //shar y ni kamayib borishini o`zgartirishim garak -10

        Rect rect = new Rect(shar_x1, shar_y1, shar_x1+bitmap_widht, shar_y1+bitmap_height);
        canvas.drawBitmap(bitmap1, null, rect, null);

        rect = new Rect(shar_x2, shar_y2, shar_x2+bitmap_widht, shar_y2+bitmap_height);
        canvas.drawBitmap(bitmap2, null, rect, null);

        rect = new Rect(shar_x3, shar_y3, shar_x3+bitmap_widht, shar_y3+bitmap_height);
        canvas.drawBitmap(bitmap3, null, rect, null);

        rect = new Rect(shar_x4, shar_y4, shar_x4+bitmap_widht, shar_y4+bitmap_height);
        canvas.drawBitmap(bitmap4, null, rect, null);

        rect = new Rect(shar_x5, shar_y5, shar_x5+bitmap_widht, shar_y5+bitmap_height);
        canvas.drawBitmap(bitmap5, null, rect, null);

        invalidate();
    }
}
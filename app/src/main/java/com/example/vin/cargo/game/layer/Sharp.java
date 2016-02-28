package com.example.vin.cargo.game.layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.vin.cargo.R;
import com.example.vin.cargo.game.GameSurface;

/**
 * Sharp
 *
 * @author: Vin
 * @time: 2016/2/28 21:14
 */
public class Sharp extends BaseLayer {

    Bitmap bitmap;
    /**
     * 构造函数
     *
     * @param surface
     */
    public Sharp(GameSurface surface) {
        super(surface);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        bitmap= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.confession);
        bitmap= Bitmap.createScaledBitmap(bitmap, screenW, screenH, true);
        canvas.drawBitmap(bitmap,0,0,paint);
    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onTouchEvent(MotionEvent event, Canvas canvas, Paint paint) {

    }
}

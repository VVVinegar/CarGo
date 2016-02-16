package com.example.vin.cargo.game.layer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.vin.cargo.R;
import com.example.vin.cargo.game.GameSurface;
import com.example.vin.cargo.utils.Assist;

/**
 * Player
 *
 * @author: Vin
 * @time: 2016/2/5 21:11
 */
public class Player extends BaseLayer {



    private float carX;
    private float carY;
    private float carW;
    private float carH;


    /**
     * 构造函数
     *
     * @param surface
     */
    public Player(GameSurface surface) {
        super(surface);

        carX=way2-75;
        carY=screenH/2+300;
        carW=150;
        carH=200;

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Assist.getColor(res, R.color.PLAYER));
        canvas.drawRect(carX, carY, carX + carW, carY + carH, paint);
    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();


        /**
         * 当前车在way2
         */
        if(touchX > screenW/2 && carX==way2-75) {
            carX=way3-75;
        }

        if(touchX < screenW/2 && carX==way2-75) {
            carX=way1-75;
        }

        /**
         * 当前车在way1
         */
        if(touchX > screenW/2 && carX==way1-75) {
            carX = way2-75;
        }


        /**
         * 当前车在way3
         */

        if(touchX < screenW/2 && carX==way3-75) {
            carX=way2-75;
        }
    }
}

package com.example.vin.cargo.game.layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.vin.cargo.R;
import com.example.vin.cargo.game.GameSurface;
import com.example.vin.cargo.utils.Assist;
import com.example.vin.cargo.utils.Constants;

/**
 * Start
 *
 * @author: Vin
 * @time: 2016/2/5 21:11
 */
public class Start extends BaseLayer {

    private int downX;
    private int downY;
    private int moveX;
    private int moveY;
    private int thisX;
    private int thisY;
    private int touchX;
    private int touchY;
    private int x;
    private int y;
    private float testX;

    private float buttonX;
    private float buttonY;
    private float buttonW;
    private float buttonH;
    Bitmap bitmap;

    /**
     * 构造函数
     *
     * @param surface
     */
    public Start(GameSurface surface) {

        super(surface);

        buttonW = 400;
        buttonH = 380;
        buttonX = screenW -400;
        buttonY = screenH -380;

//        triangleW = 200;
//        traingleH = 100;


/**
 * 测试
  */
//        downX=0;
//        downY=0;
//        moveX=0;
//        moveY=0;
//        thisX=0;
//        thisY=0;
//        x=screenW/2;
//        y=screenH/2;

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        bitmap= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.begainpictur);
        bitmap = Bitmap.createScaledBitmap(bitmap,screenW, screenH, true);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        bitmap= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.begainbutton);
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) buttonW, (int) buttonH, true);
        canvas.drawBitmap(bitmap, buttonX, buttonY, paint);


        /**
         * 测试
         */
//        Rect rect=canvas.getClipBounds();
//        rect.right=200;
//        rect.bottom=screenH;
//        rect.left=0;
//        rect.top=screenH-200;
//
    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){

            touchX = (int) event.getX();
            touchY = (int) event.getY();
        }

        //判断是否点击了开始按钮
        if(touchX > buttonX && touchX < buttonX + buttonW && touchY > buttonY && touchY < buttonY + buttonH){
            surface.setGameState(Constants.GAMING);
        }

        /**
         * 测试
         */
//        switch(event.getAction()) {
//
//            // 按下
//            case MotionEvent.ACTION_DOWN:
//
//                downX = (int) event.getX();
//                downY = (int) event.getY();
//                break;
//
//                // 移动
//            case MotionEvent.ACTION_MOVE:
//
////                thisX = (int) event.getX();
////                thisY = (int) event.getY();
////                moveX = thisX-downX;
////                moveY = thisY-downY;
////                downX = thisX;
////                downY = thisY;
//                downX = (int) event.getX();
//                downY = (int) event.getY();
//                break;
//
//        }
    }

    @Override
    public void onTouchEvent(MotionEvent event, Canvas canvas, Paint paint) {

    }
}
package com.example.vin.cargo.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    private int x;
    private int y;

    private float buttonX;
    private float buttonY;
    private float buttonW;
    private float buttonH;
    private float triangleW, traingleH;

    /**
     * 构造函数
     *
     * @param surface
     */
    public Start(GameSurface surface) {

        super(surface);

        buttonW = 800;
        buttonH = 400;
        buttonX = screenW / 2 - buttonW / 2;
        buttonY = screenH / 3 - buttonH / 2;

        triangleW = 200;
        traingleH = 100;

//        downX=0;
//        downY=0;
//        moveX=0;
//        moveY=0;
//        thisX=0;
//        thisY=0;
        x=screenW/2;
        y=screenH/2;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, screenW, screenH, paint);
        paint.setColor(Color.WHITE);

        canvas.drawRect(buttonX, buttonY, buttonX + buttonW, buttonY + buttonH, paint);

        paint.setColor(Color.GREEN);
        Path path = new Path();
        path.moveTo(buttonX + buttonW / 2 - traingleH / 2, buttonY + buttonH / 2 - triangleW / 2);
        path.lineTo(buttonX + buttonW / 2 - traingleH / 2, buttonY + buttonH / 2 + triangleW / 2);
        path.lineTo(buttonX + buttonW / 2 + traingleH / 2, buttonY + buttonH / 2);
        canvas.drawPath(path, paint);

        canvas.drawCircle(x+moveX,y+moveY,50,paint);
    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        //判断是否点击了开始按钮
        if(touchX > buttonX && touchX < buttonX + buttonW && touchY > buttonY && touchY < buttonY + buttonH){
            surface.setGameState(Constants.GAMING);
        }

        switch(event.getAction()) {

            // 按下
            case MotionEvent.ACTION_DOWN:

                downX = (int) event.getX();
                downY = (int) event.getY();

                // 移动
            case MotionEvent.ACTION_MOVE:

                thisX = (int) event.getX();
                thisY = (int) event.getY();
                moveX = thisX-downX;
                moveY = thisY-downY;
                downX = thisX;
                downY = thisY;
        }
    }
}
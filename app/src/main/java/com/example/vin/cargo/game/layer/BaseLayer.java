package com.example.vin.cargo.game.layer;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;


import com.example.vin.cargo.game.GameSurface;

/**
 * BaseLayer
 *
 * @author: Vin
 * @time: 2016/2/5 21:10
 */
public abstract class BaseLayer {

    protected int line0;
    protected int line1;
    protected int line2;
    protected int line3;
    protected int line4;
    protected int line5;             //屏幕中6条纵线

    protected int way1;
    protected int way2;
    protected int way3;

    protected GameSurface surface;
    protected Resources res;


    protected int screenW;
    protected int screenH;

    /**
     * 构造函数
     * @param surface
     */
    public BaseLayer(GameSurface surface){
        this.surface=surface;
        screenW=surface.getWidth();
        screenH=surface.getHeight();
        res= surface.getResources();

        line0=screenW/2-400;
        line1=line0+100;
        line2=line0+300;
        line3=line0+500;
        line4=line0+700;
        line5=line0+750;

        way2=screenW/2;
        way1=(line1+line2)/2;
        way3=(line3+line4)/2;
    }

    /**
     * 画图
     * @param canvas
     * @param paint
     */
    public abstract void draw(Canvas canvas,Paint paint);

    /**
     * 逻辑
     */
    public abstract void logic();


    /**
     * 触摸事件
     * @param event
     */
    public abstract void onTouchEvent(MotionEvent event);

    public abstract void onTouchEvent(MotionEvent event,Canvas canvas, Paint paint);

}

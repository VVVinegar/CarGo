package com.example.vin.cargo.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.vin.cargo.R;
import com.example.vin.cargo.game.GameSurface;
import com.example.vin.cargo.utils.Assist;
import com.example.vin.cargo.utils.Constants;

import java.util.Random;

/**
 * Barrier
 *
 * @author: Vin
 * @time: 2016/2/5 21:10
 */
public class Barrier extends BaseLayer {

    private float othercarX;          //障碍汽车的位置大小参数
    private float othercarY;
    private float othercarW;
    private float othercarH;

    private float otherSpeed;           //障碍汽车的速度
  
    private float place;                // 障碍汽车产生的随机位置
    private int isCar;                    //是否产生障碍汽车的随机数



    /**
     * 构造函数
     *
     * @param surface
     */
    public Barrier(GameSurface surface) {
        super(surface);

//        float[] places = new float[]{way1,way2,way3};
//        place = places[new Random().nextInt(places.length)];

        othercarX=place-75;
        othercarY=-200;
        othercarW=150;
        othercarH=200;

        otherSpeed=90;


    }



    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(res, R.color.BARRIER));
        while(true){
            getIsCar();
            if(isCar<=1){
                getplace();
                othercarX=place-75;
                canvas.drawRect(othercarX,othercarY,othercarX+othercarW,othercarY+othercarH,paint);
            }
        }
    }

    @Override
    public void logic() {
        othercarY+=otherSpeed;
        if(othercarY>screenH){
            getIsCar();
            getplace();
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    private float getplace(){
        float[] places = new float[]{way1,way2,way3};
        place = places[new Random().nextInt(places.length)];
        return place-75;
    }

    private int getIsCar(){
        return isCar=new Random().nextInt(100);
    }
   // public void setSpeed(float speed){this.speed=speed;}
}


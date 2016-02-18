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

    private float goodcarX;          //奖励汽车的左上角横坐标
    private float goodcarY;           //奖励汽车的左上角纵坐标
    private float goodcarW;          //奖励汽车的宽
    private float goodcarH;            //奖励汽车的高
    private float othercarX;          //障碍汽车的左上角横坐标
    private float othercarY;           //障碍汽车的左上角纵坐标
    private float othercarW;          //障碍汽车的宽
    private float othercarH;            //障碍汽车的高
    private float speed;                   //车速
    private double acc;                       //障碍汽车加速度
    private float otherSpeed;           //障碍汽车的速度
    private float place;                // 障碍汽车产生的随机位置


//    private float isCar;                    //是否产生奖励汽车的随机数
    private float carX;
    private float carY;
    private float carW;
    private float carH;
    /**
     * 构造函数
     *
     * @param surface
     */
    public Barrier(GameSurface surface) {
        super(surface);



        othercarX=getplace();
        othercarY=-200;
        othercarW=150;
        othercarH=200;
        goodcarX=getplace();
        goodcarY=-200;
        goodcarW=150;
        goodcarH=200;
        acc=0.1;
        otherSpeed=30;


    }



    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Assist.getColor(res, R.color.BARRIER));
        /**
         * 玩家达到匀速，开始出现障碍车
         */
        if (speed>=199){

            canvas.drawRect(othercarX,othercarY,othercarX+othercarW,othercarY+othercarH,paint);
        }
        paint.setColor(Assist.getColor(res, R.color.GOOD));

        if(speed>=199){
            canvas.drawRect(goodcarX,goodcarY,goodcarX+goodcarW,goodcarY+goodcarH,paint);
        }
    }

    @Override
    public void logic() {
        /**
         * 障碍
         */
        boolean condition1 = rectAndRect(carX, carY, carW, carH, othercarX, othercarY, othercarW, othercarH);
        if (speed >= 199) {

            othercarY += otherSpeed;
            otherSpeed += acc;
            if (condition1 == true) {
                surface.setGameState(Constants.GAME_OVER);
            }
            if (othercarY > screenH) {
                othercarY = -200;
                othercarX = getplace();
            }
        }
        /**
         * 奖励
         */
        boolean condition2 = rectAndRect(carX, carY, carW, carH, goodcarX, goodcarY, goodcarW, goodcarH);

        if (speed >= 199) {

            goodcarY += otherSpeed-5;
            if (condition2 == true) {
                otherSpeed -= 70;
                goodcarY = -2000;
                do{
                    goodcarX = getplace();
                }
                while (goodcarX!=othercarX);
            }
            if (goodcarY > screenH) {
                goodcarY = -2000;
                do{
                    goodcarX = getplace();
                }
                while (goodcarX!=othercarX);
            }
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }


    /**
     * 碰撞函数
     * @param rect1X
     * @param rect1Y
     * @param rect1W
     * @param rect1H
     * @param rect2X
     * @param rect2Y
     * @param rect2W
     * @param rect2H
     * @return
     */
    private boolean rectAndRect(float rect1X, float rect1Y, float rect1W, float rect1H, float
            rect2X, float rect2Y, float rect2W, float rect2H) {
        if (rect1X + rect1W < rect2X) {
            return false;
        } else if (rect1X > rect2X + rect2W) {
            return false;
        } else if (rect1Y + rect1H < rect2Y) {
            return false;
        } else if (rect1Y > rect2Y + rect2H) {
            return false;
        }
        return true;
    }

    /**
     *产生汽车出现的随机位置
     * @return
     */
    private float getplace(){
        float[] places = new float[]{way1,way2,way3};
        place = places[new Random().nextInt(places.length)];
        return place-75;
    }

//    private float getIsCar(){
//        return isCar=new Random().nextInt(100);
//    }

    public void setSpeed(float speed){this.speed=speed;}
    public void setCarX(float carX){this.carX=carX;}
    public void setCarY(float carY){this.carY=carY;}
    public void setCarW(float carW){this.carW=carW;}
    public void setCarH(float carH){this.carH=carH;}

}


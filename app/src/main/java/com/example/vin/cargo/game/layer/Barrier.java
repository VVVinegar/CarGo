package com.example.vin.cargo.game.layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.vin.cargo.R;
import com.example.vin.cargo.game.GameSurface;
import com.example.vin.cargo.utils.Assist;
import com.example.vin.cargo.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Barrier
 *
 * @author: Vin
 * @time: 2016/2/5 21:10
 */
public class Barrier extends BaseLayer {

    private float starX;
    private float starY;
    private float starW;
    private float starH;
    private float starSpeed;
    private float starPlace;
    private float starLine1;
    private float starLine2;
    private float starLine3;

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

    private long time;
    private long startTime;
    private long endTime;
    private boolean isStart;

    private Bitmap bitmapCar;
    private Bitmap bitmapStar1;
    private Bitmap bitmapStar2;

    private int touchX;
    private int touchY;


    /**
     * 构造函数
     *
     * @param surface
     */
    public Barrier(GameSurface surface) {
        super(surface);


        othercarX = getplaceCar();
        othercarY = -230;
        othercarW = 190;
        othercarH = 230;

        acc = 0.7;
        otherSpeed = 30;

        starLine1=300;
        starLine2=600;
        starLine3=900;

        starH=150;
        starW=150;
        starX=-150;
        starY=starLine1;
        starSpeed=60;


        isStart=true;
    }


    @Override
    public void draw(Canvas canvas, Paint paint) {
        int opacity = 250;
        paint.setAlpha(opacity);
        bitmapCar = BitmapFactory.decodeResource(surface.getResources(), R.mipmap.yellow_car);
        bitmapCar = Bitmap.createScaledBitmap(bitmapCar, (int) othercarW, (int) othercarH, true);

        /**
         * 玩家达到匀速，开始出现障碍车
         */

        if (speed >= 148) {

            canvas.drawBitmap(bitmapCar, othercarX, othercarY, paint);
        }

        /**
         * 画星星
         */
        bitmapStar1 = BitmapFactory.decodeResource(surface.getResources(), R.mipmap.greenstar);
        bitmapStar1 = Bitmap.createScaledBitmap(bitmapStar1, (int) starW, (int) starH, true);
        time=(int)System.currentTimeMillis()/1000;
        if(time>=10){
            canvas.drawBitmap(bitmapStar1, starX, starY, paint);
        }


    }

    @Override
    public void logic() {
        /**
         * 障碍
         */
        boolean condition1 = rectAndRect(carX, carY, carW, carH, othercarX, othercarY, othercarW, othercarH);
        if (speed >= 148) {

            othercarY += otherSpeed;
            otherSpeed += acc;
            if (condition1 == true) {
                surface.setGameState(Constants.GAME_OVER);
            }
            if (othercarY > screenH) {
                othercarY = -230;
                othercarX = getplaceCar();
            }
        }
        /**
         * 奖励
         */
        if(isStart){
            startTime=System.currentTimeMillis();
            isStart=false;
        }
        endTime=System.currentTimeMillis();
        time=(int)(endTime-startTime)/1000;
        if(time>=10){
            starX+=starSpeed;
            if(starX>screenW){

                starX=-150;
                starY=getplaceStar();
            }
        }

    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        touchX=(int)event.getX();
        touchY=(int)event.getY();
        if (touchX>=starX && touchX<=starX+starW && touchY > starY && touchY<=starY+starH){
            starX=-150-(screenW-touchX);
            starY=getplaceStar();
            otherSpeed-=10;
        }
    }


    /**
     * 碰撞函数
     *
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
     * 产生汽车出现的随机位置
     *
     * @return
     */
    private float getplaceCar() {
        float[] places = new float[]{way1, way2, way3};
        place = places[new Random().nextInt(places.length)];
        return place - 95;
    }

    private float getplaceStar(){
        float[] starPlaces=new float[]{starLine1,starLine2,starLine3};
        starPlace=starPlaces[new Random().nextInt(starPlaces.length)];
        return starPlace;
    }


//    private float getIsCar(){
//        return isCar=new Random().nextInt(100);
//    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setCarX(float carX) {
        this.carX = carX;
    }

    public void setCarY(float carY) {
        this.carY = carY;
    }

    public void setCarW(float carW) {
        this.carW = carW;
    }

    public void setCarH(float carH) {
        this.carH = carH;
    }


}


package com.example.vin.cargo.game.layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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

    private int arrowsLeft1X;
    private int arrowsLeft1Y;
    private int arrowsLeft1W;
    private int arrowsLeft1H;

    private int arrowsRight1X;
    private int arrowsRight1Y;
    private int arrowsRight1W;
    private int arrowsRight1H;

    protected Bitmap bitmapCar;
    protected Bitmap bitmapL;
    protected Bitmap bitmapR;
    protected Bitmap bitmapL2;
    protected Bitmap bitmapR2;

    int touchX;
    int touchY;


    /**
     * 构造函数
     *
     * @param surface
     */
    public Player(GameSurface surface) {
        super(surface);

        carX=way2-95;
        carY=screenH/2+300;
        carW=190;
        carH=230;

        arrowsLeft1X=0;
        arrowsLeft1Y=screenH/2+530;
        arrowsLeft1W=screenW/2;
        arrowsLeft1H=screenH-(screenH/2+530);


        arrowsRight1X=screenW/2;
        arrowsRight1Y=screenH/2+530;
        arrowsRight1W=screenW/2;
        arrowsRight1H=arrowsLeft1H=screenH-(screenH/2+530);

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

//        paint.setColor(Assist.getColor(res, R.color.PLAYER));
        /**
         * 画小汽车
         */

        bitmapCar= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.player);
        bitmapCar = Bitmap.createScaledBitmap(bitmapCar, (int) carW, (int) carH, true);
        canvas.drawBitmap(bitmapCar, carX, carY, paint);

        /**
         * 设置半透明
         */
        int opacity = 150;
        paint.setAlpha(opacity);

        /**
         * 左箭头
         */
        bitmapL= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.arrowsleft1);
        bitmapL = Bitmap.createScaledBitmap(bitmapL,  arrowsLeft1W, arrowsLeft1H, true);
        canvas.drawBitmap(bitmapL, arrowsLeft1X ,arrowsLeft1Y, paint);

        /**
         * 右箭头
         */
        bitmapR= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.arrowsright1);
        bitmapR = Bitmap.createScaledBitmap(bitmapR,  arrowsRight1W, arrowsRight1H, true);
        canvas.drawBitmap(bitmapR, arrowsRight1X,arrowsRight1Y, paint);
    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onTouchEvent(MotionEvent event,Canvas canvas, Paint paint) {
        touchX = (int) event.getX();
        touchY = (int) event.getY();

        bitmapL= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.arrowsleft1);
        bitmapL = Bitmap.createScaledBitmap(bitmapL, arrowsLeft1W, arrowsLeft1H, true);

        bitmapL2= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.arrowsleft2);
        bitmapL2 = Bitmap.createScaledBitmap(bitmapL2, arrowsLeft1W, arrowsLeft1H, true);

        bitmapR= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.arrowsright1);
        bitmapR = Bitmap.createScaledBitmap(bitmapR, arrowsRight1W, arrowsRight1H, true);

        bitmapR2= BitmapFactory.decodeResource(surface.getResources(), R.mipmap.arrowsright1);
        bitmapR2 = Bitmap.createScaledBitmap(bitmapR2, arrowsRight1W, arrowsRight1H, true);


        /**
         * 当前车在way2
         */
        if(touchX > screenW/2 && touchY > screenH/2+530 && carX==way2-95) {

            Matrix matrix=new Matrix();
            matrix.postRotate(30);
            matrix.postTranslate(carX+carW,carY-35);
            canvas.drawBitmap(bitmapCar, matrix, paint);
            carX=way3-95;
        }

        if(touchX < screenW/2 && touchY > screenH/2+530 && carX==way2-95) {

            Matrix matrix=new Matrix();
            matrix.postRotate(-30);
            matrix.postTranslate(carX-200,carY+50);
            canvas.drawBitmap(bitmapCar, matrix, paint);
            carX=way1-95;
        }

        /**
         * 当前车在way1
         */
        if(touchX > screenW/2 && touchY > screenH/2+530 && carX==way1-95) {

            Matrix matrix=new Matrix();
            matrix.postRotate(30);
            matrix.postTranslate(carX+carW,carY-35);
            canvas.drawBitmap(bitmapCar, matrix, paint);
            carX = way2-95;
        }


        /**
         * 当前车在way3
         */

        if(touchX < screenW/2 && touchY > screenH/2+530 && carX==way3-95) {
            Matrix matrix=new Matrix();
            matrix.postRotate(-30);
            matrix.postTranslate(carX-200,carY+50);
            canvas.drawBitmap(bitmapCar, matrix, paint);
            carX=way2-95;
        }
        /**
         * 按键效果
         */
        if(touchX < screenW/2 && touchY > screenH/2+530 && event.getAction()==MotionEvent.ACTION_DOWN){
            canvas.drawBitmap(bitmapL2, arrowsLeft1X ,arrowsLeft1Y, paint);
        }
        if(touchX < screenW/2 && touchY > screenH/2+530 && event.getAction()==MotionEvent.ACTION_UP){
            canvas.drawBitmap(bitmapL, arrowsLeft1X ,arrowsLeft1Y, paint);
        }

        if(touchX > screenW/2 && touchY > screenH/2+530 && event.getAction()==MotionEvent.ACTION_DOWN){
            canvas.drawBitmap(bitmapR2, arrowsRight1X ,arrowsRight1Y, paint);
        }
        if(touchX > screenW/2 && touchY > screenH/2+530 && event.getAction()==MotionEvent.ACTION_UP){
            canvas.drawBitmap(bitmapR, arrowsRight1X ,arrowsRight1Y, paint);
        }
    }

    public float getCarX(){return carX;}
    public float getCarY(){return carY;}
    public float getCarW(){return carW;}
    public float getCarH(){return carH;}
}

package com.example.vin.cargo.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.vin.cargo.R;
import com.example.vin.cargo.game.GameSurface;
import com.example.vin.cargo.utils.Assist;

/**
 * Background
 *
 * @author: Vin
 * @time: 2016/2/5 21:10
 */
public class Background extends BaseLayer {



    private float flag1;             //第一个旗子旗杆底部纵坐标
    private float flag2;

    private float speed;             //车速（参照物移动速度）
    private float acc;             //加速度


    /**
     * 构造函数
     *
     * @param surface
     */
    public Background(GameSurface surface) {
        super(surface);

        flag1=screenH/2;
        flag2=-150;

        speed=10;
        acc=1;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Assist.getColor(res, R.color.ROAD));                        //马路
        canvas.drawRect(line1, 0, line4, screenH, paint);
        paint.setColor(Color.WHITE);                                             //界线
        canvas.drawRect(line2 - 5, 0, line2 + 5, screenH, paint);
        canvas.drawRect(line3 - 5, 0, line3 + 5, screenH, paint);
        paint.setColor(Assist.getColor(res, R.color.GRASS));                  //草地
        canvas.drawRect(0, 0, line1, screenH, paint);
        canvas.drawRect(line4, 0, screenW, screenH, paint);

        paint.setColor(Color.RED);
        canvas.drawRect(line0, flag1 - 150, line0 + 50, flag1 - 75, paint);   //左侧第一个旗子
        paint.setColor(Color.WHITE);
        canvas.drawRect(line0 - 5, flag1 - 150,line0,flag1, paint);            //左侧第一个旗子的杆

        paint.setColor(Color.RED);
        canvas.drawRect(line0, flag2 - 150, line0 + 50, flag2 - 75, paint);   //左侧第二个旗子
        paint.setColor(Color.WHITE);
        canvas.drawRect(line0 - 5, flag2 - 150,line0,flag2, paint);            //左侧第二个旗子的杆

        paint.setColor(Color.RED);
        canvas.drawRect(line5, flag1 - 150, line5 + 50, flag1 - 75, paint);   //右侧第一个旗子
        paint.setColor(Color.WHITE);
        canvas.drawRect(line5 - 5, flag1 - 150,line5, flag1, paint);            //右侧第一个旗子的杆

        paint.setColor(Color.RED);
        canvas.drawRect(line5, flag2 - 150, line5 + 50, flag2 - 75, paint);   //右侧第二个旗子
        paint.setColor(Color.WHITE);
        canvas.drawRect(line5-5,flag2 - 150,line5, flag2, paint);            //右侧第二个旗子的杆

    }

    @Override
    public void logic() {
        flag1+=speed;
        speed+=acc;

        if (speed>=200){
            speed-=acc;
        }

        if(flag1-150>=screenH){                                 //参照物移动
            flag1=-150;
        }

        flag2+=speed;
        if(flag2-150>=screenH){
            flag2=-150;
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {


    }

    public float getSpeed(){
        return speed;
    }

}


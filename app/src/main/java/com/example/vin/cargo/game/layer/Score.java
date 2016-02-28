package com.example.vin.cargo.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.example.vin.cargo.game.GameSurface;
import com.example.vin.cargo.utils.Constants;

/**
 * Score
 *
 * @author: Vin
 * @time: 2016/2/5 21:11
 */
public class Score extends BaseLayer{

    private float scoreX1,scoreY1;
    private float scoreX2,scoreY2;
    private int scoreMax;
    private   int sco;
    private long startTime;
    private long endTime;
    private boolean isStart;

    /**
     * 构造函数
     *
     * @param surface
     */
    public Score(GameSurface surface) {
        super(surface);
        sco=0;
        scoreX1=20;
        scoreY1=100;
        scoreX2=20;
        scoreY2=200;

        isStart=true;

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        paint.setTypeface(Typeface.SERIF);

        switch (surface.gameState){
            case Constants.GAME_START:
                canvas.drawText("BEST SCORE："+scoreMax+"s",scoreX1,scoreY1,paint);
                break;
            case Constants.GAMING:
                paint.setTextSize(70);
                canvas.drawText("score："+'\n'+sco+"s",scoreX1,scoreY1,paint);
                break;
            case Constants.GAME_OVER:
                paint.setTextSize(100);
                canvas.drawText("BEST SCORE："+scoreMax+"s",scoreX1,scoreY1,paint);
                canvas.drawText("Your score："+sco+"s",scoreX2,scoreY2,paint);

        }
    }

    @Override
    public void logic() {

        if(isStart){
            startTime=System.currentTimeMillis();
            isStart=false;
        }
            endTime=System.currentTimeMillis();
        sco=(int)(endTime-startTime)/1000;
        if(sco>scoreMax){
            surface.setScoreMax(sco);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onTouchEvent(MotionEvent event, Canvas canvas, Paint paint) {

    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }
    public float getScore(){return sco;}
}

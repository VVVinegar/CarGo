package com.example.vin.cargo.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.example.vin.cargo.game.layer.Background;
import com.example.vin.cargo.game.layer.Barrier;
import com.example.vin.cargo.game.layer.Player;
import com.example.vin.cargo.game.layer.Score;
import com.example.vin.cargo.game.layer.Start;
import com.example.vin.cargo.utils.Constants;

/**
 * GameSurface
 *
 * @author: Vin
 * @time: 2016/2/5 21:09
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    public boolean flag;
    public int gameState;             //游戏状态

    private Canvas canvas;           //画布
    private Paint paint;              //画笔
    private SurfaceHolder holder;          //设置监听器
    private Thread thread;
    private int scoreMax;

    private Background background;
    private Barrier barrier;
    private Player player;
    private Score score;
    private Start start;

    public GameSurface(Context context) {
        super(context);
        init();
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public void init(){
        holder=getHolder();
        holder.addCallback(this);

        paint=new Paint();
        paint.setAntiAlias(true);
        scoreMax=0;
    }

    public void initGame(){
        gameState= Constants.GAME_START;

        background=new Background(this);
        barrier=new Barrier(this);
        player=new Player(this);
        score=new Score(this);
        start=new Start(this);
        score.setScoreMax(scoreMax);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initGame();
        flag=true;
        thread=new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag=false;
    }

    /**
     * 画图
     * @param canvas
     */
    public void myDraw(Canvas canvas){

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        switch (gameState){
            case Constants.GAME_START:
                start.draw(canvas,paint);
                score.draw(canvas,paint);
                break;
            case Constants.GAMING:
                background.draw(canvas, paint);
                player.draw(canvas, paint);
                barrier.setSpeed(background.getSpeed());
                barrier.draw(canvas, paint);
                score.draw(canvas,paint);
                break;
            case Constants.GAME_OVER:
                start.draw(canvas,paint);
                break;
            default:
                break;
        }
    }

    /**
     * 逻辑
     */
    public void  logic(){

        switch (gameState){
            case Constants.GAME_START:

                break;
            case Constants.GAMING:
                background.logic();
                player.logic();
                barrier.setSpeed(background.getSpeed());
                barrier.setCarX(player.getCarX());
                barrier.setCarY(player.getCarY());
                barrier.setCarW(player.getCarW());
                barrier.setCarH(player.getCarH());
                barrier.logic();
                score.logic();
                break;
            case Constants.GAME_OVER:
                initGame();
                break;
            default:
                break;
        }

    }


    public boolean onTouchEvent(MotionEvent event){

        switch (gameState){
            case Constants.GAME_START:
                start.onTouchEvent(event);
                break;
            case Constants.GAMING:

                player.onTouchEvent(event);
                barrier.onTouchEvent(event);
                break;
            case Constants.GAME_OVER:
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);                         //super?
    }

    @Override
    public void run() {
        while(flag){
            long start = System.currentTimeMillis();

            canvas = holder.lockCanvas();
            if(null != canvas){
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
            logic();

            long end = System.currentTimeMillis();

            if(end - start < 10){
                try {
                    Thread.sleep(50 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void setGameState(int gameState){
        this.gameState=gameState;
    }

    public void setScoreMax(int scoreMax) {
        this.scoreMax = scoreMax;
    }
}
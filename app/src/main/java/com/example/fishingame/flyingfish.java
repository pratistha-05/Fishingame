package com.example.fishingame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class flyingfish extends View {
    private Bitmap fish[] = new Bitmap[2];
    private int fishx = 10;
    private int fishy;
    private int fishspeed;
    private int canvaswidth, canvasheight;

    private int yellowx,yellowy,yellowspeed=16;
    private Paint yellowPaint=new Paint();
    private int greenx,greeny,greenspeed=20;
    private Paint greenPaint=new Paint();
    private int redx,redy,redspeed=20;
    private Paint redPaint=new Paint();
    private int score,lifish;


    private boolean touch = false;
    private Bitmap backgroundimage;
    private Paint scorepaint = new Paint();
    private Bitmap life[] = new Bitmap[2];

    public flyingfish(Context context) {
        super(context);
        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);

        backgroundimage = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);
        fishy = 550;
        score=0;
        lifish=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvaswidth = canvas.getWidth();
        canvasheight = canvas.getHeight();
        canvas.drawBitmap(backgroundimage, 0, 0, null);
        int minfishy = fish[0].getHeight();
        int maxfishy = canvasheight - fish[0].getHeight() * 3;
        fishy = fishy + fishspeed;

        if (fishy < minfishy)
            fishy = minfishy;
        if (fishy > maxfishy)
            fishy = maxfishy;
        fishspeed = fishspeed + 2;
        if (touch)
        {
            canvas.drawBitmap(fish[1], fishx, fishy, null);
            touch = false;
        }
        else
            {
            canvas.drawBitmap(fish[0], fishx, fishy, null);
        }

        yellowx=yellowx-yellowspeed;
        if (hitBallChecker(yellowx,yellowy))
        {
            score+=10;
            yellowx=-80;
        }

        if(yellowx<0)
        {
            yellowx=canvaswidth+21;
            yellowy=(int)Math.floor(Math.random()*(maxfishy-minfishy))+minfishy;
        }
        canvas.drawCircle(yellowx,yellowy,25 ,yellowPaint);

        greenx=greenx-greenspeed;
        if (hitBallChecker(greenx,greeny))
        {
            score+=20;
            greenx=-80;
        }

        if(greenx<0)
        {
            greenx=canvaswidth+21;
            greeny=(int)Math.floor(Math.random()*(maxfishy-minfishy))+minfishy;
        }
        canvas.drawCircle(greenx,greeny,30 ,greenPaint);

        redx=redx-redspeed;
        if (hitBallChecker(redx,redy))
        {
            lifish--;

            redx=-80;
            if(lifish==0)
            {
                Toast.makeText(getContext(), "GAME OVER", Toast.LENGTH_SHORT).show();
                Intent gointent = new Intent(getContext(), gameover.class);
                gointent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(gointent);

            }
        }

        if(redx<0)
        {
            redx=canvaswidth+21;
            redy=(int)Math.floor(Math.random()*(maxfishy-minfishy))+minfishy;
        }
        canvas.drawCircle(redx,redy,30 ,redPaint);
        canvas.drawText("Score : "+score, 20, 60, scorepaint);


        for(int i=0;i<3;i++)
        {
            int x=(int)(580+life[0].getWidth()*1.5*i);
            int y=30;

            if(i<lifish)
                canvas.drawBitmap(life[0], x, y, null);
            else
                canvas.drawBitmap(life[1], x, y, null);


        }




    }
    public Boolean hitBallChecker(int x,int y)
    {
        if(fishx<x && x<(fishx+fish[0].getWidth()) && fishy<y && y<(fishy+fish[0].getHeight()))
            return true;
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;
            fishspeed = -22;
        }

        return true;
    }
}

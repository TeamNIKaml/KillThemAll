package com.nikhil.kta.model;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.nikhil.kta.view.GameView;

public class  Sprite {
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
  
 
    private GameView gameView;
    private Bitmap bmp;
    private int x = 0;
    private int y = 0;
    private int xSpeed;
    private int ySpeed;
    private int currentFrame = 0;
    private int width;
    private int height;
    
    private String name;
    
    private int type;//0=Good;1=bad;

    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sprite(GameView gameView, Bitmap bmp) {
          this.width = bmp.getWidth() / Constant.BMP_COLUMNS;
          this.height = bmp.getHeight() / Constant.BMP_ROWS;
          this.gameView = gameView;
          this.bmp = bmp;

          Random rnd = new Random();
          x = rnd.nextInt(gameView.getWidth() - width);
         // y = rnd.nextInt(gameView.getHeight() - height);
          
          
          
          
          
        //  x = 0;
          
        
          
          
          
    }
	
	public void setSpeed()
	{
		if(type == Constant.TYPE_BAD)
		{
			xSpeed = Constant.SPEED_SPRITE_BAD;
			ySpeed = Constant.SPEED_SPRITE_BAD;
			y = 0;
		}
		else
		{
			 y = gameView.getHeight() - height;
			xSpeed = Constant.SPEED_SPRITE_GOOD;
			ySpeed = Constant.SPEED_SPRITE_GOOD;
		}
		
		  Random rnd = new Random();
		 int xboost =( rnd.nextInt()%3);
		 int yboost =(rnd.nextInt(100)) % 3;
		 
		 if((rnd.nextInt() % 2) == 0)
		 {
			 xboost++;yboost++;
		 }
		 else if((rnd.nextInt() % 3) == 0)
		 {
			 xboost *=-1; yboost *= -1;
		 }
		 else if((rnd.nextInt() % 7) == 0)
		 {
			 xboost *=-1;
		 }
		 else
		 {
			 yboost *=-1;
		 }
		  
	}
	

    private void update() {
          if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {
                 xSpeed = -xSpeed;
          }
          x = x + xSpeed;
          if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {
                 ySpeed = -ySpeed;
          }
          y = y + ySpeed;
          currentFrame = ++currentFrame % Constant.BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
          update();
          int srcX = currentFrame * width;
          int srcY = getAnimationRow() * height;
          Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
          Rect dst = new Rect(x, y, x + width, y + height);
          canvas.drawBitmap(bmp, src, dst, null);
    }

    private int getAnimationRow() {
          double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
          int direction = (int) Math.round(dirDouble) % Constant.BMP_ROWS;
          return DIRECTION_TO_ANIMATION_MAP[direction];
    }

    public boolean isCollition(float x2, float y2) {
          return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

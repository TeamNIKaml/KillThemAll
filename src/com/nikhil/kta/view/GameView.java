package com.nikhil.kta.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nikhil.kta.application.activity.R;
import com.nikhil.kta.model.Constant;
import com.nikhil.kta.model.GameLevel;
import com.nikhil.kta.model.Sprite;
import com.nikhil.kta.model.TempSprite;
import com.nikhil.kta.thread.GameLoopThread;

public class GameView extends SurfaceView {
	private GameLoopThread gameLoopThread;
	private List<Sprite> spriteList = new ArrayList<Sprite>();
	private List<Sprite> goodSpriteList = new ArrayList<Sprite>();
	private List<Sprite> badSpriteList = new ArrayList<Sprite>();
	private List<TempSprite> temps = new ArrayList<TempSprite>();
	private long lastClick;
	private Bitmap bmpBlood;
	private Random rnd = new Random();
	private GameLevel gameLevel = new GameLevel(this, getContext());

	private int color = Math.abs((rnd.nextInt(100) % 9));

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	
	private void updateSpriteList() {
		// TODO Auto-generated method stub
		for (Sprite sprite : spriteList)
		{
			if(goodSpriteList.contains(sprite))
				continue;
			if(badSpriteList.contains(sprite))
				continue;
			if(sprite.getType() == Constant.TYPE_BAD)
				badSpriteList.add(sprite);
			else
				goodSpriteList.add(sprite);
		}
	}

	

	public GameView(Context context) {
		super(context);
		gameLoopThread = new GameLoopThread(this);
		getHolder().addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// createspriteList();
				spriteList = gameLevel.startLevel();
				
					updateSpriteList();
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
			}

		

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
		bmpBlood = BitmapFactory.decodeResource(getResources(),
				R.drawable.blood1);
	}

	@Override
	public void onDraw(Canvas canvas) {

		canvas.drawColor(Constant.BACKGROUND_COLOR[color]);
		for (int i = temps.size() - 1; i >= 0; i--) {
			temps.get(i).onDraw(canvas);
		}
		for (Sprite sprite : badSpriteList) {
			sprite.onDraw(canvas);
		}
		for (Sprite sprite : goodSpriteList) {
			sprite.onDraw(canvas);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (System.currentTimeMillis() - lastClick > 300) {
			lastClick = System.currentTimeMillis();
			float x = event.getX();
			float y = event.getY();
			synchronized (getHolder()) {
				for (int i = spriteList.size() - 1; i >= 0; i--) {
					Sprite sprite = spriteList.get(i);
					if (sprite.isCollition(x, y)) {

						if (sprite.getType() == Constant.TYPE_GOOD) {
							gameLevel
									.setGoodCount(gameLevel.getGoodCount() - 1);
							gameLevel
									.setGoodKilled(gameLevel.getGoodKilled() + 1);
						} else {
							gameLevel.setBadCount(gameLevel.getBadCount() - 1);
							gameLevel
									.setBadKilled(gameLevel.getBadKilled() + 1);
						}

						if (gameLevel.getBadCount() < 3)
						{
							gameLevel.createSpritesBad();
							updateSpriteList();
							
						}
						if (gameLevel.getGoodCount() < 3)
						{
							gameLevel.createSpritesGood();
							updateSpriteList();
						}
						
						if(goodSpriteList.contains(sprite))
							goodSpriteList.remove(sprite);
						else
							badSpriteList.remove(sprite);

						spriteList.remove(sprite);

						gameLevel.checkLevelUp();

						temps.add(new TempSprite(temps, this, x, y, bmpBlood));
						break;
					}
				}
			}
		}
		return true;
	}
}

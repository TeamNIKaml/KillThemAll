package com.nikhil.kta.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.nikhil.kta.application.activity.R;
import com.nikhil.kta.model.GameLevel;
import com.nikhil.kta.model.Sprite;
import com.nikhil.kta.model.TempSprite;
import com.nikhil.kta.thread.GameLoopThread;

public class GameView extends SurfaceView {
	private GameLoopThread gameLoopThread;
	private List<Sprite> spriteList = new ArrayList<Sprite>();
	private List<TempSprite> temps = new ArrayList<TempSprite>();
	private long lastClick;
	private Bitmap bmpBlood;
	
	

	private GameLevel gameLevel = new GameLevel(this, getContext());

	
	
	
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
		canvas.drawColor(Color.BLACK);
		for (int i = temps.size() - 1; i >= 0; i--) {
			temps.get(i).onDraw(canvas);
		}
		for (Sprite sprite : spriteList) {
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

						if (sprite.getType() == 0) {
							gameLevel
									.setGoodCount(gameLevel.getGoodCount() - 1);
							gameLevel
									.setGoodKilled(gameLevel.getGoodKilled() + 1);
						} else {
							gameLevel.setBadCount(gameLevel.getBadCount() - 1);
							gameLevel
									.setBadKilled(gameLevel.getBadKilled() + 1);
						}
						
						if(gameLevel.getBadCount() <3)
							gameLevel.createSpritesBad();
						if(gameLevel.getGoodCount() < 3)
							gameLevel.createSpritesGood();

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

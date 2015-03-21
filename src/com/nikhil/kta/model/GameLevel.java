package com.nikhil.kta.model;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.nikhil.kta.view.GameView;

public class GameLevel {
	
	 private List<Sprite> spriteList = new ArrayList<Sprite>();
	   
	 private GameView gameView;
	 
	 private Context context;
	 
	 private   int badKilled,goodKilled;
	 
	 private long points;
	
	private long level=1;
	
	public int getBadKilled() {
		return badKilled;
	}


	public void setBadKilled(int badKilled) {
		this.badKilled = badKilled;
	}


	public int getGoodKilled() {
		return goodKilled;
	}


	public void setGoodKilled(int goodKilled) {
		this.goodKilled = goodKilled;
	}





	private int goodCount=0;
		
	public GameLevel(GameView gameView, Context context) {
		super();
		this.gameView = gameView;
		this.context = context;
		badKilled = goodKilled =0;
	}





	private int badCount = 0 ;
	
	public List startLevel()
	{
		createSpritesGood();
		createSpritesBad();
		return spriteList;
	}
	
	
	 public int getGoodCount() {
		return goodCount;
	}


	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}


	public int getBadCount() {
		return badCount;
	}


	public void setBadCount(int badCount) {
		this.badCount = badCount;
	}


	public void createSpritesBad() {
		 Sprite sprite ;
	        sprite  = createSprite(Constant.SPRITE_BAD1,Constant.NAME_BAD1,Constant.TYPE_BAD);
	        badCount++;
	        spriteList.add(sprite);
	        sprite  = createSprite(Constant.SPRITE_BAD2,Constant.NAME_BAD2,Constant.TYPE_BAD);
	        badCount++;
	        spriteList.add(sprite);
	        sprite  = createSprite(Constant.SPRITE_BAD3,Constant.NAME_BAD3,Constant.TYPE_BAD);
	        badCount++;
	        spriteList.add(sprite);
	        sprite  = createSprite(Constant.SPRITE_BAD4,Constant.NAME_BAD4,Constant.TYPE_BAD);
	        badCount++;
	        spriteList.add(sprite);
	        sprite  = createSprite(Constant.SPRITE_BAD5,Constant.NAME_BAD5,Constant.TYPE_BAD);
	        badCount++;
	        spriteList.add(sprite);
	        sprite  = createSprite(Constant.SPRITE_BAD6,Constant.NAME_BAD6,Constant.TYPE_BAD);
	        badCount++;
	        spriteList.add(sprite);
        
   }
	 
	 
	 public void createSpritesGood() {
        Sprite sprite ;
        sprite  = createSprite(Constant.SPRITE_GOOD1,Constant.NAME_GOOD1,Constant.TYPE_GOOD);
        goodCount++;
        spriteList.add(sprite);
        sprite  = createSprite(Constant.SPRITE_GOOD2,Constant.NAME_GOOD2,Constant.TYPE_GOOD);
        goodCount++;
        spriteList.add(sprite);
        sprite  = createSprite(Constant.SPRITE_GOOD3,Constant.NAME_GOOD3,Constant.TYPE_GOOD);
        goodCount++;
        spriteList.add(sprite);
        sprite  = createSprite(Constant.SPRITE_GOOD4,Constant.NAME_GOOD4,Constant.TYPE_GOOD);
        goodCount++;
        spriteList.add(sprite);
        sprite  = createSprite(Constant.SPRITE_GOOD5,Constant.NAME_GOOD5,Constant.TYPE_GOOD);
        goodCount++;
        spriteList.add(sprite);
        sprite  = createSprite(Constant.SPRITE_GOOD6,Constant.NAME_GOOD6,Constant.TYPE_GOOD);
        goodCount++;
        spriteList.add(sprite);
        
       
        
   }
	 
	 
	 
	 
	 
	 private Sprite createSprite(int resouce,String name,int type) {
         Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), resouce);
         Sprite sprite = new Sprite(gameView, bmp);
         sprite.setName(name);
         sprite.setType(type);
         
         
         return sprite;
   }


	public void checkLevelUp() {
		// TODO Auto-generated method stub
		int killed = badKilled + goodKilled ;
		
		int bonus =(int) level % (Constant.COUNT_BAD_TOTAL + Constant.COUNT_GOOD_TOTAL);
		
		int count = bonus + 12;
		
		points +=  (badKilled * Constant.BAD_SCORE) + (goodKilled * Constant.GOOD_SCORE);
		
		if(killed == count)
		{
			level++;
			String levelStrng ="Level up "+"Points :"+String.valueOf(points)+" Level :"+ String.valueOf(level);
			
		
		
		Toast.makeText(context, levelStrng, Toast.LENGTH_LONG).show();
		
			
			badKilled = goodKilled =0;
		}
		
	}
	

	

}

package com.nikhil.kta.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;

import com.nikhil.kta.application.activity.R;


public class Constant {
	
	//BAD SPRITE
	public static final int SPRITE_BAD1 = R.drawable.bad1;
	public static final int SPRITE_BAD2 = R.drawable.bad2;
	public static final int SPRITE_BAD3 = R.drawable.bad3;
	public static final int SPRITE_BAD4 = R.drawable.bad4;
	public static final int SPRITE_BAD5 = R.drawable.bad5;
	public static final int SPRITE_BAD6 = R.drawable.bad6;
	
	//Good SPRITE
	public static final int SPRITE_GOOD1 = R.drawable.good1;
	public static final int SPRITE_GOOD2 = R.drawable.good2;
	public static final int SPRITE_GOOD3 = R.drawable.good3;
	public static final int SPRITE_GOOD4 = R.drawable.good4;
	public static final int SPRITE_GOOD5 = R.drawable.good5;
	public static final int SPRITE_GOOD6 = R.drawable.good6;
	
	//SPEED BAD SPRITE
	
	public static final int SPEED_SPRITE_BAD = 15;
	
	
	//SPEED Good SPRITE
	public static final int SPEED_SPRITE_GOOD = 13;
	
	
	
	public static final String NAME_GOOD1 = "Good1";
	public static final String NAME_GOOD2 = "Good2";
	public static final String NAME_GOOD3 = "Good3";
	public static final String NAME_GOOD4 = "Good4";
	public static final String NAME_GOOD5 = "Good5";
	public static final String NAME_GOOD6 = "Good6";
	
	
	
	public static final String NAME_BAD1 = "Bad1";
	public static final String NAME_BAD2 = "Bad2";
	public static final String NAME_BAD3 = "Bad3";
	public static final String NAME_BAD4 = "Bad4";
	public static final String NAME_BAD5 = "Bad5";
	public static final String NAME_BAD6 = "Bad6";
	
	
	
	public static final int BMP_ROWS = 4;
	public static final int BMP_COLUMNS = 3;
	public static final int MAX_SPEED = 5;
	
	
	
	public static final int BAD_SCORE = 5;
	public static final int GOOD_SCORE = -3;
	
	
	public static final int TYPE_BAD = 1;
	public static final int TYPE_GOOD = 0;
	
	public static final int COUNT_GOOD_TOTAL=6;
	public static final int COUNT_BAD_TOTAL=6;
	
	public static String SHARED_PREFERENCE_NAME = "KillThemAll";
	
	
	public static int[] BACKGROUND_COLOR ={Color.BLACK,Color.BLUE,Color.CYAN,Color.DKGRAY,
	Color.GREEN,Color.MAGENTA,Color.GRAY,Color.LTGRAY,Color.YELLOW,Color.WHITE};	
	
	
	
	
	
	
	

}

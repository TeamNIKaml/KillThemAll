package com.nikhil.kta.application.activity;

import com.nikhil.kta.view.GameView;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class GameFragment extends Fragment {



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		return new GameView(getActivity()) ;
	}
}
	
	
	
	
	

	

	

	



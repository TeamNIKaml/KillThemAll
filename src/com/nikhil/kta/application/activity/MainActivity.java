package com.nikhil.kta.application.activity;



import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private static TextView pointTextView;
	
	Fragment fragment = null;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        init();
    }


	private void init() {
		// TODO Auto-generated method stub
		pointTextView = (TextView) findViewById(R.id.textView1);
		if(fragment == null)
		{
FragmentManager fragmentManager = getFragmentManager();
			
			fragmentManager.beginTransaction()
					.replace(R.id.container, new GameFragment()).commit();
		}
		
		
	}
	
	public static void setScore(String level,String point)
	{
		String formated = "Level :"+level+"\t\tPoints :"+point;
		pointTextView.setText(formated);
	}
}

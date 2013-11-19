package zigtraka.nfc.reta_x;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public abstract class BaseActivity extends Activity {
	protected abstract int getResourceLayoutId();

	protected Button back, home, exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getResourceLayoutId());

		// define layout parameters for relative layout
		LayoutParams rLParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		// define layout parameters for linear layout
		LayoutParams lLParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lLParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 1);

		// define layout parameters for buttons
		LinearLayout.LayoutParams bLParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);

		// define relativelayout which will occupy whole screen
		RelativeLayout relativeLayout = new RelativeLayout(
				getApplicationContext());
		relativeLayout.setBackgroundColor(0); // setbackground color tranperent

		// define linearlayout which contains nevigation buttons
		LinearLayout linearLayout = new LinearLayout(getApplicationContext());
		linearLayout.setBackgroundColor(0); // setbackground color tranperent

		// nevigation buttons
		{
			// define back nevigation button
			back = new Button(getApplicationContext());
			back.setLayoutParams(bLParams);
			back.setText("Back");
			back.setBackgroundResource(R.drawable.back_button);

			// define home nevigation button
			home = new Button(getApplicationContext());
			home.setLayoutParams(bLParams);
			home.setText("Home");
			home.setBackgroundResource(R.drawable.positive_buttons);

			// define exit nevigation button
			exit = new Button(getApplicationContext());
			exit.setLayoutParams(bLParams);
			exit.setText("Exit");
			exit.setBackgroundResource(R.drawable.negative_buttons);

			// add buttons to linearlayout
			linearLayout.addView(back);
			linearLayout.addView(home);
			linearLayout.addView(exit);
		}

		// add linearlayout to relativelayout
		relativeLayout.addView(linearLayout, lLParams);

		// add relative layout to screen
		addContentView(relativeLayout, rLParams);

		{
			// click event for home button
			home.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivity(new Intent(getApplicationContext(),
							Admin.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
				}
			});

			// click event for exit button
			exit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 Intent intent = new Intent(Intent.ACTION_MAIN);
					    intent.addCategory(Intent.CATEGORY_HOME);
					    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
					    startActivity(intent);
				}
			});
			
			// click event for back button
			back.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				finish();	
				}
			});
		}
	}
}

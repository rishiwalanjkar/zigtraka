package zigtraka.nfc.reta_x;

import deploy.appdata.Creater;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		Creater.deploy(getApplicationContext());
		
		Button Signin = (Button) findViewById(R.id.signinbutton);
		Signin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(), Admin.class));
			}
		});
		Button Cancel = (Button) findViewById(R.id.cancelbutton);
		Cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
	}

}

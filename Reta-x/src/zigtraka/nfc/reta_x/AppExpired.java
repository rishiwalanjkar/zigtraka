package zigtraka.nfc.reta_x;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AppExpired extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_expired);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_expired, menu);
		return true;
	}

}

package zigtraka.nfc.reta_x;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class About extends Activity {
String txtviewcontent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		TextView txtview=(TextView) findViewById(R.id.about_details);
		txtviewcontent=getResources().getString(R.string.Application);
		txtview.setText(txtviewcontent);
	}

}

package zigtraka.nfc.reta_x;

import android.os.Bundle;

import android.widget.TextView;

public class About extends BaseActivity {
String txtviewcontent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		TextView txtview=(TextView) findViewById(R.id.about_details);
		txtviewcontent=getResources().getString(R.string.Application);
		txtview.setText(txtviewcontent);
	}
	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.about;
	}

}

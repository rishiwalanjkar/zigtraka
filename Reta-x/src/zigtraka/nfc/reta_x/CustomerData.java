package zigtraka.nfc.reta_x;

import android.os.Bundle;

import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class CustomerData extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_data);
		TabHost tabhost=getTabHost();
		TabHost.TabSpec spec;
		Intent intent=new Intent(this,CustomerSurveyForm.class);
		spec=tabhost.newTabSpec("Survey Form").setIndicator("Survey Form").setContent(intent);
		tabhost.addTab(spec);
		 intent=new Intent(this,CustomerAnalysis.class);
		spec=tabhost.newTabSpec("Analysis").setIndicator("Analysis").setContent(intent);
		tabhost.addTab(spec);
	}

	

	

}

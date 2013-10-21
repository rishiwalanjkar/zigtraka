package zigtraka.nfc.reta_x;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.widget.TabHost;

public class Dashboard extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		TabHost tabhost=getTabHost();
		TabHost.TabSpec spec;
		Intent intent=new Intent(this,KpiForAssets.class);
		
		spec=tabhost.newTabSpec("KPI for assets").setIndicator("KPI for assets").setContent(intent);
		tabhost.addTab(spec);
		intent=new Intent(this,KpiForCustomers.class);
		spec=tabhost.newTabSpec("KPI for customers").setIndicator("KPI for customers").setContent(intent);
		tabhost.addTab(spec);
		intent=new Intent(this,Chartsandgraphs.class);
		spec=tabhost.newTabSpec("KPI charts and graphs").setIndicator("KPI charts and graphs").setContent(intent);
		tabhost.addTab(spec);
		tabhost.setCurrentTab(2);
	}

}

package zigtraka.nfc.reta_x;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class KpiForAssets extends Activity {

	private TextView AssetNotScanned,totaltaps,PercentIncreaseInTaps;
	private ListView TopLeastTapped,TopMostTapped;
private MyDatabaseHelper mydatabasehelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kpi_for_assets);
		
		mydatabasehelper=new MyDatabaseHelper(getApplicationContext());
		
		AssetNotScanned=(TextView) findViewById(R.id.kpi_for_assets_AssetsnotscannedtextView);
		totaltaps=(TextView)findViewById(R.id.kpi_for_assets_totaltapstextView);
		PercentIncreaseInTaps=(TextView)findViewById(R.id.kpi_for_assets_increaseintapstottextView);
		
		AssetNotScanned.setText(String.valueOf(mydatabasehelper.dashboard()));
		totaltaps.setText(String.valueOf(mydatabasehelper.getTotalTaps()));
        PercentIncreaseInTaps.setText(String.valueOf(mydatabasehelper.getIncreaseInTap()));
        
        TopLeastTapped=(ListView)findViewById(R.id.kpi_for_assets_top_least_tappedlistView);
        
        TopLeastTapped.setAdapter(new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_expandable_list_item_1,
				mydatabasehelper.getLeastTapItems()));

        TopMostTapped=(ListView)findViewById(R.id.kpi_for_assets_top_most_tappedlistView);
        String[] details=mydatabasehelper.getMostTapItems();
        TopMostTapped.setAdapter(new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_list_item_1,
				details));

	}

}

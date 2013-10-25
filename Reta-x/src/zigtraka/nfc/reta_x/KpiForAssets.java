package zigtraka.nfc.reta_x;

import db.Access.DbForKpiForAssetsActivity;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class KpiForAssets extends BaseActivity {

	private TextView AssetNotScanned,totaltaps,PercentIncreaseInTaps;
	private ListView TopLeastTapped,TopMostTapped;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		AssetNotScanned=(TextView) findViewById(R.id.kpi_for_assets_AssetsnotscannedtextView);
		totaltaps=(TextView)findViewById(R.id.kpi_for_assets_totaltapstextView);
		PercentIncreaseInTaps=(TextView)findViewById(R.id.kpi_for_assets_increaseintapstottextView);
		
		AssetNotScanned.setText(String.valueOf(DbForKpiForAssetsActivity.dashboard()));
		totaltaps.setText(String.valueOf(DbForKpiForAssetsActivity.getTotalTaps()));
        PercentIncreaseInTaps.setText(String.valueOf(DbForKpiForAssetsActivity.getIncreaseInTap()));
        
        TopLeastTapped=(ListView)findViewById(R.id.kpi_for_assets_top_least_tappedlistView);
        
        TopLeastTapped.setAdapter(new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_expandable_list_item_1,
				DbForKpiForAssetsActivity.getLeastTapItems()));

        TopMostTapped=(ListView)findViewById(R.id.kpi_for_assets_top_most_tappedlistView);
        String[] details=DbForKpiForAssetsActivity.getMostTapItems();
        TopMostTapped.setAdapter(new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_list_item_1,
				details));

	}
	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.kpi_for_assets;
	}

}

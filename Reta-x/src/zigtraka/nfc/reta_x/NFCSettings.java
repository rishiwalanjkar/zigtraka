package zigtraka.nfc.reta_x;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

public class NFCSettings extends BaseActivity {
	private ListView nfcSettings;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] AdminList = getResources()
				.getStringArray(R.array.NFCSettingsOptions);

		nfcSettings=(ListView)findViewById(R.id.nfcsettings_listView);
		
		nfcSettings.setAdapter(new NFCSettingsAdapter(getApplicationContext(),
				android.R.layout.simple_list_item_1, AdminList));

	}
	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.nfcsettings;
	}

}

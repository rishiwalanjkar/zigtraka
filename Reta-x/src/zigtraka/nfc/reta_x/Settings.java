package zigtraka.nfc.reta_x;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Settings extends BaseActivity {
private ListView settings;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] AdminList = getResources().getStringArray(
				R.array.SettingOptions);

		settings=(ListView)findViewById(R.id.settings_listView);
		
		settings.setAdapter(new SettingsAdapter(getApplicationContext(),
				android.R.layout.simple_list_item_1, AdminList));

		settings.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				if (position == 0)
					startActivity(new Intent(getApplicationContext(), UserProfile.class));
				if (position == 1)
					startActivity(new Intent(getApplicationContext(), NFCSettings.class));
				if (position == 2)
					startActivity(new Intent(getApplicationContext(), TestDb.class));
				if (position == 3)
					startActivity(new Intent(getApplicationContext(), Misc.class));
			}
		});
	}
	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.settings;
	}
	
}

package zigtraka.nfc.reta_x;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Settings extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] AdminList = getResources().getStringArray(
				R.array.SettingOptions);

		setListAdapter(new ListAdapter(getApplicationContext(),
				android.R.layout.simple_list_item_1, AdminList));

	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (position == 0)
			startActivity(new Intent(getApplicationContext(), UserProfile.class));
		if (position == 1)
			startActivity(new Intent(getApplicationContext(), NFCSettings.class));
		if (position == 2)
			startActivity(new Intent(getApplicationContext(), TestDb.class));
		if (position == 3)
			startActivity(new Intent(getApplicationContext(), Misc.class));
	}

}

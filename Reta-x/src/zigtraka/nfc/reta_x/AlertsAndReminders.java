package zigtraka.nfc.reta_x;

import android.app.ListActivity;
import android.os.Bundle;

public class AlertsAndReminders extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] AdminList = getResources().getStringArray(R.array.AlertRemindersOptions);

		setListAdapter(new ListAdapter(getApplicationContext(),
				android.R.layout.simple_list_item_1, AdminList));

	}

}

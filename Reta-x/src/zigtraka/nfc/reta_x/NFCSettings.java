package zigtraka.nfc.reta_x;

import android.app.ListActivity;
import android.os.Bundle;

public class NFCSettings extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] AdminList = getResources()
				.getStringArray(R.array.NFCSettingsOptions);

		setListAdapter(new NFCSettingsAdapter(getApplicationContext(),
				android.R.layout.simple_list_item_1, AdminList));

	}

}

package zigtraka.nfc.reta_x;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class NFCInteraction extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] AdminList = getResources().getStringArray(R.array.NFCInteractionOptions);

		setListAdapter(new NFCInteractionAdapter(getApplicationContext(),
				android.R.layout.simple_list_item_1, AdminList));

	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (position == 0)
			startActivity(new Intent(getApplicationContext(), GetProductInfo.class));
		if (position == 1)
		startActivity(new Intent(getApplicationContext(), StockTake.class));
		
	}

}

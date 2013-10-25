package zigtraka.nfc.reta_x;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Help extends BaseActivity {
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] AdminList = getResources().getStringArray(R.array.HelpOptions);

		listView=(ListView)findViewById(R.id.help_listView);
		
		listView.setAdapter(new HelpAdapter(getApplicationContext(),
				android.R.layout.simple_list_item_1, AdminList));
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				if (position == 0)
					startActivity(new Intent(getApplicationContext(), About.class));
				if (position == 1)
					startActivity(new Intent(getApplicationContext(), Support_Contact.class));
				if (position == 2)
					startActivity(new Intent(getApplicationContext(), AppUpdates.class));
				if (position == 3)
					startActivity(new Intent(getApplicationContext(), Subscription.class));
			}
			
		});

	}
	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.help;
	}

}

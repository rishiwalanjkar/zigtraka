package zigtraka.nfc.reta_x;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class AlertsAndReminders extends BaseActivity{
	private ExpandableListView listView1,listView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] AdminList = getResources().getStringArray(R.array.AlertRemindersOptions);

		listView1=(ExpandableListView)findViewById(R.id.alerts_and_remonders_expandableListView1);
		listView2=(ExpandableListView)findViewById(R.id.alerts_and_remonders_expandableListView2);
		
		listView1.setAdapter(new AlertsAndRemindersAdapter(getApplicationContext(),
				android.R.layout.simple_expandable_list_item_1, AdminList));
		listView2.setAdapter(new AlertsAndRemindersAdapter(getApplicationContext(),
				android.R.layout.simple_expandable_list_item_2, AdminList));

	}
	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.alerts_and_reminder;
	}

}

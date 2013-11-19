package zigtraka.nfc.reta_x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

public class AlertsAndReminders extends BaseActivity{
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] AdminList = getResources().getStringArray(R.array.AlertRemindersOptions);

		expListView = (ExpandableListView) findViewById(R.id.alerts_and_remonders_expandableListView1);
		
//		listView1=(ExpandableListView)findViewById(R.id.alerts_and_remonders_expandableListView1);
//		listView2=(ExpandableListView)findViewById(R.id.alerts_and_remonders_expandableListView2);
//		
//		listView1.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
//				android.R.layout.simple_expandable_list_item_1, AdminList));
//		listView2.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
//				android.R.layout.simple_expandable_list_item_2, AdminList));
		
		prepareListData();
		 
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
	}
	
	/*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Alerts");
        listDataHeader.add("Reminders");
        
        // Adding child data
        List<String> Alerts = new ArrayList<String>();
        Alerts.add("Items Sold");
        Alerts.add("Items Updated");
        Alerts.add("Items Missing");
        
        List<String> Reminders = new ArrayList<String>();
        Reminders.add("Daily");
        Reminders.add("Weekly");
        Reminders.add("Stock");
        
        listDataChild.put(listDataHeader.get(0), Alerts); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Reminders);
    }
	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.alerts_and_reminder;
	}

}

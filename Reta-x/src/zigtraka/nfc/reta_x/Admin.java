package zigtraka.nfc.reta_x;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * 
 * @author manish.s
 * 
 */

public class Admin extends Activity {
	GridView gridView;
	ArrayList<CustomGridViewItem> gridArray = new ArrayList<CustomGridViewItem>();
	CustomGridViewAdapter customGridAdapter;
    String[] AdminOprions;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin);
        AdminOprions=getResources().getStringArray(R.array.AdminOptions);
		// set grid view item
		Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.dashboard);
		Bitmap nfcIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.nfc);
		Bitmap alertsIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.alert);
		Bitmap searchIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.search);
		Bitmap settingsIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.settings);
		Bitmap helpIcon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.help);

		gridArray.add(new CustomGridViewItem(homeIcon, AdminOprions[0]));
		gridArray.add(new CustomGridViewItem(nfcIcon, AdminOprions[1]));
		gridArray.add(new CustomGridViewItem(alertsIcon, AdminOprions[2]));
		gridArray.add(new CustomGridViewItem(searchIcon, AdminOprions[3]));
		gridArray.add(new CustomGridViewItem(settingsIcon, AdminOprions[4]));
		gridArray.add(new CustomGridViewItem(helpIcon, AdminOprions[5]));

		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.custom_row_grid,
				gridArray);
		gridView.setAdapter(customGridAdapter);
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> grid, View v, int position,
					long id) {
				// TODO Auto-generated method stub
				if (position == 0)
					startActivity(new Intent(getApplicationContext(), Dashboard.class));
				if (position == 1)
					startActivity(new Intent(getApplicationContext(), NFCInteraction.class));
				if (position == 2)
					startActivity(new Intent(getApplicationContext(), AlertsAndReminders.class));
				if (position == 3)
					startActivity(new Intent(getApplicationContext(), SearchProduct.class));
				if (position == 4)
					startActivity(new Intent(getApplicationContext(), Settings.class));
				if (position == 5)
					startActivity(new Intent(getApplicationContext(), Help.class));
			}
		});
	}

}
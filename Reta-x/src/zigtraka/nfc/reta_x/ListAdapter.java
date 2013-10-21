package zigtraka.nfc.reta_x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {
	String[] Adminlist;
	Context context;

	public ListAdapter(Context context, int textViewResourceId,
			String[] Adminlist) {
		super(context, textViewResourceId, Adminlist);
		this.Adminlist = Adminlist;
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.custom_list_layout, parent, false);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		TextView textview = (TextView) rowView.findViewById(R.id.listlabel);
		LinearLayout linearlayout=(LinearLayout)rowView.findViewById(R.id.listcontainer);
		textview.setText(Adminlist[position]);

		// Change the icon for Windows and iPhone
		String s = Adminlist[position];
		if (s.equals("User Profile"))
			imageView.setImageResource(R.drawable.userprofile);
		else if (s.equals("NFC Settings"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Test DB"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Miscellaneous"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("About"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Support-Contact"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("App Updates"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Subscription"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Alerts")){
			imageView.setImageResource(R.drawable.settings);
			linearlayout.setBackground(null);
		}
		else if (s.equals("Items Sold"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Items Updated"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Items Missing"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Reminders")){
			imageView.setImageResource(R.drawable.settings);
			linearlayout.setBackground(null);
		}
		else if (s.equals("Daily"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Weekly"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Stock Take"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Get ProductInfo"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Stock Take"))
			imageView.setImageResource(R.drawable.settings);

		return rowView;
	}

}

package zigtraka.nfc.reta_x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlertsAndRemindersAdapter extends ArrayAdapter<String> {
	String[] Adminlist;
	Context context;

	public AlertsAndRemindersAdapter(Context context, int textViewResourceId,
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
		View rowView = inflater.inflate(R.layout.alerts_and_reminders_view, parent, false);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		TextView textview = (TextView) rowView.findViewById(R.id.listlabel);
		LinearLayout linearlayout=(LinearLayout)rowView.findViewById(R.id.listcontainer);
		textview.setText(Adminlist[position]);

		// Change the icon for Windows and iPhone
		String s = Adminlist[position];
	 if (s.equals("Alerts")){
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
		
		return rowView;
	}

}

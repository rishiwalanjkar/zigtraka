package zigtraka.nfc.reta_x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsAdapter extends ArrayAdapter<String> {
	String[] Adminlist;
	Context context;

	public SettingsAdapter(Context context, int textViewResourceId,
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
		View rowView = inflater.inflate(R.layout.settings_view, parent, false);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.settings_view_icon);
		TextView textview = (TextView) rowView.findViewById(R.id.settings_view_listlabel);
		LinearLayout linearlayout=(LinearLayout)rowView.findViewById(R.id.settings_view_listcontainer);
		textview.setText(Adminlist[position]);

		// Change the icon for Windows and iPhone
		String s = Adminlist[position];
		if (s.equals("User Profile"))
			imageView.setImageResource(R.drawable.profile);
		else if (s.equals("NFC Settings"))
			imageView.setImageResource(R.drawable.settings);
		else if (s.equals("Test DB"))
			imageView.setImageResource(R.drawable.testdb);
		else if (s.equals("Miscellaneous"))
			imageView.setImageResource(R.drawable.misc);
		
		return rowView;
	}

}

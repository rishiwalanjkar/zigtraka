package zigtraka.nfc.reta_x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelpAdapter extends ArrayAdapter<String> {
	String[] Adminlist;
	Context context;

	public HelpAdapter(Context context, int textViewResourceId,
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
		View rowView = inflater.inflate(R.layout.help_view, parent, false);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		TextView textview = (TextView) rowView.findViewById(R.id.listlabel);
		LinearLayout linearlayout=(LinearLayout)rowView.findViewById(R.id.listcontainer);
		textview.setText(Adminlist[position]);

		// Change the icon for Windows and iPhone
		String s = Adminlist[position];
		if (s.equals("About"))
			imageView.setImageResource(R.drawable.aboutus);
		else if (s.equals("Support-Contact"))
			imageView.setImageResource(R.drawable.contact);
		else if (s.equals("App Updates"))
			imageView.setImageResource(R.drawable.appupdate);
		else if (s.equals("Subscription"))
			imageView.setImageResource(R.drawable.subscription);
		
		return rowView;
	}

}

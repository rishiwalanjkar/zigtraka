package zigtraka.nfc.reta_x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NFCSettingsAdapter extends ArrayAdapter<String> {
	String[] Adminlist;
	Context context;

	public NFCSettingsAdapter(Context context, int textViewResourceId,
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
		View rowView = inflater.inflate(R.layout.nfcsettings_view, parent, false);
		TextView textview = (TextView) rowView.findViewById(R.id.nfcsettings_text);
		textview.setText(Adminlist[position]);

				return rowView;
	}

}

package zigtraka.nfc.reta_x;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NFCInteractionAdapter extends ArrayAdapter<String> {
	String[] Adminlist;
	Context context;

	public NFCInteractionAdapter(Context context, int textViewResourceId,
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
		View rowView = inflater.inflate(R.layout.nfc_interaction_view, parent, false);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.nfc_interaction_icon1);
		TextView textview = (TextView) rowView.findViewById(R.id.nfc_interaction_listlabel1);
		textview.setText(Adminlist[position]);

		// Change the icon for Windows and iPhone
		String s = Adminlist[position];
		if (s.equals("Get Product Info"))
			imageView.setImageResource(R.drawable.productinfo);
		else if (s.equals("Stock Take"))
			imageView.setImageResource(R.drawable.stocktake);

		return rowView;
	}

}

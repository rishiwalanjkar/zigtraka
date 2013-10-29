package zigtraka.nfc.reta_x;

import db.Access.DbForStockTakeActivity;
import db.handler.DbConnector;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StockTake extends BaseActivity {
	String TagID;
	String[][] techListsArray;
	PendingIntent pendingIntent;
	IntentFilter[] i = new IntentFilter[1];
	NfcAdapter NFCAdapter;
	TextView StockTakeCount;
    Button MatchInventory,Reset;
    int MissingItemCount,DatabaseItemCount,TappedItemCount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		// creating pending intent
		pendingIntent = PendingIntent.getActivity(this, 0, new Intent(
				getApplicationContext(), this.getClass())
				.addFlags( Intent.FLAG_ACTIVITY_NO_HISTORY), 0);

		i[0] = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
		// creating tech list for tag
		techListsArray = new String[][] { new String[] { NfcV.class.getName(),
				NfcA.class.getName(), NfcB.class.getName(),
				NfcF.class.getName(), Ndef.class.getName(),
				NdefFormatable.class.getName(), MifareClassic.class.getName(),
				MifareUltralight.class.getName() } };

		// creating object of nfc adapter of mobile device
		NFCAdapter = NfcAdapter.getDefaultAdapter(this);

		StockTakeCount = (TextView) findViewById(R.id.stocktake_count);

		if (StockCount.TagTapDetails.isEmpty())
			InitializeTagTapDetails();
		IncreamentCount(getIntent());
		
		//total items entry in database
		DatabaseItemCount=DbForStockTakeActivity.getItemCount();
		
		MatchInventory=(Button)findViewById(R.id.stock_take_matchinventory);
		MatchInventory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		       		TappedItemCount=Integer.parseInt(StockTakeCount.getText().toString());
		       		if(TappedItemCount==DatabaseItemCount)
		       		Toast.makeText(getApplicationContext(),"Count Matched With Database", Toast.LENGTH_SHORT).show();		       		
		       		else
		       			Toast.makeText(getApplicationContext(),(DatabaseItemCount-TappedItemCount)+" Itmes Missing", Toast.LENGTH_SHORT).show();
		       			
			}
		});
		
		Reset=(Button)findViewById(R.id.stock_take_resetbutton);
		Reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			StockTakeCount.setText("0");
			StockCount.TagTapDetails.clear();
			StockCount.setCount();
			}
		});
	}

	private void InitializeTagTapDetails() {
		// TODO Auto-generated method stub
		Cursor cursor = DbForStockTakeActivity.getTagIDs();
		if (cursor.moveToFirst())
			for (int i = 0; i < cursor.getCount(); i++) {
				StockCount.TagTapDetails.put(cursor.getString(0), 0);
				cursor.moveToNext();
			}
		DbConnector.close();
	}

	private void IncreamentCount(Intent intent) {
		// TODO Auto-generated method stub
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
			Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			TagID = ProductInformation.bin2hex(detectedTag.getId()).toString()
					.toLowerCase();

			if (TagID != null) {
				// value of corresponding key
				Integer value = StockCount.TagTapDetails.get(TagID);
				if (value != null) {
					if (value.equals(0)) {
						StockCount.Count++;
						StockCount.TagTapDetails.put(TagID, 1);
					}
					else
						Toast.makeText(getApplicationContext(),
								"Already Tapped This Tag", Toast.LENGTH_SHORT)
								.show();
					
				} else
					Toast.makeText(getApplicationContext(),
							"No Record for This Tag", Toast.LENGTH_SHORT)
							.show();
			}
		}
		StockTakeCount.setText(String.valueOf(StockCount.Count));
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (NFCAdapter != null)
			NFCAdapter.disableForegroundDispatch(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (NFCAdapter != null)
			NFCAdapter.enableForegroundDispatch(this, pendingIntent, i,
					techListsArray);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
	}

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.stock_take;
	}
	

}

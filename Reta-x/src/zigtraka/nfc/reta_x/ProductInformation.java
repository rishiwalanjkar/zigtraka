package zigtraka.nfc.reta_x;

import java.util.Locale;

import db.Access.DbForProductInformationActivity;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProductInformation extends BaseActivity implements
		TextToSpeech.OnInitListener {
	String TagID, TagContents;
	String[] TagDetails;
	Bundle bundle;
	TextView ProductCode, ProductModel, Gemstone, Price, Carat, Cut, Type,
			Wear, MakingCharges;
	TextView Description, Welcome;
	Button back;
	ImageButton voice;
	TextToSpeech textToSpeech_Obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		textToSpeech_Obj = new TextToSpeech(getApplicationContext(), this);
		bundle = getIntent().getExtras();
		if (bundle != null) {
			TagID = bundle.getString("TagID");
			TagContents = bundle.getString("TagContents");
		}
		if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(getIntent().getAction()))
			ScanTag(getIntent());

		
		// Welcome note.......
		Welcome = (TextView) findViewById(R.id.product_information_welcome);
		if (TagContents != null)
			Welcome.setText("Welcome To " + TagContents + " Store");
		TagDetails = DbForProductInformationActivity.getTagDetails(TagID);

		ProductCode = (TextView) findViewById(R.id.product_information_product_code);
		ProductModel = (TextView) findViewById(R.id.product_information_product_model);
		Gemstone = (TextView) findViewById(R.id.product_information_gemestone);
		Price = (TextView) findViewById(R.id.product_information_price);
		Carat = (TextView) findViewById(R.id.product_information_carat);
		Cut = (TextView) findViewById(R.id.product_information_cut);
		Type = (TextView) findViewById(R.id.product_information_type);
		Wear = (TextView) findViewById(R.id.product_information_wear);
		MakingCharges = (TextView) findViewById(R.id.product_information_making_Charges);
		Description = (TextView) findViewById(R.id.product_information_description);

		if (TagDetails != null) {
			ProductCode.setText(TagDetails[7]);
			ProductModel.setText(TagDetails[4]);
			Gemstone.setText(TagDetails[8]);
			Price.setText(TagDetails[2]);
			Carat.setText(TagDetails[13]);
			Cut.setText(TagDetails[10]);
			Type.setText(TagDetails[11]);
			Wear.setText(TagDetails[12]);
			MakingCharges.setText(TagDetails[5]);
			Description.setText(TagDetails[9]);
		}

		back = (Button) findViewById(R.id.product_information_backbutton);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method
				textToSpeech_Obj.stop();
				textToSpeech_Obj.shutdown();
				finish();
			}
		});

		voice = (ImageButton) findViewById(R.id.product_information_imageButton);
		voice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textToSpeech_Obj.speak(Description.getText().toString(),
						TextToSpeech.QUEUE_FLUSH, null);
			}
		});
	}

	public static String bin2hex(byte[] inarray) {
		// TODO Auto-generated method stub
		// parsing tagid to hex...
		int i, j, in;
		String[] hex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F" };
		String out = "";

		for (j = 0; j < inarray.length; ++j) {
			in = (int) inarray[j] & 0xff;
			i = (in >> 4) & 0x0f;
			out += hex[i];
			i = in & 0x0f;
			out += hex[i];
		}
		return out;
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {
			textToSpeech_Obj.setSpeechRate((float) 0.6);
			int result = textToSpeech_Obj.setLanguage(Locale.UK);
			textToSpeech_Obj.speak("Welcome To " + TagContents + " Store",
					TextToSpeech.QUEUE_FLUSH, null);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("error", "Language is not supported");
			}
		} else {
			Log.e("error", "Failed  to Initilize!");
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		textToSpeech_Obj.stop();
		textToSpeech_Obj.shutdown();
		finish();
	}

	public void ScanTag(Intent intent) {
		Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		TagID = ProductInformation.bin2hex(detectedTag.getId()).toString()
				.toLowerCase();

		TagContents = GetProductInfo.readdata(
				GetProductInfo.getNdefMessages(intent)).toString();

	}

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.product_information;
	}

}

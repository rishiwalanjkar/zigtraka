package zigtraka.nfc.reta_x;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.*;
import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;

public class GetProductInfo extends BaseActivity{
	String TagID;
	String[][] techListsArray;
	PendingIntent pendingIntent;
	IntentFilter[] i = new IntentFilter[1];
	NfcAdapter NFCAdapter;
	TextView message;
	TextToSpeech textToSpeech_Obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		// creating pending intent
		pendingIntent = PendingIntent.getActivity(this, 0, new Intent(
				getApplicationContext(), this.getClass())
				.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_NO_HISTORY), 0);

		i[0] = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
		// creating tech list for tag
		techListsArray = new String[][] { new String[] { NfcV.class.getName(),
				NfcA.class.getName(), NfcB.class.getName(),
				NfcF.class.getName(), Ndef.class.getName(),
				NdefFormatable.class.getName(), MifareClassic.class.getName(),
				MifareUltralight.class.getName() } };

		// creating object of nfc adapter of mobile device
		NFCAdapter = NfcAdapter.getDefaultAdapter(this);

		// if app is opened after pop up....
		ScanTag(getIntent());
		
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		// after detecting tag..
		ScanTag(intent);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
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

	public NdefMessage createNdefMessage() {
		Locale locale = new Locale("en", "US");
		byte[] langBytes = locale.getLanguage().getBytes(
				Charset.forName("US-ASCII"));
		boolean encodeInUtf8 = false;
		Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset
				.forName("UTF-16");
		int utfBit = encodeInUtf8 ? 0 : (1 << 7);
		char status = (char) (utfBit + langBytes.length);
		String RTD_TEXT = "Pranav Jagtap";
		byte[] textBytes = RTD_TEXT.getBytes(utfEncoding);
		byte[] data = new byte[1 + langBytes.length + textBytes.length];
		data[0] = (byte) status;
		System.arraycopy(langBytes, 0, data, 1, langBytes.length);
		System.arraycopy(textBytes, 0, data, 1 + langBytes.length,
				textBytes.length);
		NdefRecord textRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
				NdefRecord.RTD_TEXT, new byte[0], data);
		return new NdefMessage(new NdefRecord[] { textRecord });
	}

	public boolean writeNdefMessageToTag(NdefMessage message, Tag detectedTag) {
		int size = message.toByteArray().length;
		try {
			Ndef ndef = Ndef.get(detectedTag);
			if (ndef != null) {
				ndef.connect();
				if (!ndef.isWritable()) {
					Toast.makeText(this, "Tag is read-only.",
							Toast.LENGTH_SHORT).show();
					return false;
				}
				if (ndef.getMaxSize() < size) {
					Toast.makeText(
							this,
							"The data cannot written to tag,Tag capacity is "
									+ ndef.getMaxSize() + " bytes, message is "
									+ size + " bytes.", Toast.LENGTH_SHORT)
							.show();
					return false;
				}
				ndef.writeNdefMessage(message);
				ndef.close();
				Toast.makeText(this, "Message is written tag.",
						Toast.LENGTH_SHORT).show();
				return true;
			} else {
				NdefFormatable ndefFormat = NdefFormatable.get(detectedTag);
				if (ndefFormat != null) {
					try {
						ndefFormat.connect();
						ndefFormat.format(message);
						ndefFormat.close();
						Toast.makeText(this, "The data is written to the tag ",
								Toast.LENGTH_SHORT).show();
						return true;
					} catch (IOException e) {
						Toast.makeText(this, "Failed to format tag",
								Toast.LENGTH_SHORT).show();
						return false;
					}
				} else {
					Toast.makeText(this, "NDEF is not supported",
							Toast.LENGTH_SHORT).show();
					return false;
				}
			}
		} catch (Exception e) {
			Toast.makeText(this, "Write opreation is failed",
					Toast.LENGTH_SHORT).show();
		}
		return false;
	}

	public static NdefMessage[] getNdefMessages(Intent intent) {
		NdefMessage[] message = null;
		Parcelable[] rawMessages = intent
				.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		if (rawMessages != null) {
			message = new NdefMessage[rawMessages.length];
			for (int i = 0; i < rawMessages.length; i++) {
				message[i] = (NdefMessage) rawMessages[i];
			}
		} else {
			byte[] empty = new byte[] {};
			NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty,
					empty, empty);
			NdefMessage msg = new NdefMessage(new NdefRecord[] { record });
			message = new NdefMessage[] { msg };
		}
		return message;
	}

	public static StringBuilder readdata(NdefMessage[] messages) {
		String payload = new String();
		StringBuilder myText = new StringBuilder();
		if (messages != null)
			for (int i = 0; i < messages.length; i++) {
				// myText.append("Message " + (i + 1) + ":\n");
				for (int j = 0; j < messages[0].getRecords().length; j++) {
					NdefRecord record = messages[i].getRecords()[j];
					int statusByte = record.getPayload()[0];
					int languageCodeLength = statusByte & 0x3F;
					// myText.append("Language Code Length:" +
					// languageCodeLength+ "\n");
					// myText.append("Language Code:" + languageCode + "\n");
					int isUTF8 = statusByte - languageCodeLength;
					if (isUTF8 == 0x00) {
						// myText.append((j + 1) + "th. Record is UTF-8\n");
						payload = new String(record.getPayload(),
								1 + languageCodeLength,
								record.getPayload().length - 1
										- languageCodeLength,
								Charset.forName("UTF-8"));
					} else if (isUTF8 == -0x80) {
						// myText.append((j + 1) + "th. Record is UTF-16\n");
						payload = new String(record.getPayload(),
								1 + languageCodeLength,
								record.getPayload().length - 1
										- languageCodeLength,
								Charset.forName("UTF-16"));
					}
					// myText.append((j + 1) + "th. Record Tnf: " +
					// record.getTnf()+ "\n");
					// myText.append((j + 1) + "th. Record type: "+ new
					// String(record.getType()) + "\n");
					// myText.append((j + 1) + "th. Record id: "+ new
					// String(record.getId()) + "\n");
					// myText.append((j + 1) + "th. Record payload: " + payload
					// + "\n");
					myText.append(payload + "\n");
				}
			}
		return myText;
	}

	public void ScanTag(Intent intent) {
		Bundle b = new Bundle();
		String TagContents;
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
			Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			TagID = ProductInformation.bin2hex(detectedTag.getId()).toString()
					.toLowerCase();

			// MyDatabaseHelper mydatabasehelper=new MyDatabaseHelper(this);
			// String details=mydatabasehelper.getModString(TagID);
			//
			// // passing tagid to display to second activity
			// b.putString("ID", TagID);
			// b.putString("Text", mydatabasehelper.getModString(TagID));
			//
			if (TagID != null) {
				b.putString("TagID", TagID);
				TagContents = readdata(getNdefMessages(intent)).toString();
				if (TagContents != null) {
					b.putString("TagContents", TagContents);
					startActivity(new Intent(getApplicationContext(),
							ProductInformation.class).putExtras(b));
					
				}
			}
		}

	}

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.get_product_info;
	}

	
}

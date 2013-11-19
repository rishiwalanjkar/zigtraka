package zigtraka.nfc.reta_x;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import subscription.handler.GMailSender;
import subscription.handler.GoogleMail;
import subscription.handler.KeyGenerator;

public class Subscription extends BaseActivity {

	private TextView Activate, DaysRemain, Green;
	private EditText EnterKey, Name, Email;
	private Spinner Spinner1;
	private Button register, activate;
	private String productKey;
	private String senderId;
	private String senderPassword;
	private String recepientId;
	private String subject;
	private String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Activate = (TextView) findViewById(R.id.Subscription_ActivetextView);
		DaysRemain = (TextView) findViewById(R.id.Subscription_remaintextView);
		EnterKey = (EditText) findViewById(R.id.Subscription_enterkeyedit);
		Spinner1 = (Spinner) findViewById(R.id.Subscription_spinner1);
		register = (Button) findViewById(R.id.Subscription_button_register);
		activate = (Button) findViewById(R.id.Subscription_button_activate);
		Name = (EditText) findViewById(R.id.Subscription_nameedit);
		Email = (EditText) findViewById(R.id.Subscription_emailedit);
		Green = (TextView) findViewById(R.id.Subscription_Greentext);

		
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				t.start();
			}
		});
		activate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String returnedKey = PreferenceManager
						.getDefaultSharedPreferences(getApplicationContext())
						.getString("Product Key", "defaultStringIfNothingFound");
//				EnterKey.setText("wtCvqrcvauoRxPaNsvhyqpIrKrrHyFFt");
				if (EnterKey.getText().toString().equals(returnedKey))
					Toast.makeText(getApplicationContext(),
							"Activated successfully !", Toast.LENGTH_LONG)
							.show();
				else
					Toast.makeText(getApplicationContext(),
							returnedKey+"Wrong Key Unable to Activate !", Toast.LENGTH_LONG)
							.show();
			}
		});
	}

	Thread t = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				senderId = "apptest@zigtraka.com";
				senderPassword = "zigAppTest123";
				recepientId = "apptest@zigtraka.com";
				subject = "Product Key";
				productKey = KeyGenerator.getKey(getApplicationContext(), "Titan", "1");
				message = "Name        : "+Name.getText().toString()+"\n"+
				          "Email       : "+Email.getText().toString()+"\n"+
			              "Product Key : "+productKey;

				GoogleMail.Send(senderId, senderPassword, recepientId, subject,
						message);
				PreferenceManager
						.getDefaultSharedPreferences(getApplicationContext())
						.edit().putString("Product Key", productKey).commit();
			} catch (Exception e) {
				// Log.e("SendMail", e.getMessage(), e);
				e.printStackTrace();
			}
		}
	});

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.subscription;
	}

}

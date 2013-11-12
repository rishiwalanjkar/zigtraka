package zigtraka.nfc.reta_x;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Subscription extends BaseActivity {

	private TextView Activate,DaysRemain,Green;
	private EditText EnterKey,Name,Email;
	private Spinner Spinner1;
	private Button Renew;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Activate=(TextView)findViewById(R.id.Subscription_ActivetextView);
		DaysRemain=(TextView)findViewById(R.id.Subscription_remaintextView);
		EnterKey=(EditText)findViewById(R.id.Subscription_enterkeyedit);
		Spinner1=(Spinner) findViewById(R.id.Subscription_spinner1);
		Renew=(Button)findViewById(R.id.Subscription_button);
		Name=(EditText)findViewById(R.id.Subscription_nameedit);
		Email=(EditText)findViewById(R.id.Subscription_emailedit);
		Green=(TextView)findViewById(R.id.Subscription_Greentext);
		
		
	}

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.subscription;
	}

	

}

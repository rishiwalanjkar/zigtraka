package zigtraka.nfc.reta_x;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerSurveyForm extends Activity {

	private EditText NameEdit, AreaEdit, AddressEdit, EmailEdit, MobileEdit;
	private MyDatabaseHelper mydatabasehelper;
	private TextView Welcome;
	private Button save;
	private Button reset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_survey_form);
		Welcome = (TextView) findViewById(R.id.welcomeusertextView2);
		String fontpath = "fonts/Roboto-Bold.ttf";
		Typeface tf = Typeface.createFromAsset(getAssets(),fontpath);
		Welcome.setTypeface(tf);
		
		mydatabasehelper = new MyDatabaseHelper(getApplicationContext());
		NameEdit = (EditText) findViewById(R.id.customer_survey_form_customer_nameedit);
		AreaEdit = (EditText) findViewById(R.id.customer_survey_form_customer_areaedit);
		AddressEdit = (EditText) findViewById(R.id.customer_survey_form_customer_addressedit);
		EmailEdit = (EditText) findViewById(R.id.customer_survey_form_customer_emailedit);
		MobileEdit = (EditText) findViewById(R.id.customer_survey_form_customer_mobileedit);
        save=(Button)findViewById(R.id.customer_survey_save);
        reset=(Button)findViewById(R.id.customer_survey_reset);
        save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MyDatabaseHelper(getApplicationContext()).addCustomerDetails(NameEdit.getText().toString(), AreaEdit.getText().toString(), AddressEdit.getText().toString(), EmailEdit.getText().toString(), MobileEdit.getText().toString());
			}
		});
	}

	
}

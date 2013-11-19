package zigtraka.nfc.reta_x;

import db.Access.DbForCustomerSurveyFormActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerSurveyForm extends BaseActivity {

	private EditText NameEdit, AreaEdit, AddressEdit, EmailEdit, MobileEdit;
	private TextView Welcome;
	private Button save;
	private Button reset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
				DbForCustomerSurveyFormActivity.addCustomerDetails(NameEdit.getText().toString(), AreaEdit.getText().toString(), AddressEdit.getText().toString(), EmailEdit.getText().toString(), MobileEdit.getText().toString());
			}
		});
	}

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.customer_survey_form;
	}

	
}

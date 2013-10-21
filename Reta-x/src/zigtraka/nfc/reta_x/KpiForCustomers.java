package zigtraka.nfc.reta_x;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

public class KpiForCustomers extends Activity {
private TextView NewCustomers,AverageTimeSpent,IncreaseInNewCustomers;
private ListView TopMostCustomers;
private MyDatabaseHelper mydatabasehelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kpi_for_customers);
		
		mydatabasehelper=new MyDatabaseHelper(getApplicationContext());
		
		NewCustomers=(TextView)findViewById(R.id.kpi_for_customers_new_customerstextView);
		AverageTimeSpent=(TextView)findViewById(R.id.kpi_for_customers_Average_time_spentextView);
		IncreaseInNewCustomers=(TextView)findViewById(R.id.kpi_for_customers_increase_in_new_customerstextView);
		
		NewCustomers.setText(String.valueOf(mydatabasehelper.getnewcustomer()));
//		AverageTimeSpent.setText(String.valueOf(mydatabasehelper));
		IncreaseInNewCustomers.setText(String.valueOf(mydatabasehelper.getIncreaseInCustomer()));
		
		TopMostCustomers=(ListView)findViewById(R.id.kpi_for_customers_top_most_customerslistView);
//		TopMostCustomers.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,mydatabasehelper.));
	}


}

package zigtraka.nfc.reta_x;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;

import chart.specification.PieChartSpecification;
import db.Access.dbForCustomerAnalysisActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.LinearLayout;

public class CustomerAnalysis extends BaseActivity {

	private LinearLayout Graph;
	private GraphicalView g;
	private int[] values;
	private String[] NAME_LIST;
	private TreeMap<String, Integer> AreaAnalysis;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Graph = (LinearLayout) findViewById(R.id.customer_analysis);
		
		AreaAnalysis = dbForCustomerAnalysisActivity.getAreaWiseCustomerStats();

		values = new int[AreaAnalysis.size()];
		NAME_LIST = new String[AreaAnalysis.size()];
		int i = 0;
		for (Entry<String, Integer> t : AreaAnalysis.entrySet()) {
			values[i] = t.getValue();
			NAME_LIST[i] = t.getKey();
			i++;
		}

		g = ChartFactory.getPieChartView(getApplicationContext(),
				PieChartSpecification.getPieSeries(values, NAME_LIST),
				PieChartSpecification.getPieRenderer("Area Wise Analysis",values.length));
		Graph.addView(g);

	}

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.customer_analysis;
	}

	
}

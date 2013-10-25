package zigtraka.nfc.reta_x;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;

import chart.specification.BarChartSpecification;
import chart.specification.PieChartSpecification;
import db.Access.DbForChartsAndGraphsActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

public class Chartsandgraphs extends BaseActivity {
	private GraphicalView g;
	private int[] values;
	private int yAxisEndLimit;
	private String BarDetails, ChartTitle;
	private String[] xAxisLables, xyAxisTitles,NAME_LIST;
	private LinearLayout Graph1, Graph2;
	private Spinner spinner;
	private RadioButton BarChart, PieChart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		// Specifications for 1st Graph
		values = DbForChartsAndGraphsActivity.getHourStats(0);
		BarDetails = "TapCount";
		ChartTitle = "Frequency Tap Counts";
		yAxisEndLimit = getMaxValueArray();
		xAxisLables = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		NAME_LIST = new String[] { "1st Hr.", "2nd Hr.", "3rd Hr.", "4th Hr.", "5th Hr.", "6th Hr."
				, "7th Hr.", "8th Hr.", "9th Hr.", "10th Hr.", "11th Hr.", "12th Hr." };
		xyAxisTitles = new String[] { "Hours", "Count" };

		// getting graphicalview by calling local method
		g = ChartFactory.getBarChartView(getApplicationContext(), BarChartSpecification
				.getBarDataset(values, BarDetails), BarChartSpecification
				.getBarRenderer(ChartTitle, yAxisEndLimit, xAxisLables,
						xyAxisTitles), Type.DEFAULT);

		Graph1 = (LinearLayout) findViewById(R.id.charts_and_graph_graph1_linearlayout);
		Graph1.addView(g);

		radioButtonEvent();
		spinnerEvent();

	}

	private void radioButtonEvent() {
		// TODO Auto-generated method stub
		BarChart = (RadioButton) findViewById(R.id.charts_and_graphs_barchart_radiobutton);
		PieChart = (RadioButton) findViewById(R.id.charts_and_graphs_piechart_radiobutton);

		BarChart.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Graph1.removeAllViews();
					g = ChartFactory.getBarChartView(getApplicationContext(),
							BarChartSpecification.getBarDataset(values,
									BarDetails), BarChartSpecification
									.getBarRenderer(ChartTitle,
											yAxisEndLimit, xAxisLables,
											xyAxisTitles), Type.DEFAULT);
					Graph1.addView(g);
				}
			}
		});
		PieChart.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Graph1.removeAllViews();
					g = ChartFactory.getPieChartView(getApplicationContext(),
							PieChartSpecification.getPieSeries(values,
									NAME_LIST), PieChartSpecification
									.getPieRenderer(ChartTitle,values.length));
					Graph1.addView(g);
				}
			}
		});

	}

	private void spinnerEvent() {
		// TODO Auto-generated method stub
		spinner = (Spinner) findViewById(R.id.charts_and_graph_spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_spinner_item,
				new String[] { "day", "weak", "month" });
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				// TODO Auto-generated method stub
				if (BarChart.isChecked()) {
					values = DbForChartsAndGraphsActivity.getHourStats(position);
					BarDetails = "TapCount";
					yAxisEndLimit = getMaxValueArray();
					xyAxisTitles = new String[] { "Hours", "Count" };

					Graph1.removeAllViews();
					g = ChartFactory.getBarChartView(getApplicationContext(),
							BarChartSpecification.getBarDataset(values,
									BarDetails), BarChartSpecification
									.getBarRenderer(ChartTitle,
											yAxisEndLimit, xAxisLables,
											xyAxisTitles), Type.DEFAULT);
					Graph1.addView(g);
				}

				if (PieChart.isChecked()) {
					values = DbForChartsAndGraphsActivity.getHourStats(position);
					yAxisEndLimit = getMaxValueArray();
					Graph1.removeAllViews();
					g = ChartFactory.getPieChartView(getApplicationContext(),
							PieChartSpecification.getPieSeries(values,
									NAME_LIST), PieChartSpecification
									.getPieRenderer(ChartTitle,values.length));
					Graph1.addView(g);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	public int getMaxValueArray() {
		int max = values[0];
		for (int i = 1; i < values.length; i++)
			if (values[i] > max)
				max = values[i];
		return max;
	}

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.chartsandgraphs;
	}
}

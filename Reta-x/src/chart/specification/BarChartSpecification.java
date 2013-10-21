package chart.specification;

import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.graphics.Color;
import android.graphics.Paint.Align;

public class BarChartSpecification {
	
	public static XYMultipleSeriesDataset getBarDataset(int[] values,
			String BarDetails) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		CategorySeries series = new CategorySeries(BarDetails);
		for (int i = 0; i < values.length; i++) {
			series.add(values[i]);
		}
		dataset.addSeries(series.toXYSeries());
		return dataset;
	}

	public static XYMultipleSeriesRenderer getBarRenderer(String ChartTitle,
			int yAxisEndLimit, String[] xAxisLables, String[] xyAxisTitles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setMargins(new int[] { 30, 40, 15, 10 });
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(Color.parseColor("#DC143C"));
		renderer.addSeriesRenderer(r);
		myChartSettings(renderer, ChartTitle, yAxisEndLimit, xAxisLables,
				xyAxisTitles);
		return renderer;
	}

	private static void myChartSettings(XYMultipleSeriesRenderer renderer,
			String ChartTitle, int yAxisEndLimit, String[] xaxislables,
			String[] xyAxisTitles) {
		renderer.setChartTitle(ChartTitle);
		renderer.setXAxisMin(0.5);
		renderer.setXAxisMax(8.5);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(yAxisEndLimit+10);
		// creating x-axis labels
		for (int i = 0; i < xaxislables.length; i++)
			renderer.addXTextLabel(i + 1, xaxislables[i]);

		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setBarSpacing(0.5);
		renderer.setXTitle(xyAxisTitles[0]);
		renderer.setYTitle(xyAxisTitles[1]);
		renderer.setShowGrid(true);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.BLACK);
		renderer.setGridColor(Color.GRAY);
		renderer.setXLabels(0); // sets the number of integer labels to appear
	}

}

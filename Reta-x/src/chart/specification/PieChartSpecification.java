package chart.specification;

import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import android.graphics.Color;
import android.graphics.Typeface;

public class PieChartSpecification {
	private static int[] color=new int[]{Color.parseColor("#00FFFF"),Color.parseColor("#FFFF00")
		,Color.parseColor("#7FFF00"),Color.parseColor("#6495ED")
		,Color.parseColor("#DC143C"),Color.parseColor("#008B8B")
		,Color.parseColor("#B8860B"),Color.parseColor("#006400")
		,Color.parseColor("#9932CC"),Color.parseColor("#FF00FF")
		,Color.parseColor("#FFA500"),Color.parseColor("#8FBC8F")};
	
	public static CategorySeries getPieSeries(int[] values, String[] NAME_LIST) {
		CategorySeries series = new CategorySeries("");
		for (int i = 0; i < values.length; i++) {
			series.add(NAME_LIST[i] + " " + values[i], values[i]);
		}
		return series;
	}

	public static DefaultRenderer getPieRenderer(String ChartTitle,int length) {
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setChartTitleTextSize(35);
		renderer.setLabelsTextSize(25);
		renderer.setLegendTextSize(25);
		renderer.setMargins(new int[] { 40, 40, 40, 40 });
        renderer.setTextTypeface(Typeface.DEFAULT_BOLD);
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color[i]);
			renderer.addSeriesRenderer(r);
		}
		myChartSettings(renderer, ChartTitle);
		return renderer;
	}

	private static void myChartSettings(DefaultRenderer renderer, String ChartTitle) {
		renderer.setChartTitle(ChartTitle);
		renderer.setShowGrid(true);
		renderer.setAxesColor(Color.BLACK);
		renderer.setApplyBackgroundColor(true);
		renderer.setBackgroundColor(Color.BLACK);
		renderer.setStartAngle(-90);
	}

}

package db.Access;

import db.handler.DbConnector;
import mod.database.ModStatsInterface;
import android.database.Cursor;

public class DbForChartsAndGraphsActivity {
	

	/**
	 *Function to get number of customers for each hour in 12hrs 
	 */
	public static int[] getHourStats(int SpinnerSelectedItem) {
		Cursor cursor = DbConnector.open().rawQuery("SELECT " + "sum("
				+ ModStatsInterface.T1[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T2[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T3[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T4[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T5[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T6[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T7[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T8[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T9[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T10[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T11[SpinnerSelectedItem] + ")," + "sum("
				+ ModStatsInterface.T12[SpinnerSelectedItem] + ") FROM "
				+ ModStatsInterface.TABLE_NAME, null);

		int[] values = null;
		if (cursor.moveToFirst()) {
			values = new int[cursor.getColumnCount()];
			for (int i = 0; i < cursor.getColumnCount(); i++)
				values[i] = cursor.getInt(i);
		}
		DbConnector.close();
		return values;

	}

}

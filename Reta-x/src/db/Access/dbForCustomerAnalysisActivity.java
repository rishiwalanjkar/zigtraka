package db.Access;

import java.util.TreeMap;

import db.handler.DbConnector;

import mod.database.CustMainEnum;
import android.database.Cursor;

public class dbForCustomerAnalysisActivity {
	
	/**
	 * Function to get areawise customer count
	 */
	public static TreeMap<String, Integer> getAreaWiseCustomerStats() {
		TreeMap<String, Integer> areaAnalysis = new TreeMap<String, Integer>();

		Cursor cursor = DbConnector.open().rawQuery(
				"SELECT " + CustMainEnum.CustMain.Area
						+ ", COUNT(*) AS `num`FROM "
						+ CustMainEnum.CustMain.TableName + " GROUP BY "
						+ CustMainEnum.CustMain.Area, null);

		if (cursor.moveToFirst()) {
			do {
				areaAnalysis.put(cursor.getString(0), cursor.getInt(1));
			} while (cursor.moveToNext());
		}
		DbConnector.close();
		return areaAnalysis;
	}

}

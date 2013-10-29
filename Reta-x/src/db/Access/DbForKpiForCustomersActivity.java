package db.Access;

import db.handler.DbConnector;
import android.database.Cursor;

public class DbForKpiForCustomersActivity {

	/**
	 * Fuction to get number of new customers
	 */
	public static int getnewcustomer() {
		Cursor cursor = DbConnector.open().rawQuery(
				"SELECT ncust_count_day FROM cust_stats", null);

		cursor.moveToFirst();
		DbConnector.close();
		return cursor.getInt(0);

	}

	/**
	 * Function to get number of increase in customers
	 */
	public static int getIncreaseInCustomer() {
		Cursor cursor = DbConnector
				.open()
				.rawQuery(
						"select(select sum(ncust_count_day) from cust_stats) - (select sum(ncust_count_pday) from cust_stats)",
						null);
		cursor.moveToFirst();
		DbConnector.close();
		return cursor.getInt(0);
	}

}

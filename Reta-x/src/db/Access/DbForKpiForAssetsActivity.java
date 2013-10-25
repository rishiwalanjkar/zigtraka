package db.Access;

import db.handler.DbConnector;
import android.database.Cursor;

public class DbForKpiForAssetsActivity {

	/**
	 * Function to get daytapcount
	 */
	public static int dashboard() {
		Cursor cursor = DbConnector.open().rawQuery(
				"SELECT count(*) FROM mod_stats WHERE mod_day_tapcount ='0'",
				null);
		cursor.moveToFirst();
		DbConnector.close();
		return cursor.getInt(0);
	}

	/**
	 * Fuction to get total taps
	 */
	public static int getTotalTaps() {
		Cursor cursor = DbConnector.open().rawQuery(
				"SELECT sum(mod_day_tapcount) FROM mod_stats", null);
		cursor.moveToFirst();
		DbConnector.close();
		return cursor.getInt(0);
	}

	/**
	 * Fucntion to get Increase in tap
	 */
	public static int getIncreaseInTap() {
		Cursor cursor = DbConnector
				.open()
				.rawQuery(
						"select(select sum(mod_day_tapcount) from mod_stats) - (select sum(mod_pday_tapcount) from mod_stats)",
						null);
		cursor.moveToFirst();
		DbConnector.close();
		return cursor.getInt(0);

	}

	/**
	 * Function to get least tapped items list
	 */
	public static String[] getLeastTapItems() {
		String[] a = new String[3];
		Cursor cursor = DbConnector
				.open()
				.rawQuery(
						" SELECT mod_model from mod_main INNER JOIN mod_stats on(mod_main.mod_tagid=mod_stats.mod_id) where mod_tagid in(SELECT mod_id FROM mod_stats) ORDER BY mod_stats.mod_day_tapcount asc",
						null);

		cursor.moveToFirst();
		a[0] = cursor.getString(0);
		cursor.moveToNext();
		a[1] = cursor.getString(0);
		cursor.moveToNext();
		a[2] = cursor.getString(0);
		DbConnector.close();
		return a;

	}

	/**
	 * Functio to get most tapped items list
	 */
	public static String[] getMostTapItems() {
		String[] a = new String[3];
		Cursor cursor = DbConnector
				.open()
				.rawQuery(
						" SELECT mod_model from mod_main INNER JOIN mod_stats on(mod_main.mod_tagid=mod_stats.mod_id) where mod_tagid in(SELECT mod_id FROM mod_stats) ORDER BY mod_stats.mod_day_tapcount desc",
						null);

		cursor.moveToFirst();
		a[0] = cursor.getString(0);
		cursor.moveToNext();
		a[1] = cursor.getString(0);
		cursor.moveToNext();
		a[2] = cursor.getString(0);

		DbConnector.close();
		return a;

	}
}

package db.Access;

import db.handler.DbConnector;
import android.database.Cursor;

public class DbForStockTakeActivity {

	/**
	 * Function to get number of tag entries in database
	 */
	public static int getItemCount() {
		Cursor cursor = DbConnector.open().rawQuery(
				"SELECT count(*) FROM mod_main", null);
		cursor.moveToFirst();
		DbConnector.close();
		return cursor.getInt(0);
	}

	/**
	 * Funtion to get tagIds from database
	 */
	public static Cursor getTagIDs() {
		Cursor cursor = DbConnector.open().rawQuery(
				"SELECT mod_tagid FROM mod_main", null);
		return cursor;
	}

}

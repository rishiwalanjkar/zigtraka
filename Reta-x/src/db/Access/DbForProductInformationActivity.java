package db.Access;

import db.handler.DbConnector;
import android.database.Cursor;

public class DbForProductInformationActivity {
	
	
    /**
	 *Function to get details of specific tag using tagid 
	 */
	public static String[] getTagDetails(String TagID) {
		String[] details = null;
		Cursor cursor = DbConnector.open().rawQuery("SELECT * FROM mod_main where mod_tagid ='" + TagID + "'",
				null);
		if (cursor.moveToFirst()) {
			details = new String[cursor.getColumnCount()];
			for (int i = 0; i < cursor.getColumnCount(); i++) {
				if (i == 1)
					details[i] = String.valueOf(cursor.getFloat(i));
				else
					details[i] = cursor.getString(i);
				System.out.println(cursor.getString(i) + "\n");
			}
		}
		DbConnector.close();
		return details;
	}

}

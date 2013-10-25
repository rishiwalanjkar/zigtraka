package db.Access;

import db.handler.DbConnector;
import mod.database.CustMainEnum;
import android.content.ContentValues;

public class DbForCustomerSurveyFormActivity {

	/**
	 * Function to add new customer Entry in Database
	 */
	public static void addCustomerDetails(String Name, String Area,
			String Address, String Email, String MobileNo) {
		ContentValues values = new ContentValues();
		values.put(CustMainEnum.CustMain.Name, Name);
		values.put(CustMainEnum.CustMain.Area, Area);
		values.put(CustMainEnum.CustMain.Address, Address);
		values.put(CustMainEnum.CustMain.Email, Email);
		values.put(CustMainEnum.CustMain.Mobile, MobileNo);

		DbConnector.open()
				.insert(CustMainEnum.CustMain.TableName, null, values);
		DbConnector.close();
		// Toast.makeText(applicationContext, "Record Added Successfully!",
		// Toast.LENGTH_SHORT).show();
	}

}

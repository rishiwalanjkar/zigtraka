package zigtraka.nfc.reta_x;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import mod.database.ModStatsInterface;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	private String DB_PATH = "data/data/zigtraka.nfc.reta_x/databases/";
	private static String DB_NAME = "mod.sqlite";
	private SQLiteDatabase applicationDatabase;
	private final Context applicationContext;
	private static final int DATABASE_VERSION = 3;
	private final String TABLE_NAME = "mod_main";
	private final String TagID = "mod_tagid";
	private final String Price = "mod_price";
	private final String Carat = "mod_carat";
	private final String Cut = "mod_cut";
	private final String Origin = "mod_origin";
	private final String MakingCharges = "mod_makingcharges";
	private String TABLE_NAME1 = "mod_stats";
	private String DayTapCount = "mod_day_tapcount";
	private String[] a1;

	public MyDatabaseHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		applicationContext = context;
		boolean dbexist = checkdatabase();
		if (dbexist) {
		} else {
			System.out.println("Database doesn't exist");
			try {
				createdatabase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// TODO Auto-generated constructor stub
	}

	public void createdatabase() throws IOException {
		boolean dbexist = checkdatabase();
		if (dbexist) {
		} else {
			this.getReadableDatabase();
			try {
				copydatabase();
			} catch (IOException e) {
				e.printStackTrace();
				throw new Error("Error copying database");
			}
		}
	}

	private boolean checkdatabase() {
		boolean checkdb = false;
		try {
			String myPath = DB_PATH + DB_NAME;
			File dbfile = new File(myPath);
			checkdb = dbfile.exists();
		} catch (SQLiteException e) {
			System.out.println("Database doesn't exist");
		}

		return checkdb;
	}

	private void copydatabase() throws IOException {

		// Open your local db as the input stream
		InputStream myinput = applicationContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outfilename = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myoutput = new FileOutputStream(outfilename);

		// transfer byte to inputfile to outputfile
		byte[] buffer = new byte[512];
		int length;
		while ((length = myinput.read(buffer)) > 0) {
			myoutput.write(buffer, 0, length);
		}

		// Close the streams
		myoutput.flush();
		myoutput.close();
		myinput.close();
		System.out.println("Coppying db successfull..........");
	}

	public SQLiteDatabase open() {
		// Open the database
		String mypath = DB_PATH + DB_NAME;
		applicationDatabase = SQLiteDatabase.openDatabase(mypath, null,
				SQLiteDatabase.OPEN_READWRITE);
		return applicationDatabase;
	}

	public synchronized void close() {
		applicationDatabase.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
		// + TagID + " TEXT," + Price + " FLOAT," + Carat
		// + " TEXT," + Cut + " TEXT," + Origin + " TEXT,"
		// + MakingCharges + " TEXT" + ")";
		// db.execSQL(CREATE_CONTACTS_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	}

	public void addRow(String TagID, float Price, String Carat, String Cut,
			String MakingCharges) {

		ContentValues values = new ContentValues();
		values.put(this.TagID, TagID);
		values.put(this.Price, Price);
		values.put(this.Carat, Carat);
		values.put(this.Cut, Cut);
		values.put(this.Origin, Origin);
		values.put(this.MakingCharges, MakingCharges);

		applicationDatabase.insert(TABLE_NAME, null, values);
		applicationDatabase.close();
	}

	public String[] getTagDetails(String TagID) {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		// if(applicationDatabase!=null)
		String[] details = null;
		Cursor cursor = applicationDatabase.rawQuery("SELECT * FROM "
				+ TABLE_NAME + " where " + this.TagID + "='" + TagID + "'",
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
		return details;
	}

	public void deleteRowByTagID(String TagID) {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		applicationDatabase.delete(TABLE_NAME, this.TagID + " = '" + TagID
				+ "'", null);
		applicationDatabase.close();
	}

	public void updateRowByTagID(String TagID, String ProductCode,
			String ProductModel, String Gemstone, String Price, String Carat,
			String Cut, String Type, String Wear, String MakingCharges) {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		ContentValues values = new ContentValues();
		values.put("mod_id", ProductCode);
		values.put("mod_model", ProductModel);
		values.put("mod_gemstone", Gemstone);
		values.put(this.Price, Float.parseFloat(Price));
		values.put(this.Carat, Carat);
		values.put(this.Cut, Cut);
		values.put("mod_comments", Type);
		values.put("mod_category", Wear);
		values.put(this.MakingCharges, MakingCharges);
		applicationDatabase.update(TABLE_NAME, values, this.TagID + " = '"
				+ TagID + "'", null);
		applicationDatabase.close();
		
		try {
			a1 = applicationContext.getAssets().list("file:///");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i = a1.length;
		if (i != 0)
			for (String a : a1)
				Toast.makeText(applicationContext, a, Toast.LENGTH_LONG).show();
	}

	public Cursor searchViewResult(String keyword) {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		return applicationDatabase.rawQuery("SELECT * " + "FROM [mod_main]"
				+ " WHERE (substr([mod_id],1,4096) LIKE '%"
				+ keyword
				+ "%')"
				+ " OR (substr([mod_tagid],1,4096) LIKE '%"
				+ keyword
				+ "%')"
				+ " OR (substr([mod_category],1,4096) LIKE '%"
				+ keyword
				+ "%')"
				+ " OR (substr([mod_origin],1,4096) LIKE '%"
				+ keyword
				+ "%')"
				+ " OR (substr([mod_gemstone],1,4096) LIKE '%"
				+ keyword
				+ "%')"
				+ " OR (substr([mod_carat],1,4096) LIKE '%"
				+ keyword
				+ "%')"
				+ " OR (substr([mod_description],1,4096) LIKE '%"
				+ keyword
				+ "%')"
				+ " OR (substr([mod_model],1,4096) LIKE '%"
				+ keyword
				+ "%')"
				+ " OR (substr([mod_comments],1,4096) LIKE '%"
				+ keyword + "%')", null);

	}

	public Cursor getTagIDs() {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		// if(applicationDatabase!=null)
		return applicationDatabase.rawQuery("SELECT " + this.TagID + " FROM "
				+ TABLE_NAME, null);

	}

	public int dashboard() {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = applicationDatabase.rawQuery("SELECT count(*) FROM "
				+ TABLE_NAME1 + " WHERE " + DayTapCount + " ='0'", null);
		cursor.moveToFirst();
		return cursor.getInt(0);
	}

	public int getTotalTaps() {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = applicationDatabase.rawQuery("SELECT sum("
				+ DayTapCount + ") FROM " + TABLE_NAME1, null);
		cursor.moveToFirst();
		return cursor.getInt(0);
	}

	public int getItemCount() {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = applicationDatabase.rawQuery("SELECT count(*) FROM "
				+ TABLE_NAME, null);
		cursor.moveToFirst();
		return cursor.getInt(0);
	}

	public int[] getHourStats(int SpinnerSelectedItem) {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = applicationDatabase.rawQuery("SELECT " + "sum("
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
		return values;

	}

	public int getIncreaseInTap() {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = applicationDatabase
				.rawQuery(
						"select(select sum(mod_day_tapcount) from mod_stats) - (select sum(mod_pday_tapcount) from mod_stats)",
						null);
		cursor.moveToFirst();
		return cursor.getInt(0);

	}

	public String[] getLeastTapItems() {
		String[] a = new String[3];
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = applicationDatabase
				.rawQuery(
						" SELECT mod_model from mod_main INNER JOIN mod_stats on(mod_main.mod_tagid=mod_stats.mod_id) where mod_tagid in(SELECT mod_id FROM mod_stats) ORDER BY mod_stats.mod_day_tapcount asc",
						null);

		cursor.moveToFirst();
		a[0] = cursor.getString(0);
		cursor.moveToNext();
		a[1] = cursor.getString(0);
		cursor.moveToNext();
		a[2] = cursor.getString(0);

		return a;

	}

	public String[] getMostTapItems() {
		String[] a = new String[3];
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = applicationDatabase
				.rawQuery(
						" SELECT mod_model from mod_main INNER JOIN mod_stats on(mod_main.mod_tagid=mod_stats.mod_id) where mod_tagid in(SELECT mod_id FROM mod_stats) ORDER BY mod_stats.mod_day_tapcount desc",
						null);

		cursor.moveToFirst();
		a[0] = cursor.getString(0);
		cursor.moveToNext();
		a[1] = cursor.getString(0);
		cursor.moveToNext();
		a[2] = cursor.getString(0);

		return a;

	}

	public int getnewcustomer() {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = applicationDatabase.rawQuery(
				"SELECT ncust_count_day FROM cust_stats", null);

		cursor.moveToFirst();

		return cursor.getInt(0);

	}

	public int getIncreaseInCustomer() {
		applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
				null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor = applicationDatabase
				.rawQuery(
						"select(select sum(ncust_count_day) from cust_stats) - (select sum(ncust_count_pday) from cust_stats)",
						null);
		cursor.moveToFirst();
		return cursor.getInt(0);
	}

}

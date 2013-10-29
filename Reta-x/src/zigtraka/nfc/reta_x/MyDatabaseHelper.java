package zigtraka.nfc.reta_x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import deploy.appdata.directory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	private static String DB_PATH = directory.databaseFolderPath+"/";
	private static String DB_NAME = "mod.sqlite";
	private static final int DATABASE_VERSION = 3;
	private static InputStream Source;
	private static OutputStream Destination;
	private SQLiteDatabase applicationDatabase;
	private Context applicationContext;
	public MyDatabaseHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		applicationContext=context;

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
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}
	
	public SQLiteOpenHelper getSQLiteOpenHelperObject(){
		return this;
	}

	// public void deleteRowByTagID(String TagID) {
	// applicationDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME,
	// null, SQLiteDatabase.OPEN_READWRITE);
	// applicationDatabase.delete(TABLE_NAME, this.TagID + " = '" + TagID
	// + "'", null);
	// applicationDatabase.close();
	// }

}

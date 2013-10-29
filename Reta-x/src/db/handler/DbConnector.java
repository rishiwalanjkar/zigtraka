package db.handler;

import deploy.appdata.directory;
import android.database.sqlite.SQLiteDatabase;

public class DbConnector {
	private static String DB_PATH = directory.databaseFolderPath+"/";
	private static String DB_NAME = "mod.sqlite";
	private static SQLiteDatabase applicationDatabase;
	
	public static SQLiteDatabase open() {
		// Open the database
		String mypath = DB_PATH + DB_NAME;
		applicationDatabase = SQLiteDatabase.openDatabase(mypath, null,
				SQLiteDatabase.OPEN_READWRITE);
		return applicationDatabase;
	}

	public static void close() {
		applicationDatabase.close();
	}

}

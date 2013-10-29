package deploy.appdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import zigtraka.nfc.reta_x.MyDatabaseHelper;


public class Creater implements directory {
	private static File file, logfile, dbfile;
	private static OutputStream Destination;
	private static InputStream Source;
	private static String DB_NAME = "mod.sqlite";

	public static void deploy(Context applicationContext) {
		try {
		new File(directory.databaseFolderPath).mkdirs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package zigtraka.nfc.reta_x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import deploy.appdata.directory;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.database.sqlite.SQLiteOpenHelper;

public class TestDb extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.test_db;
	}

//	public void addTree(File file, Collection<File> all) {
//		File[] children = file.listFiles();
//		if (children != null) {
//			for (File child : children) {
//					if (child.getAbsolutePath().endsWith(".sqlite"))
//						all.add(child);
//				addTree(child, all);
//			}
//		}
//	}

}

package zigtraka.nfc.reta_x;

import db.Access.DbForSearchProcuctActivity;
import db.handler.DbConnector;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class SearchProduct extends BaseActivity {
	private SearchView searchview;
	private ListView listview;
	private Cursor cursor;
	private EditText ProductCode, ProductModel, Gemstone, Price, Carat, Cut,
			Type, Wear, MakingCharges, Description;
	private GridLayout gridlayout;
	private ScrollView scrollviewgridlayout, scrollviewdescription;
	private Button btn_Edit, btn_Add, btn_Delete;
//	private MyDatabaseHelper mydbhelper;
	private boolean EditFlag = false;
	private int ListViewSelectedItemPosition = -1;
	private TextView Txt_NoOfItemsFound, Txt_productInfoTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		mydbhelper = new MyDatabaseHelper(getApplicationContext());

		gridlayout = (GridLayout) findViewById(R.id.search_product_gridLayout);
		scrollviewgridlayout = (ScrollView) findViewById(R.id.search_product_scrollviewgridLayout);
		scrollviewdescription = (ScrollView) findViewById(R.id.search_product_descriptionscrollview);

		ProductCode = (EditText) findViewById(R.id.search_product_product_code);
		ProductModel = (EditText) findViewById(R.id.search_product_product_model);
		Gemstone = (EditText) findViewById(R.id.search_product_gemestone);
		Price = (EditText) findViewById(R.id.search_product_price);
		Carat = (EditText) findViewById(R.id.search_product_carat);
		Cut = (EditText) findViewById(R.id.search_product_cut);
		Type = (EditText) findViewById(R.id.search_product_type);
		Wear = (EditText) findViewById(R.id.search_product_wear);
		MakingCharges = (EditText) findViewById(R.id.search_product_making_Charges);
		Description = (EditText) findViewById(R.id.search_product_description);
		Txt_NoOfItemsFound = (TextView) findViewById(R.id.search_product_no_of_itemstextview);
		Txt_productInfoTitle = (TextView) findViewById(R.id.search_product_productinfotextview);

		btn_Edit = (Button) findViewById(R.id.search_product_editbutton);
		btn_Add = (Button) findViewById(R.id.search_product_addbutton);
		btn_Delete = (Button) findViewById(R.id.search_product_deletebutton);

		listview = (ListView) findViewById(R.id.search_product_listView);
		searchview = (SearchView) findViewById(R.id.search_product_searchview);
		
		searchview.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub

				// initially data is invisible as nothing is clicked on listview
				gridlayout.setVisibility(View.INVISIBLE);
				scrollviewgridlayout.setVisibility(View.INVISIBLE);
				scrollviewdescription.setVisibility(View.INVISIBLE);
				Description.setVisibility(View.INVISIBLE);

				Txt_NoOfItemsFound.setVisibility(View.VISIBLE);
				Txt_productInfoTitle.setVisibility(View.INVISIBLE);

				// getting result of searched keyword in cursor
				cursor = DbForSearchProcuctActivity.searchViewResult(searchview.getQuery().toString());

				if (cursor != null) {
					// string which will hold list of data for listview
					Txt_NoOfItemsFound.setText(String.valueOf(cursor.getCount())
							+ " Results Found:");
					String[] details = new String[cursor.getCount()];

					// adding data to list from cursor
					if (cursor.moveToFirst())
						for (int i = 0; i < cursor.getCount(); i++) {
							details[i] = cursor.getString(4);
							cursor.moveToNext();
						}
                    DbConnector.close();
                    
					// defining adapter for listview
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							getApplicationContext(),
							android.R.layout.simple_expandable_list_item_1,
							details);

					// setting adapter for listview
					listview.setAdapter(adapter);
					listview.setVisibility(View.VISIBLE);

					getApplicationContext();
					// hide virtual keyboard
					((InputMethodManager) getApplicationContext()
							.getSystemService(
									Context.INPUT_METHOD_SERVICE))
							.hideSoftInputFromWindow(getCurrentFocus()
									.getWindowToken(),
									InputMethodManager.HIDE_NOT_ALWAYS);

				listview.requestFocusFromTouch();
				}
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				// checking cursor before further processing
				if (cursor != null)
					// moving to respective record of cursor using absolute
					// position
					if (cursor.moveToPosition(position)) {

						// setting respective records to respective edittexts
						ProductCode.setText(cursor.getString(7));
						ProductModel.setText(cursor.getString(4));
						Gemstone.setText(cursor.getString(8));
						Price.setText(cursor.getString(2));
						Carat.setText(cursor.getString(13));
						Cut.setText(cursor.getString(10));
						Type.setText(cursor.getString(11));
						Wear.setText(cursor.getString(12));
						MakingCharges.setText(cursor.getString(5));
						Description.setText(cursor.getString(9));

						// setting all data visible
						gridlayout.setVisibility(View.VISIBLE);
						scrollviewgridlayout.setVisibility(View.VISIBLE);
						scrollviewdescription.setVisibility(View.VISIBLE);
						Description.setVisibility(View.VISIBLE);
						Txt_productInfoTitle.setVisibility(View.VISIBLE);

						ListViewSelectedItemPosition = position;
					}
			}

		});
		btn_Edit.setOnClickListener(new OnClickListener() {
			// hitting database once.....
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String TagID;

				if (EditFlag) {
					if (cursor.moveToPosition(ListViewSelectedItemPosition)) {
						TagID = cursor.getString(0);
						DbForSearchProcuctActivity.updateRowByTagID(TagID, ProductCode
								.getText().toString(), ProductModel.getText()
								.toString(), Gemstone.getText().toString(),
								Price.getText().toString(), Carat.getText()
										.toString(), Cut.getText().toString(),
								Type.getText().toString(), Wear.getText()
										.toString(), MakingCharges.getText()
										.toString());
					}

					ProductCode.setEnabled(false);
					ProductModel.setEnabled(false);
					Gemstone.setEnabled(false);
					Price.setEnabled(false);
					Carat.setEnabled(false);
					Cut.setEnabled(false);
					Type.setEnabled(false);
					Wear.setEnabled(false);
					MakingCharges.setEnabled(false);
					Description.setEnabled(false);

					btn_Edit.setText("Edit");
					btn_Add.setEnabled(true);
					btn_Delete.setEnabled(true);
					EditFlag = false;
				} else {
					ProductCode.setEnabled(true);
					ProductModel.setEnabled(true);
					Gemstone.setEnabled(true);
					Price.setEnabled(true);
					Carat.setEnabled(true);
					Cut.setEnabled(true);
					Type.setEnabled(true);
					Wear.setEnabled(true);
					MakingCharges.setEnabled(true);
					Description.setEnabled(true);

					btn_Edit.setText("Save");
					btn_Add.setEnabled(false);
					btn_Delete.setEnabled(false);
					EditFlag = true;
				}
			}
		});

	}

	@Override
	protected int getResourceLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.search_product;
	}
}

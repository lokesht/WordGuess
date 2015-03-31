package in.sel.smileword;

import in.sel.utility.AppConstants;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	String TAG = "DBHelper";

	/** */
	SQLiteDatabase db;

	public static final int DATABASE_VERSION = 1;
	public static final String DB_NAME = "BabyName.sqlite";

	public static final String DB_SUFFIX = "/databases/";
	Context myContext;

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(TableContract.Category.SQL_CREATE);
			db.execSQL(TableContract.HintWord.SQL_CREATE);

			if (AppConstants.DEBUG)
				Log.i(TAG, "Tatabase created Successfully");
		} catch (Exception e) {
			if (AppConstants.DEBUG)
				Log.e(TAG, e.toString());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

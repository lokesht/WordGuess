package in.sel.smileword;

import in.sel.exception.ValueNotInsertedException;
import in.sel.model.M_WordHint;
import in.sel.utility.AppConstants;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

	/** Insert Words with Hints */
	public long insertWord_Hint(List<M_WordHint> lsData) {
		long rowid = 0;
		try {
			db = this.getWritableDatabase();

			for (M_WordHint obj : lsData) {
				ContentValues cv = new ContentValues();
				cv.put(TableContract.HintWord.HINT, obj.getHint());
				cv.put(TableContract.HintWord.WORD, obj.getWord());

				rowid = db.insert(TableContract.HintWord.TABLE_NAME, TableContract.HintWord.HINT, cv);

				if (rowid < 0) {
					throw new ValueNotInsertedException();
				}
			}

		} catch (Exception e) {
			if (AppConstants.DEBUG)
				Log.e("insertName", e.toString());
		} finally {
			db.close();
		}
		return rowid;
	}

	/** Insert Category of Words */
	public long insertCategory(List<String> lsData) {
		long rowid = 0;
		try {
			db = this.getWritableDatabase();

			for (String obj : lsData) {
				ContentValues cv = new ContentValues();
				cv.put(TableContract.Category.CATEGORY_NAME, obj);

				rowid = db.insert(TableContract.Category.TABLE_NAME, TableContract.Category.CATEGORY_NAME, cv);

				if (rowid < 0) {
					throw new ValueNotInsertedException();
				}
			}

		} catch (Exception e) {
			if (AppConstants.DEBUG)
				Log.e("insertName", e.toString());
		} finally {
			db.close();
		}
		return rowid;
	}

	/**
	 * @param TableName
	 * @param columns
	 * @param where
	 * @return Cursor
	 */
	public Cursor getTableValue(String TableName, String[] columns, String where) {
		Cursor c = null;
		try {
			db = this.getReadableDatabase();
			c = db.query(TableName, columns, where, null, null, null, null, null);
		} catch (Exception e) {
			if (AppConstants.DEBUG)
				Log.e(TAG, e.toString() + "--> getTableValue()");
		} finally {
			/** If database does not contain anything immediately close database */
			if (c == null)
				db.close();
		}
		return c;
	}
}

package in.sel.smileword;

import in.sel.model.M_WordHint;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	/** Text, which player have to guess */
	private String strGuessText = "OHELLO";

	/** Holds User Input */
	private char[] chArray;

	/** Indicate no of chances for player */
	private int noOfChances = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/** Initialize all buttons */
		init();
	}

	/** Initialize all button */
	private void init() {

		int[] id = { R.id.btn_a, R.id.btn_b, R.id.btn_c, R.id.btn_d, R.id.btn_e, R.id.btn_f, R.id.btn_g, R.id.btn_h,
				R.id.btn_i, R.id.btn_j, R.id.btn_k, R.id.btn_l, R.id.btn_m, R.id.btn_n, R.id.btn_o, R.id.btn_p,
				R.id.btn_q, R.id.btn_r, R.id.btn_s, R.id.btn_t, R.id.btn_u, R.id.btn_v, R.id.btn_w, R.id.btn_x,
				R.id.btn_y, R.id.btn_z };

		Button btn;
		for (int temp : id) {
			btn = (Button) findViewById(temp);
			btn.setOnClickListener(this);
		}

		/** Default initialization */
		chArray = new char[strGuessText.length()];

		/** Initialize with space */
		for (int i = 0; i < chArray.length; i++)
			chArray[i] = ' ';

		/** Insert Some Default Value to database */
		List<M_WordHint> lsTemp = new ArrayList<M_WordHint>();
		M_WordHint tem = new M_WordHint("HELLO", "When People meet they say this..");
		M_WordHint tem1 = new M_WordHint("LAST LOCAL", "Its Us");
		M_WordHint tem2 = new M_WordHint("ANDROID", "a robot with a human appearance");
		M_WordHint tem3 = new M_WordHint("PRANAV MUKHARJI", "Prsident of India");

		lsTemp.add(tem);
		lsTemp.add(tem1);
		lsTemp.add(tem2);
		lsTemp.add(tem3);

		DBHelper db = new DBHelper(this);
		db.insertWord_Hint(lsTemp);
	}

	/** For new word It will reset all parameters */
	public void reset() {

		/** Default Word */
		strGuessText = "OHELLO";

		/** Reset Chances */
		noOfChances = 5;

		/** Show to user About chances */
		TextView tv = (TextView) findViewById(R.id.tv_no_of_chances);
		tv.setText(noOfChances + "");

		/** Reset Hint and word both */
		tv = (TextView) findViewById(R.id.tv_hint);
		tv.setText("");

		tv = (TextView) findViewById(R.id.tv_smile_word);
		tv.setText("");

		/** Reset All buttons */
		int[] id = { R.id.btn_a, R.id.btn_b, R.id.btn_c, R.id.btn_d, R.id.btn_e, R.id.btn_f, R.id.btn_g, R.id.btn_h,
				R.id.btn_i, R.id.btn_j, R.id.btn_k, R.id.btn_l, R.id.btn_m, R.id.btn_n, R.id.btn_o, R.id.btn_p,
				R.id.btn_q, R.id.btn_r, R.id.btn_s, R.id.btn_t, R.id.btn_u, R.id.btn_v, R.id.btn_w, R.id.btn_x,
				R.id.btn_y, R.id.btn_z };

		Button btn;
		for (int temp : id) {
			btn = (Button) findViewById(temp);
			btn.setBackgroundResource(android.R.drawable.btn_default_small);
			btn.setEnabled(true);
		}

		/** initialization of char array with same length */
		chArray = new char[strGuessText.length()];
		/** Initialize with space */
		for (int i = 0; i < chArray.length; i++)
			chArray[i] = ' ';

		/** Enable tap button Now Until game finish */
		Button b = (Button) findViewById(R.id.btn_tap);
		b.setEnabled(true);
	}

	/** */
	public void onHint(View v) {
		/** Initialize guess Text */
		DBHelper db = new DBHelper(this);
		String where = null;
		String[] column = null;
		Cursor c = db.getTableValue(TableContract.HintWord.TABLE_NAME, column, where);

		List<M_WordHint> ls = parseCursor(c);
		M_WordHint temp = ls.get(new Random().nextInt(ls.size()));
		strGuessText = temp.getWord();

		TextView tv = (TextView) findViewById(R.id.tv_hint);
		tv.setText(temp.getHint());

		/** initialization of char array with same length */
		chArray = new char[strGuessText.length()];
		/** Initialize with space */
		for (int i = 0; i < chArray.length; i++)
			chArray[i] = ' ';

		/** Reset Under line also */
		tv = (TextView) findViewById(R.id.tv_smile_word_underline);
		tv.setText("");
		for (int i = 0; i < strGuessText.length(); i++) {
			if (strGuessText.charAt(i) == ' ')
				tv.append(" ");
			else
				tv.append("_ ");
		}
	}

	@Override
	public void onClick(View v) {

		/** Once Started playing Disable tap button Now Until game finish */
		Button b = (Button) findViewById(R.id.btn_tap);
		b.setEnabled(false);
		
		/* get the text of clicked button*/
		Button btnTemp = (Button) v;
		String strChar = btnTemp.getText().toString();
		
		/* get the target strign*/
		String temp = strGuessText.toUpperCase(Locale.ENGLISH);
		
		/* compare them .. does clicked button is in target string*/
		if (temp.contains(strChar)) {
			btnTemp.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
			fillBlankSpace(strGuessText, strChar.charAt(0));
		} else {
			btnTemp.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
			noOfChances--;

			/** Show to user About reducing chances */
			TextView tv = (TextView) findViewById(R.id.tv_no_of_chances);
			tv.setText(noOfChances + "");
		}

		/** Once Button is Clicked Disabled It */
		btnTemp.setEnabled(false);

		if (noOfChances == 0) {
			Toast.makeText(this, "Oh sorry ! Game Over", Toast.LENGTH_SHORT).show();

			/** Display Answer g before refreshing */
			TextView tv = (TextView) findViewById(R.id.tv_smile_word);
			tv.setText(strGuessText);

			/** Delay for 2 Sec before refresh */
			Handler handle = new Handler();
			handle.postDelayed(new Runnable() {

				@Override
				public void run() {
					reset();
				}
			}, 2000);
		}
	}

	/** fill the blank space with Word with clicked alphabet */
	public void fillBlankSpace(String src, char alphabet) {
		char[] tempChar = src.toCharArray();

		for (int i = 0; i < tempChar.length; i++) {
			if (tempChar[i] == alphabet) {
				chArray[i] = alphabet;
			}
		}

		/** Display New generated Word */
		TextView tv = (TextView) findViewById(R.id.tv_smile_word);
		String temp = String.valueOf(chArray);
		tv.setText(temp);

		/* If string contains space ,that means more char are needed to fill word */
		if ((String.valueOf(chArray)).equalsIgnoreCase(strGuessText)) {
			Toast.makeText(this, "Congrats you won ", Toast.LENGTH_SHORT).show();

			/** Delay for 2 Sec before refresh */
			Handler handle = new Handler();
			handle.postDelayed(new Runnable() {

				@Override
				public void run() {
					reset();
				}
			}, 2000);
		}
	}

	/** */
	private List<M_WordHint> parseCursor(Cursor c) {
		List<M_WordHint> ls = new ArrayList<M_WordHint>();
		if (c != null && c.getCount() > 0) {
			c.moveToFirst();
			do {
				int id = c.getInt(c.getColumnIndex(TableContract.HintWord.AUTO_ID));
				String word = c.getString(c.getColumnIndex(TableContract.HintWord.WORD));
				String hint = c.getString(c.getColumnIndex(TableContract.HintWord.HINT));

				M_WordHint temp = new M_WordHint(word, hint, id);
				ls.add(temp);

			} while (c.moveToNext());

			/** */
			c.close();
		}
		return ls;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent in = new Intent(this, ActivityPascleTriangle.class);
			startActivity(in);
		}
		return super.onOptionsItemSelected(item);
	}
}

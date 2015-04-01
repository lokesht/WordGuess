package in.sel.smileword;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPascleTriangle extends Activity {

	int[] previousRow;
	int[] previousRowTemp;
	String strRow = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pascle);

		getActionBar().setHomeButtonEnabled(true);

		init();
		
		/** For Default Value*/
		onSubmit(null);
	}

	public void init() {
		/** Default Start point as one */
		previousRow = new int[] { 1 };
	}

	public void onSubmit(View v) {
		EditText etStart = (EditText) findViewById(R.id.et_start_point);
		EditText etNoOfRow = (EditText) findViewById(R.id.et_no_of_row);

		String start = etStart.getEditableText().toString();
		String noRow = etNoOfRow.getEditableText().toString();

		/** Default Initialization */
		if (start == null || start.length() == 0)
			start = "1";

		if (noRow == null || noRow.length() == 0)
			noRow = "5";

		try {
			int startPoint = Integer.valueOf(start);
			int noOfRow = Integer.valueOf(noRow);
			
			/** Default Start point as one */
			previousRow[0] =startPoint;
			
			printTriangle(startPoint, noOfRow);

		} catch (NumberFormatException e) {
			Toast.makeText(this, "Enter Only Number", Toast.LENGTH_SHORT).show();
		}
	}

	public void printTriangle(int startPoint, int noOfRow) {

		TextView tv = (TextView) findViewById(R.id.tv_pascle);
		tv.setText("");
		for (int i = 0; i < noOfRow; i++) {
			previousRowTemp = new int[i + 1];
			for (int j = 0; j < (noOfRow - (i)); j++) {
				strRow = " " + strRow;
			}

			for (int k = 0; k <= i; k++) {
				if (k == 0 || k == i) {
					strRow = strRow + " " + previousRow[0];
					previousRowTemp[k] = previousRow[0];
				} else {
					int tmp = previousRow[k - 1] + previousRow[k];
					strRow = strRow + " " + tmp;
					previousRowTemp[k] = tmp;
				}
			}

			previousRow = previousRowTemp;
			tv.append(strRow + "\n");

			/* Initialize String for next input */
			strRow = "";
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

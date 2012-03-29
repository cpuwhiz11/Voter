package voter.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class viewQuestionStats extends Activity implements OnClickListener{

	private static final String TAG = "viewQuestionStats";

	private Button retrieveQuestionBtn; 

	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewquestionstats);

		retrieveQuestionBtn = (Button) findViewById(R.id.retrieveQuestionBtn);
		retrieveQuestionBtn.setOnClickListener(this); 
	}

	public void onClick(View v) {
		switch (v.getId()) {

		// Send to people
		case R.id.retrieveQuestionBtn:

			// go to the site and retrieve the question
			// pull id from field
			retrieveQuestionBtn.toString();
			
			// show some stats
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}

	}

}

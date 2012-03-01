package voter.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SendQuestion extends Activity implements OnClickListener{

	private static final String TAG = "CreateNewQuestionActivity";

	private Button selectSenders; 

	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendquestion);

		// Populate list of questions the user has created
		// Need to query the DB 


		selectSenders = (Button) findViewById(R.id.selectSenders);
		selectSenders.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {

		// Send to people
		case R.id.selectSenders:

			//Bundle up selected questions

			Intent createNewQuestion = new Intent(this, SelectSenders.class);
			startActivity(createNewQuestion);
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}

	}

}

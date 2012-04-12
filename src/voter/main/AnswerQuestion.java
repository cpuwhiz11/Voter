package voter.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AnswerQuestion extends Activity implements OnClickListener {

	private static final String TAG = "AnswerQuestion";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answerquestion);
		
		// User enters question id
		
		
		
	}
	
	/** Handle clicks */
	public void onClick(View v) {
		switch (v.getId()) {

		// Get some help
		case R.id.helpBtn:
			Intent help = new Intent(this, Help.class);
			startActivity(help);
			break;

	    // Get Send responses 
		case R.id.send:
			
			//Send responses back to owners
			
			//Display msg that we sent questions
			Toast ts = new Toast(this);
			ts.setText("Responses(s) Sent!");
			ts.show();

			//Return to main page
			Intent VoterActivity = new Intent(this, VoterActivity.class);
			startActivity(VoterActivity);
			break;

		default:
			Log.e(TAG, "onClick id not found");
		}
	}

}
